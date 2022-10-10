package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.dto.InfoDto;
import com.example.MPBE.service.dto.TokenDto;
import com.example.MPBE.service.request.*;
import com.example.MPBE.service.response.BaseResponse;
import com.example.MPBE.service.response.LoginRes;
import com.example.MPBE.service.response.MyInfoRes;
import com.example.MPBE.service.service.CertificationService;
import com.example.MPBE.service.service.MailService;
import com.example.MPBE.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final MailService mailService;
    private final CertificationService certificationService;

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
        String randomCode = certificationService.makeCertificationCode(emailCertificationReq.getEmail());
        mailService.sendSignUpCertificationMail(emailCertificationReq.getEmail(), randomCode);
        return ResponseEntity.status(201).body(new BaseResponse("인증코드 발송을 완료했습니다.", 201));
    }

    @PostMapping("/verification")
    public ResponseEntity<? extends BaseResponse> verifyEmailCertification(@RequestBody EmailVerificationReq emailVerificationReq) {
        if(certificationService.verifyCertificationCode(emailVerificationReq.getEmail(),emailVerificationReq.getRandomCode()))
            return ResponseEntity.status(200).body(new BaseResponse("인증에 성공했습니다.", 200));
        return ResponseEntity.status(400).body(new BaseResponse("인증을 실패했습니다.", 400));
    }

    @PostMapping("/login")
    public ResponseEntity<? extends BaseResponse> loginUser(@Valid @RequestBody LoginReq loginReq) {
        if(!userService.isExistUserId(loginReq.getUserId()))
            return ResponseEntity.status(400).body(new BaseResponse("존재하지 않는 ID 입니다.", 400));

        if(!userService.matchPassword(loginReq.getUserId(),loginReq.getPassword()))
            return ResponseEntity.status(400).body(new BaseResponse("패스워드가 틀렸습니다.", 400));

        TokenDto tokenDto = userService.createToken(loginReq);

        return ResponseEntity.status(200).body(new LoginRes("로그인에 성공했습니다.",200, tokenDto.getAccessToken(),tokenDto.getRefreshToken()));
    }

    @GetMapping("/mine")
    public ResponseEntity<? extends BaseResponse> getInfo() {
        InfoDto infoDto = userService.getMyInfo();
        return ResponseEntity.status(200).body(new MyInfoRes("유저 정보를 가져왔습니다.", 200, infoDto));
    }

    @PutMapping("/mine/newinfo")
    public ResponseEntity<? extends BaseResponse> updateInfo(@RequestBody UpdateMyInfoReq updateMyInfoReq) {
        userService.update(updateMyInfoReq);
        return ResponseEntity.status(201).body(new BaseResponse("수정이 완료되었습니다.", 201));
    }
}