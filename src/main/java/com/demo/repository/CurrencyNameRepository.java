package com.demo.repository;

import com.demo.entity.CurrencyName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyNameRepository extends CrudRepository<CurrencyName, Integer> {
}
