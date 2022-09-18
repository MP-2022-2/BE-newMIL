package com.example.MPBE.domain.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaStudent extends BaseModel{
    String name;

    Integer studentId;
}
