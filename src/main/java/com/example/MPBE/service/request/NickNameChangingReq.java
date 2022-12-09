package com.example.MPBE.service.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class NickNameChangingReq {

    @NotNull
    @Size(min=1, max=10)
    String nickName;
}
