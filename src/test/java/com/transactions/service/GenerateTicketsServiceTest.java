package com.transactions.service;

import com.jdbc.DBManager;
import com.jdbc.service.LotteryTicketService;
import org.junit.Before;
import org.junit.Test;


public class GenerateTicketsServiceTest {
    public static  DBManager dbm;
    public static   LotteryTicketService ls;

    @Before
    public  void init() {
        dbm = new DBManager();
        dbm.openConnectionPool(50);
        ls =new LotteryTicketService(dbm);
    }

    @Test
    public  void ticketsSaverToDB() throws Exception {
   // ls.ticketsSaverToDB();
    }
    @Test
    public void runBuyers(){
        try {
            ls.runBuyers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}