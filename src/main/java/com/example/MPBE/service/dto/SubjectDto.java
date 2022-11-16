package com.example.MPBE.service.dto;

import com.example.MPBE.domain.model.Subject;
import com.example.MPBE.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDto {
    String subject;

    Boolean isMajor;

    Integer gpa;

    public Subject toModel(User user){
        Subject subject = Subject.builder()
                .user(user)
                .subject(this.subject)
                .isMajor(this.isMajor)
                .gpa(this.gpa)
                .build();
        user.addSubject(subject);
        return subject;
    }

    public SubjectDto(Subject subject){
        this.subject = subject.getSubject();
        this.isMajor = subject.getIsMajor();
        this.gpa = subject.getGpa();
    }
}
