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
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/free")
    public ResponseEntity<? extends BaseResponse> addFreeBoardPost(@Valid @RequestBody PostReq postReq){
        boardService.addPost(postReq);
        return ResponseEntity.status(201).body(new BaseResponse("자유 게시판에 글이 등록되었습니다.",201));
    }

    @GetMapping("/free")
    public ResponseEntity<? extends BaseResponse> getFreeBoardPosts(@Valid Pageable pageable){
        return ResponseEntity.status(200).body(new PostListReq("글 목록 조회 완료",200,boardService.getFreeBoardAll(pageable)));
    }

    @GetMapping("/free/{id}")
    public ResponseEntity<? extends BaseResponse> getFreeBoardPost(@Valid @PathVariable(value = "id") Long postId){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));
        if(!boardService.postType(postId).equals("FREE"))
            return ResponseEntity.status(400).body(new BaseResponse("자유 게시판의 글이 아닙니다.",400));
        PostDto postDto = boardService.getPost(postId);
        return ResponseEntity.status(200).body(new PostRes("게시글 조회에 성공했습니다.",200,postDto));
    }

    @PostMapping("/free/{id}/comment")
    public ResponseEntity<? extends BaseResponse> addFreeBoardComment(@Valid @PathVariable(value = "id") Long postId,
                                                             @RequestBody CommentReq commentReq){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));
        if(!boardService.postType(postId).equals("FREE"))
            return ResponseEntity.status(400).body(new BaseResponse("자유 게시판의 글이 아닙니다.",400));
        boardService.addComment(postId, commentReq);
        return ResponseEntity.status(201).body(new BaseResponse("댓글 작성 완료.",201));
    }

    @PreAuthorize("hasRole('ROLE_GRADUATE')")
    @PostMapping("/graduate")
    public ResponseEntity<? extends BaseResponse> addGraduateBoardPost(@Valid @RequestBody PostReq postReq){
        boardService.addPost(postReq);
        return ResponseEntity.status(201).body(new BaseResponse("졸업생 게시판에 글이 등록되었습니다.",201));
    }

    @PreAuthorize("hasRole('ROLE_GRADUATE')")
    @GetMapping("/graduate")
    public ResponseEntity<? extends BaseResponse> getGraduateBoardPosts(@Valid Pageable pageable){
        return ResponseEntity.status(200).body(new PostListReq("글 목록 조회 완료",200,boardService.getFreeBoardAll(pageable)));
    }

    @PreAuthorize("hasRole('ROLE_GRADUATE')")
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

    @PreAuthorize("hasRole('ROLE_GRADUATE')")
    @GetMapping("/graduate/{id}")
    public ResponseEntity<? extends BaseResponse> getGraduateBoardPost(@Valid @PathVariable(value = "id") Long postId){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));
        if(!boardService.postType(postId).equals("GRADUATE"))
            return ResponseEntity.status(400).body(new BaseResponse("졸업생 게시판의 글이 아닙니다.",400));
        PostDto postDto = boardService.getPost(postId);
        return ResponseEntity.status(200).body(new PostRes("게시글 조회에 성공했습니다.",200,postDto));
    }

    @PreAuthorize("hasRole('ROLE_STUDNET')")
    @PostMapping("/student")
    public ResponseEntity<? extends BaseResponse> addStudentBoardPost(@Valid @RequestBody PostReq postReq){
        boardService.addPost(postReq);
        return ResponseEntity.status(201).body(new BaseResponse("재학생 게시판에 글이 등록되었습니다.",201));
    }

    @PreAuthorize("hasRole('ROLE_STUDNET')")
    @GetMapping("/student")
    public ResponseEntity<? extends BaseResponse> getStudentBoardPosts(@Valid Pageable pageable){
        return ResponseEntity.status(200).body(new PostListReq("글 목록 조회 완료",200,boardService.getFreeBoardAll(pageable)));
    }

    @PreAuthorize("hasRole('ROLE_STUDNET')")
    @PostMapping("/student/{id}/comment")
    public ResponseEntity<? extends BaseResponse> addStudentBoardComment(@Valid @PathVariable(value = "id") Long postId,
                                                                          @RequestBody CommentReq commentReq){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));
        if(!boardService.postType(postId).equals("STUDENT"))
            return ResponseEntity.status(400).body(new BaseResponse("재학생 게시판의 글이 아닙니다.",400));
        boardService.addComment(postId, commentReq);
        return ResponseEntity.status(201).body(new BaseResponse("댓글 작성 완료.",201));
    }

    @PreAuthorize("hasRole('ROLE_STUDNET')")
    @GetMapping("/student/{id}")
    public ResponseEntity<? extends BaseResponse> getStudentBoardPost(@Valid @PathVariable(value = "id") Long postId){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));
        if(!boardService.postType(postId).equals("STUDENT"))
            return ResponseEntity.status(400).body(new BaseResponse("재학생 게시판의 글이 아닙니다.",400));
        PostDto postDto = boardService.getPost(postId);
        return ResponseEntity.status(200).body(new PostRes("게시글 조회에 성공했습니다.",200,postDto));
    }

    @PostMapping("/{board-type}/{id}/postlike")
    public ResponseEntity<? extends BaseResponse> addOrSubPostLike(@Valid @PathVariable(value = "id") Long postId,
                                                                   @PathVariable(value = "board-type") String boardType){
        if(!boardService.isExistPost(postId))
            return ResponseEntity.status(404).body(new BaseResponse("해당 게시글이 존재하지 않습니다.",404));

        if(boardType.equals("FREE")&&!boardService.postType(postId).equals("FREE"))
            return ResponseEntity.status(400).body(new BaseResponse("자유 게시판의 글이 아닙니다.",400));
        else if(boardType.equals("STUDENT")&&!boardService.postType(postId).equals("STUDENT"))
            return ResponseEntity.status(400).body(new BaseResponse("재학생 게시판의 글이 아닙니다.",400));
        else if(boardType.equals("GRADUATE")&&!boardService.postType(postId).equals("GRADUATE"))
            return ResponseEntity.status(400).body(new BaseResponse("졸업생 게시판의 글이 아닙니다.",400));

        if(!boardService.addOrSubPostLike(postId))
            return ResponseEntity.status(200).body(new BaseResponse("좋아요가 취소됐습니다.",200));
        return ResponseEntity.status(201).body(new BaseResponse("좋아요 누르기가 성공했습니다.",201));
    }
}
