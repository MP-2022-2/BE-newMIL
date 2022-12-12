package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.response.BaseResponse;
import com.example.MPBE.service.response.PostListRes;
import com.example.MPBE.service.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainBoardController {
    private final BoardService boardService;

    @GetMapping("/top5")
    public ResponseEntity<? extends BaseResponse> getTop5(){
        return ResponseEntity.status(200).body(new PostListRes("최신글 목록 조회 완료",200, boardService.getTop5()));
    }

    @GetMapping("/hot5")
    public ResponseEntity<? extends BaseResponse> getHot5(){
        return ResponseEntity.status(200).body(new PostListRes("인기글 목록 조회 완료",200, boardService.getHot5()));
    }
}
