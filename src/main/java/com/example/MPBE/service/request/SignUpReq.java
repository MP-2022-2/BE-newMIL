package com.example.MPBE.service.request;

import com.example.MPBE.domain.model.Identity;
import com.example.MPBE.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpReq {
    @NotNull
    String userId;

    @NotNull
    String name;

    @NotNull
    String email;

    @NotNull
    String password;

    @NotNull
    Integer studentId;

    @NotNull
    Identity identity;

    String company;

    public User toUserModel() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .identity(identity)
                .studentId(studentId)
                .userId(userId)
                .company(company)
                .build();
    }
}
