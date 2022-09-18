package com.example.MPBE.domain.model;

import com.example.MPBE.util.Identity;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;



@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel{

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

    // Todo : User Password 암호화 메소드 추가
}
