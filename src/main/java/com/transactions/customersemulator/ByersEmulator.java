package com.transactions.customersemulator;

import com.transactions.repository.LotteryTicketRepository;
import com.jdbc.service.LotteryTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ByersEmulator {
    @Autowired
    private LotteryTicketRepository lotteryRepository;
   /* @Autowired
    private LotteryTicketService lotteryTicketService;*/
    private AtomicInteger count = new AtomicInteger();
    private ExecutorService byerPool = Executors.newFixedThreadPool(50);
    private String byerName = "Byer_";
    public void createBuyers() throws InterruptedException {
// Collection<Callable<String>> tasks = new ArrayList<Callable<String>>();
        Collection<Callable<Boolean>> byerTasks = new ArrayList<Callable<Boolean>>();
        for (int i = 0; i <50 ; i++) {
            byerTasks.add(() -> {
            //    lotteryTicketService.buy(byerName + count.incrementAndGet());
                System.out.println("step + " + count.get());
                return true;
            });
        }
        System.out.println("byers size = " + byerTasks.size());
        byerPool.invokeAll(byerTasks);
    }

}
