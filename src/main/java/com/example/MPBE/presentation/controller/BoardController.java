package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.dto.PostDto;
import com.example.MPBE.service.response.BaseResponse;
import com.example.MPBE.service.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/free")
    public ResponseEntity<? extends BaseResponse> addPost(@Valid @RequestBody PostDto postDto){
        boardService.addPost(postDto);
        return ResponseEntity.status(201).body(new BaseResponse("자유 게시판에 글이 등록되었습니다.",201));
    }
}
