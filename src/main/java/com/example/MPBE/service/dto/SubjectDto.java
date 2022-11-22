package com.example.MPBE.service.dto;

import com.example.MPBE.domain.model.MajorSubject;
import com.example.MPBE.domain.model.NonMajorSubject;
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

    Integer gpa;

    public MajorSubject toMajorModel(User user){
        MajorSubject majorSubject = MajorSubject.builder()
                .user(user)
                .subject(this.subject)
                .gpa(this.gpa)
                .build();
        user.addMajorSubject(majorSubject);
        return majorSubject;
    }
    public NonMajorSubject toNonMajorModel(User user){
        NonMajorSubject nonMajorSubject = NonMajorSubject.builder()
                .user(user)
                .subject(this.subject)
                .gpa(this.gpa)
                .build();
        user.addNonMajorSubject(nonMajorSubject);
        return nonMajorSubject;
    }
    public SubjectDto(MajorSubject majorSubject){
        this.subject = majorSubject.getSubject();
        this.gpa = majorSubject.getGpa();
    }

    public SubjectDto(NonMajorSubject nonMajorSubject){
        this.subject = nonMajorSubject.getSubject();
        this.gpa = nonMajorSubject.getGpa();
    }
}
