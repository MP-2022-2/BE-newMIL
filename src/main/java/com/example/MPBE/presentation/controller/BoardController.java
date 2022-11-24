package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.request.PostReq;
import com.example.MPBE.service.response.BaseResponse;
import com.example.MPBE.service.response.PostListReq;
import com.example.MPBE.service.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/free")
    public ResponseEntity<? extends BaseResponse> addPost(@Valid @RequestBody PostReq postReq){
        boardService.addPost(postReq);
        return ResponseEntity.status(201).body(new BaseResponse("자유 게시판에 글이 등록되었습니다.",201));
    }

    @GetMapping("/free")
    public ResponseEntity<? extends BaseResponse> getPosts(@Valid Pageable pageable){
        return ResponseEntity.status(200).body(new PostListReq("글 목록 조회 완료",200,boardService.getFreeBoardAll(pageable)));
    }
}
