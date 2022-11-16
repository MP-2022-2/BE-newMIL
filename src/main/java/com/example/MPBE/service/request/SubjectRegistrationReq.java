package com.example.MPBE.service.request;

import com.example.MPBE.service.dto.SubjectDto;
import com.sun.istack.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class SubjectRegistrationReq {
    @NotNull
    Integer studentId;

    List<SubjectDto> subjectList;
}
