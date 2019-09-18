package com.app.demo.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table (name="exchange_rates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="base_currency_id")
    private Currency baseCurrency;

    @ManyToOne
    @JoinColumn(name="pair_currency_id")
    private Currency pairCurrency;

    @Column(name="rate")
    private BigDecimal rate;

    @Column(name="date")
    private LocalDate date;

    public ExchangeRate(){}

    public ExchangeRate(Currency baseCurrency, Currency pairCurrency, BigDecimal rate, LocalDate date) {
        this.baseCurrency = baseCurrency;
        this.pairCurrency = pairCurrency;
        this.rate = rate;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Currency getPairCurrency() {
        return pairCurrency;
    }

    public void setPairCurrency(Currency pairCurrency) {
        this.pairCurrency = pairCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
