package com.exchage.rate.controller;

import com.exchage.rate.enums.ResponseCode;
import com.exchage.rate.model.BaseResponse;
import com.exchage.rate.service.RateCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * author : codegun
 * desc : 환율 계산
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class RateCoreController {
    @Autowired
    private RateCoreService rateCoreService;


    @GetMapping("/receivingAmount")
    public ResponseEntity<BaseResponse> getReceivingAmount(BigDecimal transmissionAmount, BigDecimal exchangeRate) {
        BaseResponse baseResponse = new BaseResponse();
        BigDecimal compare = new BigDecimal(10000);
        BigDecimal zero = new BigDecimal(0);
        if(transmissionAmount.compareTo(zero) != 1){
            baseResponse.setCode(ResponseCode.FAIL.toString());
            baseResponse.setMsg("송금액이 바르지 않습니다");
            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        if(transmissionAmount.compareTo(compare) == 1){
            baseResponse.setCode(ResponseCode.FAIL.toString());
            baseResponse.setMsg("송금액이 바르지 않습니다");
            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        BigDecimal result = rateCoreService.getReceivingAmount(transmissionAmount,exchangeRate);
        baseResponse.setCode(ResponseCode.SUSUESS.toString());
        baseResponse.setData(result);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
