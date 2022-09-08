package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.request.SignUpReq;
import com.example.MPBE.service.response.BaseResponse;
import com.example.MPBE.service.service.UserService;
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
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<? extends BaseResponse> addUser(@Valid @RequestBody SignUpReq signUpReq){
        if(userService.isExistUserId(signUpReq.getUserId()))
            return ResponseEntity.status(409).body(new BaseResponse("이미 존재하는 ID 입니다.", 409));
        if(userService.isExistEmail(signUpReq.getEmail()))
            return ResponseEntity.status(409).body(new BaseResponse("이미 존재하는 Email 입니다.", 409));

        // Todo : 이름 학번 매칭 확인 과정, 이메일 인증 과정 추가

        userService.save(signUpReq);
        return ResponseEntity.status(201).body(new BaseResponse("회원가입이 완료되었습니다.",201));
    }
}