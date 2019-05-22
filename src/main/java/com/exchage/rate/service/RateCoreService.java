package com.exchage.rate.service;

import com.exchage.rate.dto.ApiLayerDto;

import java.math.BigDecimal;

public interface RateCoreService {
    BigDecimal getReceivingAmount(BigDecimal transmissionAmount, BigDecimal exchangeRate);

}
