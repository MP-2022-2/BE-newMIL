package com.example.MPBE.domain.model;

import com.example.MPBE.util.enums.BoardType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseModel{
    @NotNull
    @Enumerated(EnumType.STRING)
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
    List<Comment> commentList = new LinkedList<>();
    public void addComment(Comment comment) {
        if (this.commentList == null) {
            this.commentList = new LinkedList<>();
        }
        this.commentList.add(comment);
        comment.setPost(this);
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<PostLike> postLikeList = new LinkedList<>();
    public void addLike(PostLike postLike){
        if (this.postLikeList == null) {
            this.postLikeList = new LinkedList<>();
        }
        this.postLikeList.add(postLike);
        postLike.setPost(this);
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Tag> tagList = new LinkedList<>();
    public void addTag(Tag tag){
        if (this.tagList == null) {
            this.tagList = new LinkedList<>();
        }
        this.tagList.add(tag);
        tag.setPost(this);
    }

    public List<Post> hot5(List<Post> postList){
        Collections.sort(postList,(p1,p2)->p1.getPostLikeList().size()-p2.getPostLikeList().size());
        List<Post> hot5 = new LinkedList<>();
        if(postList.size()<5){
            for(Post p : postList)
                hot5.add(p);
            return hot5;
        }
        for(int i = 0; i < 5; i++){
            hot5.add(postList.get(i));
        }
        return hot5;
    }
}
