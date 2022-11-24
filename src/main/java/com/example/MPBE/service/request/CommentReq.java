package com.example.MPBE.service.request;

import com.example.MPBE.domain.model.Comment;
import com.example.MPBE.domain.model.Post;
import com.example.MPBE.domain.model.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentReq {
    @NotNull
    String text;

    public Comment toModel(User user, Post post){
        Comment comment = Comment.builder()
                            .user(user)
                            .post(post)
                            .text(this.text)
                            .build();
        user.addComment(comment);
        post.addComment(comment);
        return comment;
    }
}
