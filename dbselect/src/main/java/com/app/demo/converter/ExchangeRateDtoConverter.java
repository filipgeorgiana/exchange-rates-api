package com.app.demo.converter;

import com.app.demo.Model.Currency;
import com.app.demo.Model.CurrencyRepository;
import com.app.demo.Model.ExchangeRate;
import com.app.demo.dto.ExchangeRateDto;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class ExchangeRateDtoConverter {

    private CurrencyRepository currencyRepository;

    public ExchangeRateDtoConverter(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public ArrayList<ExchangeRate> convertDtoToExchangeRate(ExchangeRateDto exchangeRateDto) {
        Currency baseCurrency = currencyRepository.findBySymbol(exchangeRateDto.getBase())
                .orElseThrow(EntityNotFoundException::new);

        ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
        
        for (Map.Entry<String, Double> entry : exchangeRateDto.getRates().entrySet()) {
             currencyRepository.findBySymbol(entry.getKey())
                     .ifPresent((pairCurrency)-> {
                        ExchangeRate exchangeRate = buildExchangeRate(exchangeRateDto, baseCurrency, entry, pairCurrency);

                        exchangeRates.add(exchangeRate);
                    }
            );
        }

        return exchangeRates;
    }

    private ExchangeRate buildExchangeRate(ExchangeRateDto exchangeRateDto, Currency baseCurrency, Map.Entry<String, Double> entry, Currency pairCurrency) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBaseCurrency(baseCurrency);
        exchangeRate.setDate(exchangeRateDto.getDate());
        exchangeRate.setPairCurrency(pairCurrency);
        exchangeRate.setRate(BigDecimal.valueOf(entry.getValue()));
        return exchangeRate;
    }
}
