package com.jdbc.service;

import com.jdbc.DBManager;
import org.apache.log4j.LogManager;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service

public class LotteryTicketService {
    private static org.apache.log4j.Logger logger = LogManager.getRootLogger();
    private DBManager dbm;
    private AtomicInteger count = new AtomicInteger();
    private ExecutorService byerPool = Executors.newFixedThreadPool(50);
    private String byerName = "Byer_";
    private ConcurrentHashMap<String, String> conrolMap = new ConcurrentHashMap<>();
    private String selectQuery = "select id, ticket_number from lottery_tickets where buyer_id is null limit 1";
    private String updateQuery = "UPDATE lottery_tickets SET buyer_id = ? WHERE id = ?";

    public LotteryTicketService(DBManager dbm) {
        this.dbm = dbm;
    }

    /*сервис покупки билетов. выбирается первый попавшийся с null в byerId и записывается туда имя покупателя*/
    public synchronized Boolean buy(String buyerId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmSelect = null;
        PreparedStatement pstmUpdate = null;

        int id = 0;
        String ticketId = "_";
        try {
            con = dbm.openConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            pstmSelect = con.prepareStatement(selectQuery, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            pstmUpdate = con.prepareStatement(updateQuery, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstmSelect.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                ticketId = rs.getString(2);
            }
            pstmUpdate.setString(1, buyerId);
            pstmUpdate.setInt(2, id);
            pstmUpdate.executeUpdate();
            addToMap(buyerId, ticketId);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmSelect != null) {
                pstmSelect.close();
            }
            if (pstmUpdate != null) {
                pstmUpdate.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

    /*Генерация и сохраниение 10000тыс билетов в БД*/
    public void ticketsSaverToDB() throws SQLException {
        Connection con = dbm.openConnection();
        con.setAutoCommit(false);
        PreparedStatement pstm = con.prepareStatement("INSERT INTO lottery_tickets VALUES(?,?,?)");
        for (int i = 0; i < 10000; i++) {
            pstm.setInt(1, i);
            pstm.setString(2, null);
            pstm.setString(3, "OT_" + i);
            pstm.executeUpdate();
        }
        con.commit();
        pstm.close();
        con.close();
    }

    /*подготовка таблицы - удаление всех записей.*/
    public void clearTable() throws SQLException {
        Connection con = dbm.openConnection();
        con.prepareStatement("DELETE FROM lottery_tickets").execute();
    }

    /*контрольная мапа для сравнения с записями в БД*/
    public void addToMap(String byerId, String ticketId) {
        conrolMap.put(byerId, ticketId);
    }

    public DBManager getDbm() {
        return dbm;
    }

    /*имитация активности сервера, на 50 соединений создаётся по 200 запросов на покупку билета. */
    public void runBuyers() throws InterruptedException {
        Collection<Callable<Boolean>> byerTasks = new ArrayList<Callable<Boolean>>();
        for (int i = 0; i < 50; i++) {
            byerTasks.add(() -> {
                for (int j = 0; j < 200; j++) {
                    buy(byerName + count.incrementAndGet());
                }
                return true;
            });
        }
        System.out.println("byers size = " + byerTasks.size());
        byerPool.invokeAll(byerTasks);
    }

    /*вывод записей из контрольной мапы*/
    public void showTicketOwners() {
        logger.info("cintrolMap size : " + getConrolMap().size());
        getConrolMap().forEach((k, v) -> logger.info("byerId : " + k + " ;  ticketId  " + v));
    }

    public ConcurrentHashMap<String, String> getConrolMap() {
        return conrolMap;
    }

}
