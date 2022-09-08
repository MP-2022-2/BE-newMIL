package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.request.SignUpReq;
import com.example.MPBE.service.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @PostMapping("/signup")
    public ResponseEntity<? extends BaseResponse> addUser(@Valid @RequestBody SignUpReq signUpReq){

        return ResponseEntity.status(201).body(new BaseResponse("회원가입이 완료되었습니다.",201));
    }
}