package com.app.demo.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long> {
    Optional<Payment> findByItem(String item);

    @Modifying
    void deleteByItem(String item);

    @Query(nativeQuery = true, value = "SELECT pay.value * e.rate " +
            "FROM payments AS pay " +
            "         JOIN exchange_rates e ON e.base_currency_id = pay.currency_id " +
            "         JOIN currency c ON e.pair_currency_id = c.id " +
            "WHERE item = :item AND c.symbol = :symbol AND e.date = :date")
    Optional<BigDecimal> findValueByPairCurrency(@Param("item")String item, @Param("symbol") String symbol, @Param("date") LocalDate date);
}
