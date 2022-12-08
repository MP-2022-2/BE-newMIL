package com.example.MPBE.service.response;

import com.example.MPBE.service.dto.PostDto;
import lombok.Getter;


@Getter
public class PostRes extends BaseResponse{
    PostDto postDto;
    Boolean isLikedPost;

    public PostRes(String msg, Integer status, PostDto postDto, Boolean isLikedPost){
        super(msg,status);
        this.postDto=postDto;
        this.isLikedPost=isLikedPost;
    }
}
