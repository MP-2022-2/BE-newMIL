package com.example.MPBE.service.request;

import com.example.MPBE.util.enums.Identity;
import com.example.MPBE.domain.model.User;
import com.example.MPBE.util.enums.Track;
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
    String nickName;

    @NotNull
    String email;

    @NotNull
    String password;

    @NotNull
    Integer studentId;

    @NotNull
    Identity identity;

    String company;

    @NotNull
    Track track;

    public void setPassword(String password){
        this.password = password;
    }

    public User toUserModel() {
        return User.builder()
                .name(name)
                .nickName(nickName)
                .email(email)
                .password(password)
                .identity(identity)
                .studentId(studentId)
                .userId(userId)
                .company(company)
                .track(track)
                .build();
    }
}
