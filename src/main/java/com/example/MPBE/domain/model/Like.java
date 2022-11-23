package com.example.MPBE.domain.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Like extends BaseModel{
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    Post post;
}
