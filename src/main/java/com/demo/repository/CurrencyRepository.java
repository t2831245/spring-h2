package com.demo.repository;

import com.demo.entity.Currency;
import com.demo.entity.model.CurrencyNameModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

    List<Currency> findAll();

    Optional<Currency> findByCurrencyNameEname(CurrencyNameModel currencyName);
}