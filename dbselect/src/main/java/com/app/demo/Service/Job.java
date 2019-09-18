package com.app.demo.Service;

import com.app.demo.Controller.RestResponseEntityExceptionHandler;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Job {

    @Autowired
    ExchangeRateService exchangeRateService;

    @Scheduled(cron = "0/10 * * * * *") //"0 15 9 1/1 * *" -> every day at 9:15
    public void getLatestExchangeRates() {
        try {
            exchangeRateService.addExchangeRates(LocalDate.now());
        } catch (Exception e) {
            RestResponseEntityExceptionHandler restResponseEntityExceptionHandler = new RestResponseEntityExceptionHandler();
            restResponseEntityExceptionHandler.handleMissingEntity(e);
        }
    }
}
