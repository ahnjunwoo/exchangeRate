package com.exchage.rate.service.impl;

import com.exchage.rate.dto.ApiLayerDto;
import com.exchage.rate.service.RateAPIService;
import com.exchage.rate.service.RateCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class RateCoreServiceImpl implements RateCoreService {


    @Override
    public BigDecimal getReceivingAmount(BigDecimal transmissionAmount, BigDecimal exchangeRate) {
        BigDecimal result = transmissionAmount.multiply(exchangeRate);
        return result.setScale(2,RoundingMode.FLOOR);
    }
}
