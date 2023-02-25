package com.demo.service;

import com.demo.entity.Currency;
import com.demo.entity.CurrencyConverter;
import com.demo.entity.model.CoinDeskModel;
import com.demo.repository.CurrencyRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RestService {

    private final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

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

        coinDeskModel.getTime().setUpdated(convertUpdatedFormat(coinDeskModel.getTime().getUpdated()));
        coinDeskModel.getTime().setUpdatedISO(convertUpdatedISOFormat(coinDeskModel.getTime().getUpdatedISO()));
        coinDeskModel.getTime().setUpdateduk(convertUpdatedukFormat(coinDeskModel.getTime().getUpdateduk()));

        return coinDeskModel;
    }

    public List<Currency> updateBpiData(String url) {
        CoinDeskModel coinDesk = callApi(url);

        if (coinDesk.getBpi() == null)
            throw new NullPointerException();

        List<Currency> updatedList = currencyRepository.findAll().stream().map(
                        x -> {
                            if (x.getCurrencyName().getEname().name().equals("USD"))
                                return CurrencyConverter.modelToEntity(coinDesk.getBpi().getUSD(), x);
                            if (x.getCurrencyName().getEname().name().equals("GBP"))
                                return CurrencyConverter.modelToEntity(coinDesk.getBpi().getGBP(), x);
                            if (x.getCurrencyName().getEname().name().equals("EUR"))
                                return CurrencyConverter.modelToEntity(coinDesk.getBpi().getEUR(), x);
                            return null;
                        }
                )
                .filter(x -> Objects.nonNull(x)).
                collect(Collectors.toList());
        currencyRepository.saveAll(updatedList);
        return currencyRepository.findAll();
    }

    private String convertUpdatedFormat(String input) {
        Map<Long, String> monthNameMap = new HashMap<>();
        monthNameMap.put(1L, "Jan");
        monthNameMap.put(2L, "Feb");
        monthNameMap.put(3L, "Mar");
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendText(ChronoField.MONTH_OF_YEAR, monthNameMap)
                .appendPattern(" dd, yyyy HH:mm:ss ")
                .appendZoneText(TextStyle.SHORT)
                .toFormatter();
        LocalDateTime dateTime = LocalDateTime.parse(input, fmt);
        return dateTime.format(CUSTOM_FORMATTER);
    }

    private String convertUpdatedISOFormat(String input) {
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .appendZoneText(TextStyle.FULL)
                .toFormatter();
        LocalDateTime dateTime = LocalDateTime.parse(input, fmt);
        return dateTime.format(CUSTOM_FORMATTER);
    }

    private String convertUpdatedukFormat(String input) {
        Map<Long, String> monthNameMap = new HashMap<>();
        monthNameMap.put(1L, "Jan");
        monthNameMap.put(2L, "Feb");
        monthNameMap.put(3L, "Mar");
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendText(ChronoField.MONTH_OF_YEAR, monthNameMap)
                .appendPattern(" dd, yyyy 'at' HH:mm ")
                .appendZoneText(TextStyle.SHORT)
                .toFormatter();
        LocalDateTime dateTime = LocalDateTime.parse(input, fmt);
        return dateTime.format(CUSTOM_FORMATTER);
    }
}
