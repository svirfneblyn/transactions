package com.rest.controller;

import com.transactions.service.LotteryTicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trans")
public class TransactionsController {
    @Autowired
    private LotteryTicketServiceImpl service;

    @RequestMapping(value = "/add/{number}", method = RequestMethod.GET)
    public void addTicketsToDB(@PathVariable("number") String number) {
        service.ticketsSaverToDB(number);
    }
    @RequestMapping(value = "/by/{byer_id}", method = RequestMethod.GET)
    public void byeTicket(@PathVariable("byer_id") String byerId) {
        service.buy(byerId);
    }

}
