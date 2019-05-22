package com.exchage.rate.controller;

import com.exchage.rate.dto.ApiLayerDto;
import com.exchage.rate.model.BaseResponse;
import com.exchage.rate.service.RateAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * author : codegun
 * desc : 환율관련 정보 api
 */
@RestController
@RequestMapping("/api/exchangeRate")
@Slf4j
public class RateAPIController {
    @Autowired
    private RateAPIService rateAPIService;


    @GetMapping("/{source}/{currencies}")
    public ResponseEntity<BigDecimal> getSelectRate(@PathVariable String currencies,@PathVariable String source) {
        BigDecimal rate = rateAPIService.getSelectRate(currencies,source);
        return new ResponseEntity<>(rate,HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<ApiLayerDto> getAllRate() {
        ApiLayerDto apiLayerDto = rateAPIService.getAllRate();
        return new ResponseEntity<>(apiLayerDto,HttpStatus.OK);
    }
}
