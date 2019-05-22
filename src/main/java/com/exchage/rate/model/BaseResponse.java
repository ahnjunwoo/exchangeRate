package com.exchage.rate.model;

import lombok.Data;

@Data
public class BaseResponse {
    private String code;
    private String msg;
    private Object data;
}
