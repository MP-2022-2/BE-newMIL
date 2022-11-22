package com.example.MPBE.service.request;

import com.example.MPBE.service.dto.SubjectDto;
import lombok.Getter;

import java.util.List;

@Getter
public class SubjectRegistrationReq {

    Integer studentId;

    List<SubjectDto> subjectList;
}
