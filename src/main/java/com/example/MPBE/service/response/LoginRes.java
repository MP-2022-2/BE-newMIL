package com.example.MPBE.service.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRes extends BaseResponse{
    String accessToken;
    String refreshToken;

    public LoginRes(String msg, Integer status, String accessToken, String refreshToken){
        super(msg, status);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
