package com.app.demo.Service;

import com.app.demo.Model.Currency;
import com.app.demo.Model.CurrencyRepository;
import com.app.demo.Model.ExchangeRate;
import com.app.demo.Model.ExchangeRateRepository;
import com.app.demo.client.ExchangeRateClient;
import com.app.demo.converter.ExchangeRateDtoConverter;
import com.app.demo.dto.ExchangeRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyService currencyService;


    public ExchangeRate findById(Long id) {
        return exchangeRateRepository.findById(id)
                .orElse(null);
    }

    public void addExchangeRates(LocalDate date) {
        List<Currency> currencies = currencyRepository.findAll();
        ExchangeRateDtoConverter exchangeRateDtoConverter = new ExchangeRateDtoConverter(currencyRepository);

        ExchangeRateClient exchangeRateClient = new ExchangeRateClient(currencyService.getCurrencySymbols());

        for (Currency baseCurrency : currencies) {
            ExchangeRateDto exchangeRateDto = exchangeRateClient.getExchangeRatesDto(baseCurrency.getSymbol(), date);
            ArrayList<ExchangeRate> exchangeRates = exchangeRateDtoConverter.convertDtoToExchangeRate(exchangeRateDto);

            saveExchangeRates(exchangeRates, baseCurrency);
        }
    }

    private void saveExchangeRates(ArrayList<ExchangeRate> exchangeRates, Currency baseCurrency) {
        ArrayList<ExchangeRate> rates = new ArrayList<>();
        for (ExchangeRate exchangeRate : exchangeRates) {
            if( ! baseCurrency.equals(currencyRepository.findById(exchangeRate.getPairCurrency().getId()))) {
                rates.add(exchangeRate);
            }
        }
        exchangeRateRepository.saveAll(rates);
    }
}
