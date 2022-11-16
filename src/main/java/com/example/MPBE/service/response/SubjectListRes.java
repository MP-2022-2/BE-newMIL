package com.example.MPBE.service.response;

import com.example.MPBE.service.dto.SubjectDto;
import lombok.Getter;

import java.util.List;


@Getter
public class SubjectListRes extends BaseResponse{
    private List<SubjectDto> majorSubjectList;
    private List<SubjectDto> nonMajorSubjectList;
    private Integer majorTotal;
    private Integer nonMajorTotal;

    public SubjectListRes(String msg, Integer status, List<SubjectDto> majorSubjectDtoList, List<SubjectDto> nonMajorSubjectDtoList) {
        super(msg, status);
        this.majorSubjectList = majorSubjectDtoList;
        this.nonMajorSubjectList = nonMajorSubjectDtoList;
        this.majorTotal = sumOfGpa(majorSubjectDtoList);
        this.nonMajorTotal = sumOfGpa(nonMajorSubjectDtoList);
    }

    private Integer sumOfGpa(List<SubjectDto> subjectDtoList){
        Integer sum = 0;
        for(SubjectDto subjectDto : subjectDtoList){
            sum+=subjectDto.getGpa();
        }
        return sum;
    }
}
