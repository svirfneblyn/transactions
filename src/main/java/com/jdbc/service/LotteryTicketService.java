package com.jdbc.service;

import com.jdbc.DBManager;
import com.transactions.entity.LotteryTicket;
import com.transactions.repository.LotteryTicketRepository;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;

@Service

public class LotteryTicketService {
    private static org.apache.log4j.Logger logger = LogManager.getRootLogger();
    private DBManager dbm;
    private AtomicInteger count = new AtomicInteger();
    private ExecutorService byerPool = Executors.newFixedThreadPool(50);
    private String byerName = "Byer_";

    public LotteryTicketService(DBManager dbm) {
        this.dbm = dbm;
    }


    public synchronized Boolean buy( String buyerId) throws SQLException {
       Connection con = null;
        PreparedStatement pstmSelect = null;
        PreparedStatement pstmUpdate = null;

        int id=0;
        try {
            con = dbm.openConnection();
           con.setAutoCommit(false);
           con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            pstmSelect = con.prepareStatement("select id from lottery_tickets where buyer_id is null limit 1");
            pstmUpdate = con.prepareStatement("UPDATE lottery_tickets SET buyer_id = ? WHERE id = ?");
            ResultSet rs = pstmSelect.executeQuery();
            while (rs.next()) {
               id  = rs.getInt(1);
            }
            pstmUpdate.setString(1, buyerId);
            pstmUpdate.setInt(2, id);
            pstmUpdate.executeUpdate();
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
           /* dbm.getConectionPool().add(con);
            logger.info("connection returned to the pool.. ");*/
        }


        return true;
    }

    public void ticketsSaverToDB() throws SQLException {
        List<LotteryTicket> ticketsList = new ArrayList<>();
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


    public DBManager getDbm() {
        return dbm;
    }

    public void runBuyers() throws InterruptedException {
            Collection<Callable<Boolean>> byerTasks = new ArrayList<Callable<Boolean>>();
            for (int i = 0; i <50 ; i++) {
                byerTasks.add(() -> {
                   // Connection con = dbm.openConnection();
                   // con.setAutoCommit(false);
               //     con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                    for (int j = 0; j <20 ; j++) {
                        buy( byerName + count.incrementAndGet());

                    }
                   // con.commit();
                    return true;
                });
            }
            System.out.println("byers size = " + byerTasks.size());
            byerPool.invokeAll(byerTasks);
    }

}
