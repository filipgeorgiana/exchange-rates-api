package com.app.demo.Controller;

import com.app.demo.Model.ExchangeRate;
import com.app.demo.Service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @PostMapping("/date/{date}")
    public void addExchangeRates(@PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
       exchangeRateService.addExchangeRates(date);
    }

    @GetMapping("/id/{id}")
    public ExchangeRate findById(@PathVariable Long id) {
        return exchangeRateService.findById(id);
    }
}
