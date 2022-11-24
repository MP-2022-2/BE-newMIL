package com.example.MPBE.service.response;

import com.example.MPBE.service.dto.PostDto;
import lombok.Getter;


@Getter
public class PostRes extends BaseResponse{
    PostDto postDto;

    public PostRes(String msg, Integer status, PostDto postDto){
        super(msg,status);
        this.postDto=postDto;
    }
}
