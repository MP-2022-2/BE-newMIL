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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@PreAuthorize("hasRole('ROLE_GRADUATE')")
public class GraduateBoardController{
    private final BoardService boardService;

    @PostMapping("/graduate")
    public ResponseEntity<? extends BaseResponse> addGraduateBoardPost(@Valid @RequestBody PostReq postReq){
        boardService.addPost(postReq);
        return ResponseEntity.status(201).body(new BaseResponse("졸업생 게시판에 글이 등록되었습니다.",201));
    }

    @GetMapping("/graduate")
    public ResponseEntity<? extends BaseResponse> getGraduateBoardPosts(@Valid Pageable pageable){
        return ResponseEntity.status(200).body(new PostListReq("글 목록 조회 완료",200,boardService.getGraduateBoardAll(pageable)));
    }

    @GetMapping("/graduate/{id}")
    public ResponseEntity<? extends BaseResponse> getGraduateBoardPost(@Valid @PathVariable(value = "id") Long postId){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));
        if(!boardService.postType(postId).equals("GRADUATE"))
            return ResponseEntity.status(400).body(new BaseResponse("졸업생 게시판의 글이 아닙니다.",400));
        PostDto postDto = boardService.getPost(postId);
        return ResponseEntity.status(200).body(new PostRes("게시글 조회에 성공했습니다.",200,postDto));
    }

    @PostMapping("/graduate/{id}/comment")
    public ResponseEntity<? extends BaseResponse> addGraduateBoardComment(@Valid @PathVariable(value = "id") Long postId,
                                                                          @RequestBody CommentReq commentReq){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));
        if(!boardService.postType(postId).equals("GRADUATE"))
            return ResponseEntity.status(400).body(new BaseResponse("졸업생 게시판의 글이 아닙니다.",400));
        boardService.addComment(postId, commentReq);
        return ResponseEntity.status(201).body(new BaseResponse("댓글 작성 완료.",201));
    }

    @PostMapping("/graduate/{id}/postlike")
    public ResponseEntity<? extends BaseResponse> addOrSubPostLike(@Valid @PathVariable(value = "id") Long postId){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));

        if(!boardService.postType(postId).equals("GRADUATE"))
            return ResponseEntity.status(400).body(new BaseResponse("졸업생 게시판의 글이 아닙니다.",400));

        if(!boardService.addOrSubPostLike(postId))
            return ResponseEntity.status(200).body(new BaseResponse("좋아요가 취소됐습니다.",200));
        return ResponseEntity.status(201).body(new BaseResponse("좋아요 누르기가 성공했습니다.",201));
    }
}
