package com.demo;

import com.demo.entity.CurrencyName;
import com.demo.entity.model.CurrencyNameModel;
import com.demo.repository.CurrencyNameRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockTest {

    @Mock
    private CurrencyNameRepository currencyNameRepository;

    private CurrencyName currencyName = CurrencyName
            .builder()
            .cname("新台幣")
            .ename(CurrencyNameModel.TWD)
            .build();
    private CurrencyName tt = CurrencyName
            .builder()
            .cname("新台幣3")
            .ename(CurrencyNameModel.TWD)
            .build();

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        when(currencyNameRepository.save(currencyName)).thenReturn(tt);
    }

    @Test
    void gt(){
        System.out.println("yy - " + currencyNameRepository.save(currencyName).getCname());
        List<String> mockedList = mock(ArrayList.class);
        mockedList.add("1");
        mockedList.add("1");
        verify(mockedList, times(2)).add("1");
    }
}
