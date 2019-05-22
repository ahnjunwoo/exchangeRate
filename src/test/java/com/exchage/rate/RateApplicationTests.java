package com.exchage.rate;

import com.exchage.rate.dto.ApiLayerDto;
import com.exchage.rate.service.RateAPIService;
import com.exchage.rate.service.RateCoreService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RateApplicationTests {
    @Autowired
    RateAPIService rateAPIService;
    @Autowired
    RateCoreService rateCoreService;
    /**
     * 외부 api이용하여 모든 통화 환율 가져오기
     */
    @Test
    public void getRate() {
        ApiLayerDto apiLayerDto = rateAPIService.getAllRate();
        log.info("result: {}",apiLayerDto);
        assertThat(apiLayerDto.isSuccess()).isTrue();
    }

    /**
     * 특정 통화 환율 가져오기
     */
    @Test
    public void getSelectRate() {
        String source = "USD";
        String currencies = "KRW";
        BigDecimal rate = rateAPIService.getSelectRate(currencies,source);
        log.info("rate: {}",rate);
        assertThat(rate).isNotNull();
    }

    @Test
    public void getReceivingAmount() {
        BigDecimal transmissionAmount = new BigDecimal(10);
        BigDecimal exchangeRate = new BigDecimal(52.44);
        BigDecimal rate = rateCoreService.getReceivingAmount(transmissionAmount,exchangeRate);
        log.info("amt: {}",rate);
        assertThat(rate).isNotNull();
    }
}
