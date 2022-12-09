package com.example.MPBE.service.dto;

import com.example.MPBE.domain.model.Comment;
import com.example.MPBE.util.enums.BoardType;
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

    @NotNull
    Long postId;

    @NotNull
    BoardType boardType;

    public CommentDto(Comment comment){
        this.text = comment.getText();
        this.boardType = comment.getPost().getBoardType();
        this.createdAt = comment.getCreatedAt();
        this.nickName = comment.getUser().getNickName();
        this.postId = comment.getPost().getId();
    }
}
