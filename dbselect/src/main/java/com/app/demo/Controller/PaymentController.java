package com.app.demo.Controller;

import com.app.demo.Model.Payment;
import com.app.demo.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping(path = "/item/{item}")
    public Payment findByItem(@PathVariable String item) {
        return paymentService.findByItem(item);
    }

    @GetMapping
    public List<Payment> findAll() {
        return paymentService.findAll();
    }


    @GetMapping(path="/convert/item/{item}/symbol/{symbol}/date/{date}")
    public BigDecimal convertPayment(@PathVariable String item, @PathVariable String symbol, @PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return paymentService.convertPayment(item, symbol, date);
    }

    @PostMapping
    public void addPayment(@RequestBody Payment payment){
        paymentService.save(payment);
    }

    @DeleteMapping(path="/item/{item}")
    public void deletePayment(@PathVariable String item) {
        paymentService.deleteByItem(item);
    }

    @PutMapping(path="/{item}")
    public void updatePayment(@PathVariable String item, @RequestBody Payment payment) {
        paymentService.update(item,payment);
    }
}
