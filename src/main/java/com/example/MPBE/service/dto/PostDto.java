package com.example.MPBE.service.dto;

import com.example.MPBE.domain.model.Post;
import com.example.MPBE.util.enums.BoardType;
import com.sun.istack.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDto {
    @NotNull
    Long id;

    @NotNull
    String title;

    @NotNull
    String content;

    @NotNull
    LocalDateTime createdAt;

    @NotNull
    String nickname;

    @NotNull
    Integer like;

    @NotNull
    Integer comment;

    @NotNull
    BoardType boardType;

    List<CommentDto> commentDtoList;

    List<String> tagList;

    public PostDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.nickname = post.getUser().getNickName();
        this.like = post.getPostLikeList().size();
        this.comment = post.getCommentList().size();
        this.boardType = post.getBoardType();
        this.commentDtoList = post.getCommentList().stream().map(e -> new CommentDto(e)).collect(Collectors.toList());
        this.tagList = post.getTagList().stream().map(e ->e.getTag()).collect(Collectors.toList());
    }
}
