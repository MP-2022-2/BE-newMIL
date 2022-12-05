package com.example.MPBE.domain.model;

import com.example.MPBE.util.enums.BoardType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseModel{
    @NotNull
    String tag;

    @com.sun.istack.NotNull
    @Enumerated(EnumType.STRING)
    BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    Post post;
}
