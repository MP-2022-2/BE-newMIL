package com.example.MPBE.service.response;

import com.example.MPBE.service.dto.SubjectDto;
import lombok.Getter;
import java.util.List;


@Getter
public class SubjectListRes extends BaseResponse{
    private List<SubjectDto> subjectList;

    public SubjectListRes(String msg, Integer status, List<SubjectDto> subjectDtoList) {
        super(msg, status);
        this.subjectList = subjectDtoList;
    }
}
