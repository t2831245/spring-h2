package com.demo;

import com.demo.entity.Currency;
import com.demo.entity.CurrencyName;
import com.demo.entity.model.CurrencyNameModel;
import com.demo.repository.CurrencyNameRepository;
import com.demo.repository.CurrencyRepository;
import com.demo.service.RestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ApiTest {

    @Autowired
    RestService restService;

    @Autowired
    CurrencyNameRepository currencyNameRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Test
    void TestQuery() {
        currencyRepository.findByCurrencyNameEname(CurrencyNameModel.EUR);
    }

    @Test
    void TestInsert() {
        currencyRepository.save(
                Currency.builder()
                        .currencyName(
                                CurrencyName
                                        .builder()
                                        .cname("新台幣")
                                        .ename(CurrencyNameModel.TWD)
                                        .build()
                        )
                        .rateFloat(0F)
                        .build()
        );

//        Currency currency = currencyRepository.findByCurrencyNameEname(CurrencyNameModel.TWD).get();
//        System.out.println(currency.getCurrencyName().getCname());
    }

    @Test
    void TestUpdate(){
        Currency c = currencyRepository.findByCurrencyNameEname(CurrencyNameModel.USD).get();
        c.setDescription("test update...");
        currencyRepository.save(c);
    }

    @Test
    void TestDelete(){
        Currency c = currencyRepository.findByCurrencyNameEname(CurrencyNameModel.EUR).get();
        currencyRepository.delete(c);
    }
}
