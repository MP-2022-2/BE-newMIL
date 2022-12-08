package com.example.MPBE.service.dto;

import com.example.MPBE.domain.model.User;
import com.example.MPBE.util.enums.Identity;
import com.example.MPBE.util.enums.Track;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InfoDto {
    private String name;
    private Integer studentId;
    private String nickName;
    private String email;
    private Track track;
    private String company;
    private Identity identity;

    public InfoDto(User user){
        this.name = user.getName();
        this.nickName = user.getNickName();
        this.studentId = user.getStudentId();
        this.email = user.getEmail();
        this.track = user.getTrack();
        this.company = user.getCompany();
        this.identity = user.getIdentity();
    }
}
