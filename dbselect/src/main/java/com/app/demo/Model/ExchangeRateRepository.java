package com.app.demo.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository <ExchangeRate, Long> {
    @Override
    Optional<ExchangeRate> findById(Long id);

    Optional<ExchangeRate> findByBaseCurrencyIdAndPairCurrencyIdAndDate(long baseCurrencyId, long pairCurrencyId, LocalDate date);

}
