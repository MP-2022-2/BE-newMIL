package com.example.MPBE.domain.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseModel{
    @NotNull
    String text;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    Board board;

}
