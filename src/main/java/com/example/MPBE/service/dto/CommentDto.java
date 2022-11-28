package com.example.MPBE.service.dto;

import com.example.MPBE.domain.model.Comment;
import com.sun.istack.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
public class CommentDto {
    @NotNull
    String text;

    @NotNull
    Date createdAt;

    @NotNull
    String nickName;

    public CommentDto(Comment comment){
        this.text = comment.getText();
        this.createdAt = comment.getCreatedAt();
        this.nickName = comment.getUser().getNickName();
    }
}
