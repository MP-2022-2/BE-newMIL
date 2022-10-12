package com.example.MPBE.service.response;

import com.example.MPBE.service.dto.InfoDto;
import com.example.MPBE.util.enums.Track;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRes extends BaseResponse{
    String name;
    Integer student_id;
    Track track;
    String accessToken;
    String refreshToken;

    public LoginRes(String msg, Integer status, InfoDto infoDto, String accessToken, String refreshToken){
        super(msg, status);
        this.name = infoDto.getName();
        this.student_id = infoDto.getStudentId();
        this.track = infoDto.getTrack();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
