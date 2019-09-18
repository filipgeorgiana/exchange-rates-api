package com.app.demo.ui;

import com.app.demo.Model.Currency;
import com.app.demo.Model.CurrencyRepository;
import com.app.demo.Model.Payment;
import com.app.demo.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class UiController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    CurrencyRepository currencyRepository;

    @RequestMapping(path="/ui/convert/item/{item}/symbol/{symbol}/date/{date}")
    public String convertPayment(@PathVariable String item, @PathVariable String symbol, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model) {
        BigDecimal convertedValue = paymentService.convertPayment(item, symbol, date);

        return addModel(symbol, model, convertedValue, paymentService.findByItem(item));
    }

    private String addModel(@PathVariable String symbol, Model model, BigDecimal convertedValue, Payment payment) {
        Currency currency = currencyRepository.findById(payment.getCurrency().getId());

        model.addAttribute("convertedValue",convertedValue);
        model.addAttribute("item",payment.getItem());
        model.addAttribute("value", payment.getValue());
        model.addAttribute("baseCurrency", currency.getSymbol());
        model.addAttribute("targetCurrency", symbol);

        return "index";
    }
}
