package com.example.MPBE.service.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SubjectRemovalReq {
    @NotNull
    String subject;
}
