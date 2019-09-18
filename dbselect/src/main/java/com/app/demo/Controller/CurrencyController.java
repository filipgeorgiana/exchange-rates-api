package com.app.demo.Controller;

import com.app.demo.Model.Currency;
import com.app.demo.Service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping(path = "/currency-symbol/{currencySymbol}")
    public Currency findBySymbol(@PathVariable String currencySymbol) {
        return currencyService.findBySymbol(currencySymbol).orElse(null);
    }
}
