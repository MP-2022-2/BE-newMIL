package com.example.MPBE.service.request;

import com.example.MPBE.util.Identity;
import com.example.MPBE.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    public void setPassword(String password){
        this.password = password;
    }

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
