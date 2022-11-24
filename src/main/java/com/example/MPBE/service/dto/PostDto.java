package com.example.MPBE.service.dto;

import com.example.MPBE.domain.model.Post;
import com.sun.istack.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
public class PostDto {
    @NotNull
    Long id;

    @NotNull
    String title;

    @NotNull
    String content;

    @NotNull
    Date createdAt;

    @NotNull
    String nickname;

    @NotNull
    Integer like;

    @NotNull
    Integer comment;

    public PostDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.nickname = post.getUser().getNickName();
        this.like = post.getPostLikeList().size();
        this.comment = post.getCommentList().size();
    }
}
