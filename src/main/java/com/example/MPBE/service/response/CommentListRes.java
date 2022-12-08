package com.example.MPBE.service.response;

import com.example.MPBE.service.dto.CommentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentListRes extends BaseResponse{
    List<CommentDto> commentDtoList;

    public CommentListRes(String msg, Integer status, List<CommentDto> commentDtoList){
        super(msg, status);
        this.commentDtoList = commentDtoList;
    }
}
