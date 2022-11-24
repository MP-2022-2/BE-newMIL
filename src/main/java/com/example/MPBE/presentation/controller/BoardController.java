package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.dto.PostDto;
import com.example.MPBE.service.request.CommentReq;
import com.example.MPBE.service.request.PostReq;
import com.example.MPBE.service.response.BaseResponse;
import com.example.MPBE.service.response.PostListReq;
import com.example.MPBE.service.response.PostRes;
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

    @PostMapping("/free/{id}/comment")
    public ResponseEntity<? extends BaseResponse> addComment(@Valid @PathVariable(value = "id") Long postId,
                                                             @RequestBody CommentReq commentReq){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));
        boardService.addComment(postId, commentReq);
        return ResponseEntity.status(201).body(new BaseResponse("댓글 작성 완료.",201));
    }

    @GetMapping("/free/{id}")
    public ResponseEntity<? extends BaseResponse> getPost(@Valid @PathVariable(value = "id") Long postId){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));
        PostDto postDto = boardService.getPost(postId);
        return ResponseEntity.status(200).body(new PostRes("게시글 조회에 성공했습니다.",200,postDto));
    }
}
