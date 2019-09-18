package com.app.demo.Service;

import com.app.demo.Model.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    private CurrencyRepository currencyRepository;

    private ExchangeRateRepository exchangeRateRepository;

    public PaymentService(PaymentRepository paymentRepository, CurrencyRepository currencyRepository, ExchangeRateRepository exchangeRateRepository) {
        this.paymentRepository = paymentRepository;
        this.currencyRepository = currencyRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public Payment findByItem(String item) {
        return paymentRepository.findByItem(item)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void save(Payment payment) { paymentRepository.save(payment); }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public void deleteByItem(String item) {
       paymentRepository.deleteByItem(item);
    }

    @Transactional
    public void update(String item, Payment payment) {
        Payment newPayment = paymentRepository.findByItem(item).
                orElseThrow(EntityNotFoundException::new);
        newPayment.setItem(payment.getItem());
        newPayment.setValue(payment.getValue());
        newPayment.setCurrency(payment.getCurrency());
        newPayment.setDate(payment.getDate());
    }
    /*
    public BigDecimal convertPayment(String item, String symbol, LocalDate date) {
        Payment payment = paymentRepository.findByItem(item)
                .orElseThrow(EntityNotFoundException::new);

        Currency baseCurrency = currencyRepository.findById(payment.getCurrency().getId());
        Currency pairCurrency = currencyRepository.findBySymbol(symbol)
                .orElseThrow(EntityNotFoundException::new);

        return pairCurrency.equals(baseCurrency)
                ? payment.getValue() : determinedConvertedValue(payment, baseCurrency, pairCurrency, date);
    }

    private BigDecimal determinedConvertedValue(Payment payment, Currency baseCurrency, Currency pairCurrency, LocalDate date) {
        ExchangeRate exchangeRate = exchangeRateRepository.findByBaseCurrencyIdAndPairCurrencyIdAndDate(baseCurrency.getId(), pairCurrency.getId(), date)
                .orElseThrow(EntityNotFoundException::new);

        return payment.getValue().multiply(exchangeRate.getRate());
    }

     */

    public BigDecimal convertPayment(String item, String symbol, LocalDate date) {
        String itemCurrencySymbol = paymentRepository.findByItem(item)
                .orElseThrow(EntityNotFoundException::new).getCurrency().getSymbol();

       // if(!itemCurrencySymbol.equals(symbol))
            return paymentRepository.findValueByPairCurrency(item, symbol, date)
                    .orElseThrow(EntityNotFoundException::new);
    }
}
