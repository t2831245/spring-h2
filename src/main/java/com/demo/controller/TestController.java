package com.demo.controller;

import com.demo.entity.Currency;
import com.demo.entity.model.CurrencyNameModel;
import com.demo.repository.CurrencyNameRepository;
import com.demo.repository.CurrencyRepository;
import com.demo.service.RestService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("bpi")
public class TestController {

    private final RestService restService;

    private final CurrencyNameRepository currencyNameRepository;

    private final CurrencyRepository currencyRepository;

    public TestController(RestService restService, CurrencyNameRepository currencyNameRepository, CurrencyRepository currencyRepository) {
        this.restService = restService;
        this.currencyNameRepository = currencyNameRepository;
        this.currencyRepository = currencyRepository;
    }

    @GetMapping("ping")
    public String ping() {
        return "OK";
    }

    @GetMapping("api")
    public ResponseEntity callApi() throws IOException {
        return ResponseEntity.ok(restService.callApi("https://api.coindesk.com/v1/bpi/currentprice.json"));
    }

    @GetMapping
    public ResponseEntity<Currency> findByEname(@RequestParam("currency") CurrencyNameModel request) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(
                currencyRepository.findByCurrencyNameEname(request)
                        .orElseThrow(() -> new ChangeSetPersister.NotFoundException())
        );
    }

    @PostMapping
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) {
        return ResponseEntity.ok(currencyRepository.save(currency));
    }

    @PutMapping
    public ResponseEntity<Currency> updateCurrency(@RequestBody Currency currency) {
        return ResponseEntity.ok(currencyRepository.save(currency));
    }

    @DeleteMapping
    public ResponseEntity deleteCurrency(@RequestBody Currency currency) {
        currencyRepository.delete(currency);
        return ResponseEntity.noContent().build();
    }

}
