package com.exchage.rate.service.impl;

import com.exchage.rate.dto.ApiLayerDto;
import com.exchage.rate.service.RateAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class RateAPIServiceImpl implements RateAPIService {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${currencylayer.api.url}")
    private String url;
    @Value("${currencylayer.api.access_key}")
    private String accessKey;

    /**
     * 특정 통화 환율만 조회
     * @param currencies
     * @param source
     * @return ApiLayerDto
     */
    @Override
    public BigDecimal getSelectRate(String currencies, String source) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("access_key", accessKey)
                .queryParam("currencies", currencies)
                .queryParam("source", source);

        ApiLayerDto apiLayerDto = restTemplate.getForObject(builder.toUriString(), ApiLayerDto.class);
        BigDecimal rate = apiLayerDto.getQuotes().get(source + currencies);
        return rate.setScale(2, RoundingMode.FLOOR);
    }

    /**
     * 모든 통화 환율 조회
     * TODO : 차후 환율 API 배치로 돌려 지정 환율 디비에서 관리 필요 (매번 호출시 비용 및 공격 문제...)
     * @return ApiLayerDto
     */
    @Override
    public ApiLayerDto getAllRate() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("access_key", accessKey);

        ApiLayerDto apiLayerDto = restTemplate.getForObject(builder.toUriString(), ApiLayerDto.class);
        return apiLayerDto;
    }
}
