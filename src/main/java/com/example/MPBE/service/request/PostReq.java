package com.example.MPBE.service.request;

import com.example.MPBE.domain.model.Post;
import com.example.MPBE.domain.model.Tag;
import com.example.MPBE.domain.model.User;
import com.example.MPBE.util.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReq {
    @NotNull
    BoardType boardType;

    @NotNull
    String title;

    @NotNull
    String content;

    List<String> tagList;

    // Todo : Tag 받아서 모델화 하는거
    public Post toModel(User user){
        Post post = Post.builder()
                .user(user)
                .title(this.title)
                .content(this.content)
                .boardType(this.boardType)
                .build();
        user.addBoard(post);
        return post;
    }
}
