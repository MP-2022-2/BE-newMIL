package com.example.MPBE.service.response;

import com.example.MPBE.service.dto.InfoDto;
import com.example.MPBE.util.enums.Track;
import lombok.Getter;

@Getter
public class MyInfoRes extends BaseResponse {

    private String name;
    private Integer studentId;
    private String email;
    private Track track;
    private String company;

    public MyInfoRes(String msg, Integer status, InfoDto infoDto) {
        super(msg, status);
        this.name = infoDto.getName();
        this.studentId = infoDto.getStudentId();
        this.email = infoDto.getEmail();
        this.track = infoDto.getTrack();
        this.company = infoDto.getCompany();
    }
}