package com.example.MPBE.service.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    String msg = null;
    Integer status = null;
    public BaseResponse(String msg, Integer status){
        this.msg = msg;
        this.status = status;
    }
}
