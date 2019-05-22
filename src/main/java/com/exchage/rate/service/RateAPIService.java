package com.exchage.rate.service;

import com.exchage.rate.dto.ApiLayerDto;

import java.math.BigDecimal;

public interface RateAPIService {
    BigDecimal getSelectRate(String currencies, String source);
    ApiLayerDto getAllRate();
}
