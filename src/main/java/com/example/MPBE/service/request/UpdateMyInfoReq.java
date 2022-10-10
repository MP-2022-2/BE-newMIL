package com.example.MPBE.service.request;
import com.example.MPBE.util.enums.Track;
import lombok.Getter;

@Getter
public class UpdateMyInfoReq {
    private Track track;
    private String company;
}
