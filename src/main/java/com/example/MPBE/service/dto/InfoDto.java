package com.example.MPBE.service.dto;

import com.example.MPBE.util.enums.Track;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InfoDto {
    private String name;
    private Integer studentId;
    private String email;
    private Track track;
    private String company;
}
