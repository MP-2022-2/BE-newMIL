package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.dto.CommentDto;
import com.example.MPBE.service.dto.InfoDto;
import com.example.MPBE.service.dto.PostDto;
import com.example.MPBE.service.dto.TokenDto;
import com.example.MPBE.service.request.*;
import com.example.MPBE.service.response.*;
import com.example.MPBE.service.service.CertificationService;
import com.example.MPBE.service.service.MailService;
import com.example.MPBE.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

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
        if(userService.isExistNickName(signUpReq.getNickName()))
            return ResponseEntity.status(409).body(new BaseResponse("이미 존재하는 닉네임 입니다.", 409));
        if(!userService.isMediaStudent(signUpReq.getName(), signUpReq.getStudentId()))
            return ResponseEntity.status(401).body(new BaseResponse("미디어학과 학생이 아닙니다.", 401));

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

        InfoDto infoDto = userService.getLoginInfo(loginReq.getUserId());

        return ResponseEntity.status(200).body(new LoginRes("로그인에 성공했습니다.",200, infoDto, tokenDto.getAccessToken(),tokenDto.getRefreshToken()));
    }

    @GetMapping("/mine")
    public ResponseEntity<? extends BaseResponse> getInfo() {
        InfoDto infoDto = userService.getMyInfo();
        return ResponseEntity.status(200).body(new MyInfoRes("유저 정보를 가져왔습니다.", 200, infoDto));
    }

    @PutMapping("/mine/nick-name")
    public ResponseEntity<? extends BaseResponse> nickNameVerification(@Valid @RequestBody NickNameChangingReq nickNameChangingReq){
        if(userService.isExistNickName(nickNameChangingReq.getNickName()))
            return ResponseEntity.status(409).body(new BaseResponse("이미 존재하는 닉네임 입니다.", 409));
        userService.updateNickName(nickNameChangingReq.getNickName());
        return ResponseEntity.status(200).body(new BaseResponse("닉네임 변경에 성공했습니다.",200));
    }

    @PutMapping("/mine/newinfo")
    public ResponseEntity<? extends BaseResponse> updateInfo(@RequestBody UpdateMyInfoReq updateMyInfoReq) {
        userService.update(updateMyInfoReq);
        return ResponseEntity.status(201).body(new BaseResponse("수정이 완료되었습니다.", 201));
    }

    @GetMapping("/mine/my-post")
    public ResponseEntity<? extends BaseResponse> getMyPost(@Valid Pageable pageable){
        List<PostDto> postDtoList = userService.getMyPosts(pageable);
        return ResponseEntity.status(200).body(new PostListRes("내가 쓴 글 목록 조회에 성공했습니다.",200,postDtoList));
    }

    @GetMapping("/mine/my-comment")
    public ResponseEntity<? extends BaseResponse> getMyComment(@Valid Pageable pageable){
        List<CommentDto> commentDtoList = userService.getMyComments(pageable);
        return ResponseEntity.status(200).body(new CommentListRes("내가 쓴 댓글 목록 조회에 성공했습니다.",200,commentDtoList));
    }
}