package com.transactions.service;

import com.transactions.entity.LotteryTicket;
import org.springframework.stereotype.Component;

@Component
public interface ILotteryTicketService {
    LotteryTicket buy(String buyerId);
    void save(LotteryTicket lotteryTicket);
}
