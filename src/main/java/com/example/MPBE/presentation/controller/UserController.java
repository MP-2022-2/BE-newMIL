package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.request.EmailCertificationReq;
import com.example.MPBE.service.request.SignUpReq;
import com.example.MPBE.service.response.BaseResponse;
import com.example.MPBE.service.service.MailService;
import com.example.MPBE.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final MailService mailService;

    @PostMapping("/signup")
    public ResponseEntity<? extends BaseResponse> addUser(@Valid @RequestBody SignUpReq signUpReq){
        if(userService.isExistUserId(signUpReq.getUserId()))
            return ResponseEntity.status(409).body(new BaseResponse("이미 존재하는 ID 입니다.", 409));
        if(userService.isExistEmail(signUpReq.getEmail()))
            return ResponseEntity.status(409).body(new BaseResponse("이미 존재하는 Email 입니다.", 409));
        if(!userService.isMediaStudent(signUpReq.getName(), signUpReq.getStudentId()))
            return ResponseEntity.status(401).body(new BaseResponse("미디어학과 학생이 아닙니다.", 401));
        // Todo : 이메일 인증 과정 추가

        signUpReq.setPassword(userService.encryptPassword(signUpReq.getPassword()));
        userService.save(signUpReq);
        return ResponseEntity.status(201).body(new BaseResponse("회원가입이 완료되었습니다.",201));
    }

    @PostMapping("/certification")
    public ResponseEntity<? extends BaseResponse> sendEmailCertifiaction(@RequestBody EmailCertificationReq emailCertificationReq)
            throws MessagingException {
        String code = "12345678";
        mailService.sendSignUpCertificationMail(emailCertificationReq.getEmail(), code);
        return ResponseEntity.status(201).body(new BaseResponse("인증코드 발송을 완료했습니다.", 201));
    }
}