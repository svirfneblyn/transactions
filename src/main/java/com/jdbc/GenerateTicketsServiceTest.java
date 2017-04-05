package com.jdbc;

import com.jdbc.service.LotteryTicketService;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;


public class GenerateTicketsServiceTest {
    public static DBManager dbm;
    public static LotteryTicketService ls;

    @Before
    public void init() {
        dbm = new DBManager();
        ls = new LotteryTicketService(dbm);
        try {
            ls.clearTable();
            ls.ticketsSaverToDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void runBuyers() {
        try {
            ls.runBuyers();
            ls.showTicketOwners();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void showTicketOwners() {
        ls.showTicketOwners();
    }
}