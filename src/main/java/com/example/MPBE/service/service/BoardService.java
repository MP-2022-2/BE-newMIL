package com.example.MPBE.service.service;

import com.example.MPBE.domain.model.Post;
import com.example.MPBE.domain.model.PostLike;
import com.example.MPBE.domain.model.User;
import com.example.MPBE.domain.repository.*;
import com.example.MPBE.service.dto.CommentDto;
import com.example.MPBE.service.dto.PostDto;
import com.example.MPBE.service.request.CommentReq;
import com.example.MPBE.service.request.PostReq;
import com.example.MPBE.util.enums.BoardType;
import com.example.MPBE.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;

    private User findCurrentUser(){
        User user = userRepository.findById(SecurityUtil.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        return user;
    }

    public boolean isExistPost(Long id){
        return postRepository.existsById(id);
    }

    @Transactional
    public void addPost(PostReq postReq){
        User user = findCurrentUser();
        Post post = postReq.toModel(user);
        postRepository.save(post);
    }

    @Transactional
    public List<PostDto> getFreeBoardAll(Pageable pageable) {
        Page<Post> freePostList = postRepository.findAllByBoardType(BoardType.FREE,pageable);
        return freePostList.toList().stream().map(s -> new PostDto(s)).collect(Collectors.toList());
    }

    @Transactional
    public PostDto getPost(Long postId){
        return new PostDto(postRepository.findById(postId).orElse(null));
    }

    @Transactional
    public void addComment(Long postId, CommentReq commentReq){
        User user = findCurrentUser();
        Post post = postRepository.findById(postId).orElse(null);
        commentRepository.save(commentReq.toModel(user,post));
    }

    @Transactional
    public boolean addOrSubPostLike(Long postId){
        User user = findCurrentUser();
        Post post = postRepository.findById(postId).orElse(null);
        PostLike postLike = postLikeRepository.findByUserAndPost(user,post).orElse(null);
        if(postLike!=null) {
            postLikeRepository.delete(postLike);
            return false;
        }
        postLikeRepository.save(new PostLike(user,post));
        return true;
    }
}
