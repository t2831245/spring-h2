package com.demo;

import com.demo.controller.BpiController;
import com.demo.entity.Currency;
import com.demo.entity.CurrencyName;
import com.demo.entity.model.CurrencyNameModel;
import com.demo.repository.CurrencyNameRepository;
import com.demo.repository.CurrencyRepository;
import com.demo.service.RestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@SpringBootTest
public class CallApiTest {

    @Autowired
    RestService restService;

    @Autowired
    BpiController bpiController;

    @Autowired
    CurrencyNameRepository currencyNameRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Test
    void TestCallBpiApi() throws IOException {
        ResponseEntity e = bpiController.callApi("https://api.coindesk.com/v1/bpi/currentprice.json");
        System.out.println(" test call bpi request: " + e.getBody().toString());
    }

    @Test
    void TestQuery() throws ChangeSetPersister.NotFoundException {
        ResponseEntity e = bpiController.findByEname(CurrencyNameModel.EUR);
        System.out.println(" test queryCurrency: " + e.getBody().toString());
    }

    @Test
    void TestAddCurrency() {
        ResponseEntity e = bpiController.addCurrency(
                Currency.builder().rateFloat(10F).description("test add .").
                        currencyName(CurrencyName.builder().ename(CurrencyNameModel.TWD).cname("新台幣").build())
                        .build());
        System.out.println(" test AddCurrency: " + e.getBody().toString());
    }

    @Test
    void TestUpdate() {
        Currency ent = currencyRepository.findById(1).get();
        ent.setDescription("test update");
        ResponseEntity e = bpiController.updateCurrency(ent);
        System.out.println(" test UpdateCurrency: " + e.getBody().toString());
    }

    @Test
    void TestDelete() {
        bpiController.deleteCurrency(Currency.builder().id(4).build());
        System.out.println("deleted : " + currencyRepository.findById(4).isEmpty());
    }

    @Test
    void UpdateByApiResponse() {
        ResponseEntity e = bpiController.updateBpiData();
        System.out.println(" test UpdateByApiResponse: " + e.getBody().toString());
    }
}
