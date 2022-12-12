package com.example.MPBE.domain.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PostLike extends BaseModel{
    @CreatedDate
    LocalDateTime createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    Post post;
    public PostLike(User user, Post post){
        this.user = user;
        this.post = post;
    }
}
