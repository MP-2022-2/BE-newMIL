package com.example.MPBE.domain.model;

import com.example.MPBE.util.enums.BoardType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseModel{
    @NotNull
    BoardType boardType;

    @NotNull
    String title;

    @NotNull
    String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Comment> commentList;
    public void addComment(Comment comment) {
        if (this.commentList == null) {
            this.commentList = new LinkedList<>();
        }
        this.commentList.add(comment);
        comment.setPost(this);
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Like> likeList;
    public void addLike(Like like){
        if (this.likeList == null) {
            this.likeList = new LinkedList<>();
        }
        this.likeList.add(like);
        like.setPost(this);
    }
}
