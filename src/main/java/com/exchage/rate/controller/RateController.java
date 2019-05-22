package com.exchage.rate.controller;

import com.exchage.rate.service.RateAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

/**
 * 단순 뷰 이동 컨트롤러
 */
@Controller
public class RateController {
    @Autowired
    private RateAPIService rateAPIService;
    @GetMapping("")
    public String exchangeRateView(Model model){
        BigDecimal rate = rateAPIService.getSelectRate("KRW", "USD");
        model.addAttribute("rate",rate);
        return "exchangeRateView";
    }
}
