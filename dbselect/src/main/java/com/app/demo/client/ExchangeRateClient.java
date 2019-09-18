package com.app.demo.client;

import com.app.demo.dto.ExchangeRateDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExchangeRateClient {

    private static final String API_URL = "https://api.exchangeratesapi.io/";

    private ArrayList<String> currenciesSymbols;

    public ExchangeRateClient(ArrayList<String> currenciesSymbols) {
        this.currenciesSymbols=currenciesSymbols;
    }

    private String urlBuilder(String baseCurrencySymbol, LocalDate date) {

        ArrayList<String> currenciesSymbolsNew = new ArrayList<>();

        for(String symbol : currenciesSymbols) {
            if( !symbol.equals(baseCurrencySymbol)) {
                currenciesSymbolsNew.add(symbol);
            }
        }

        UriComponentsBuilder url = UriComponentsBuilder
                .fromUriString(API_URL)
                .path(date.toString())
                .queryParam("base", baseCurrencySymbol)
                .queryParam("symbols", String.join("," , currenciesSymbolsNew));

        return url.toUriString();
    }

    public ExchangeRateDto getExchangeRatesDto(String baseCurrencySymbol, LocalDate date) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ExchangeRateDto> result = restTemplate.exchange(urlBuilder(baseCurrencySymbol, date), HttpMethod.GET, null, ExchangeRateDto.class);

        return result.getBody();
    }
}
