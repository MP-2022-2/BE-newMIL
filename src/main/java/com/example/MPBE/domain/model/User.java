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
    List<Subject> subjectList;
    public void addSubject(Subject subject) {
        if (this.subjectList == null) {
            this.subjectList = new LinkedList<>();
        }
        this.subjectList.add(subject);
        subject.setUser(this);
    }

    public void updateMyInfo(String company, Track track){
        this.company = company;
        this.track = track;
    }
    // Todo : User Password 암호화 메소드 추가
}
