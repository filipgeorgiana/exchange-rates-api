package com.app.demo.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, BigDecimal> {

     Optional<Currency> findBySymbol(String symbol);

     Currency findById(long id);
}
