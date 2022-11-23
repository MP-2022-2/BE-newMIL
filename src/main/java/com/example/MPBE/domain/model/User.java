package com.example.MPBE.domain.model;

import com.example.MPBE.util.enums.Identity;
import com.example.MPBE.util.enums.Track;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel{

    @NotNull
    @Column(unique = true)
    String userId;

    @NotNull
    String name;

    @NotNull
    @Column(unique = true)
    String email;

    @NotNull
    String password;

    @NotNull
    @Column(unique = true)
    Integer studentId;

    @NotNull
    Identity identity;

    String company;

    @NotNull
    Track track;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<MajorSubject> majorSubjectList;
    public void addMajorSubject(MajorSubject majorSubject) {
        if (this.majorSubjectList == null) {
            this.majorSubjectList = new LinkedList<>();
        }
        this.majorSubjectList.add(majorSubject);
        majorSubject.setUser(this);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<NonMajorSubject> nonMajorSubjectList;
    public void addNonMajorSubject(NonMajorSubject nonMajorSubject) {
        if (this.nonMajorSubjectList == null) {
            this.nonMajorSubjectList = new LinkedList<>();
        }
        this.nonMajorSubjectList.add(nonMajorSubject);
        nonMajorSubject.setUser(this);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> postList;
    public void addBoard(Post post) {
        if(this.postList == null) {
            this.postList = new LinkedList<>();
        }
        this.postList.add(post);
        post.setUser(this);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> likeList;
    public void addLike(Like like) {
        if(this.likeList == null) {
            this.likeList = new LinkedList<>();
        }
        this.likeList.add(like.getPost());
        like.setUser(this);
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Comment> commentList;
    public void addComment(Comment comment) {
        if(this.commentList == null) {
            this.commentList = new LinkedList<>();
        }
        this.commentList.add(comment);
        comment.setUser(this);
    }

    public void updateMyInfo(String company, Track track){
        this.company = company;
        this.track = track;
    }
    // Todo : User Password 암호화 메소드 추가
}
