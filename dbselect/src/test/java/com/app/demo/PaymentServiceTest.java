package com.app.demo;

import com.app.demo.Model.CurrencyRepository;
import com.app.demo.Model.ExchangeRateRepository;
import com.app.demo.Model.PaymentRepository;
import com.app.demo.Service.PaymentService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @Mock
    private  PaymentRepository paymentRepository;

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    private PaymentService paymentService;

    @Before
    public void setUp() {
        paymentService = new PaymentService(paymentRepository, currencyRepository, exchangeRateRepository);
    }

    /*
    @Before
    public void dummyObjectsInit() {
        Payment payment = new Payment();
        payment.setDate(Date.valueOf("2019-02-13"));
        payment.setCurrencyID(2);
        payment.setItem("boat");
        payment.setValue(BigDecimal.valueOf(5000.0));

        Currency baseCurrency = new Currency();
        baseCurrency.setSymbol("EUR");
        baseCurrency.setName("euro");
        baseCurrency.setId(2);

        Currency pairCurrency = new Currency();
        pairCurrency.setSymbol("RON");
        pairCurrency.setName("leu");
        pairCurrency.setId(1);

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setDate(LocalDate.of(2019,5,30));
        exchangeRate.setBaseCurrencyId(2);
        exchangeRate.setPairCurrencyId(1);
        exchangeRate.setRate(BigDecimal.valueOf(4.5));

        when(paymentRepository.findByItem("boat")).thenReturn(Optional.of(payment));
        when(currencyRepository.findBySymbol("EUR")).thenReturn(Optional.of(baseCurrency));
        when(currencyRepository.findById(2)).thenReturn(baseCurrency);
        when(currencyRepository.findBySymbol("RON")).thenReturn(Optional.of(pairCurrency));

        when(exchangeRateRepository.findByBaseCurrencyIdAndPairCurrencyIdAndDate(2,1, LocalDate.of(2019, 5, 30)))
                .thenReturn(Optional.of(exchangeRate));
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenPaymentNotFound_shouldThrowException() {
        paymentService.convertPayment("pen","EUR", LocalDate.of(2019, 5, 30));
    }

    @Test(expected = EntityNotFoundException.class)
    public void pairCurrencyNotFound_shouldThrowException() {
        paymentService.convertPayment("boat","LLL", LocalDate.of(2019, 5, 30));
    }

    @Test
    public void sameCurrencyShould_returnPaymentValue() {
        BigDecimal convertedValue = paymentService.convertPayment("boat", "EUR", LocalDate.of(2019, 5, 30));

        Assert.assertEquals(BigDecimal.valueOf(5000.0), convertedValue);
    }

    @Test
    public void whenDifferentCurrencies_shouldReturnComputedValue() {
        BigDecimal actual = paymentService.convertPayment("boat", "RON", LocalDate.of(2019, 5, 30));

        BigDecimal expected = new BigDecimal("22500.0");
        Assert.assertEquals(0, expected.compareTo(actual));
    }


     */
}