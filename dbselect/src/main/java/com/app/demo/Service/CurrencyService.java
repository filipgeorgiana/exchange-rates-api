package com.app.demo.Service;

import com.app.demo.Model.Currency;
import com.app.demo.Model.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService{
    @Autowired
    private CurrencyRepository currencyRepository;

    public Optional<Currency> findBySymbol(String symbol) {
        return currencyRepository.findBySymbol(symbol);
    }

    public ArrayList<String> getCurrencySymbols () {
        List<Currency> currencies = currencyRepository.findAll();
        ArrayList<String> currenciesSymbols = new ArrayList<>();

        for (Currency currency : currencies) {
                currenciesSymbols.add(currency.getSymbol());
        }

        return currenciesSymbols;
    }
}
