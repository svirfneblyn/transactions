package com.transactions.service;

import com.transactions.entity.LotteryTicket;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component

public interface ILotteryTicketService {

    LotteryTicket buy(String buyerId);
    void save(LotteryTicket lotteryTicket);
}
