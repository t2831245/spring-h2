package com.demo.service;

import com.demo.entity.Currency;
import com.demo.entity.model.CoinDeskModel;
import com.demo.repository.CurrencyRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestService {

    private final CurrencyRepository currencyRepository;

    public RestService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public CoinDeskModel callApi(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String response
                = restTemplate.getForObject(url, String.class);

        Gson g = new Gson();
        CoinDeskModel coinDeskModel = g.fromJson(response, CoinDeskModel.class);
        return coinDeskModel;
    }

    public List<Currency> findAll(){
        return currencyRepository.findAll();
    }

}
