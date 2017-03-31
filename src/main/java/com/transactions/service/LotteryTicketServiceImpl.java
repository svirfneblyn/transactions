package com.transactions.service;

import com.transactions.entity.LotteryTicket;
import com.transactions.repository.LotteryTicketRepository;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LotteryTicketServiceImpl implements ILotteryTicketService{
    private static org.apache.log4j.Logger logger = LogManager.getRootLogger();

    @Autowired
    private LotteryTicketRepository lotteryRepository;

    @Override
    public LotteryTicket buy(String buyerId) {
        LotteryTicket lotteryTicket =  lotteryRepository.getNotSoldedTicket();
        lotteryTicket.setBuyerId(buyerId);
        lotteryRepository.save(lotteryTicket);
        logger.info(lotteryTicket.toString());
        return lotteryTicket;
    }
    public void ticketsSaverToDB(String number){
        List<LotteryTicket> ticketsList = new ArrayList<>();
        for (int i = 0; i <10000 ; i++) {
            LotteryTicket lotteryTicket = new LotteryTicket();
            lotteryTicket.setNumber(number + "_" + i);
            ticketsList.add(lotteryTicket);
        }
        lotteryRepository.save(ticketsList);

    }
    @Override
    public void save(LotteryTicket lotteryTicket) {
        lotteryRepository.save(lotteryTicket);
        logger.info(" ticket " + lotteryTicket.toString() + " added");
    }
}
