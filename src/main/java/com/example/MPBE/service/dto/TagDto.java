package com.example.MPBE.service.dto;

import com.example.MPBE.domain.model.Tag;
import com.example.MPBE.util.enums.BoardType;
import lombok.Getter;

@Getter
public class TagDto {
    String tag;

    BoardType boardType;

    public TagDto(Tag tag){
        this.tag = tag.getTag();
        this.boardType = tag.getBoardType();
    }
}
