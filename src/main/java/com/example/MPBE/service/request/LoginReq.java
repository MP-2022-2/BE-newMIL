package com.example.MPBE.service.request;

import com.sun.istack.NotNull;
import lombok.Getter;

@Getter
public class LoginReq {
    @NotNull
    String userId;

    @NotNull
    String password;
}
