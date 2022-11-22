package com.example.MPBE.domain.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseModel{
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

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    List<Comment> commentList;
    public void addComment(Comment comment) {
        if (this.commentList == null) {
            this.commentList = new LinkedList<>();
        }
        this.commentList.add(comment);
        comment.setBoard(this);
    }

    @OneToMany(mappedBy = "like", cascade = CascadeType.ALL)
    List<Like> likeList;
    public void addLike(Like like){
        if (this.likeList == null) {
            this.likeList = new LinkedList<>();
        }
        this.likeList.add(like);
        like.setBoard(this);
    }
}
