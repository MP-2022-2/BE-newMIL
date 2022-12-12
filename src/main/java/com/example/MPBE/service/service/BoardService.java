package com.example.MPBE.service.service;

import com.example.MPBE.domain.model.Post;
import com.example.MPBE.domain.model.PostLike;
import com.example.MPBE.domain.model.Tag;
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
    private final TagRepository tagRepository;

    private User findCurrentUser(){
        User user = userRepository.findById(SecurityUtil.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        return user;
    }

    public boolean isExistPost(Long id){
        return postRepository.existsById(id);
    }

    public String postType(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return post.getBoardType().toString();
    }

    @Transactional
    public void addPost(PostReq postReq){
        User user = findCurrentUser();
        Post post = postReq.toModel(user);
        if(postReq.getTagList()!=null){
            for(String s : postReq.getTagList()){
                Tag tag = Tag.builder()
                        .tag(s)
                        .post(post)
                        .boardType(postReq.getBoardType())
                        .build();
                post.addTag(tag);
                tagRepository.save(tag);
            }
        }
        postRepository.save(post);
    }

    @Transactional
    public List<PostDto> getFreeBoardAll(Pageable pageable) {
        Page<Post> freePostList = postRepository.findAllByBoardType(BoardType.FREE,pageable);
        return freePostList.toList().stream().map(s -> new PostDto(s)).collect(Collectors.toList());
    }

    @Transactional
    public List<PostDto> getStudentBoardAll(Pageable pageable) {
        Page<Post> studentPostList = postRepository.findAllByBoardType(BoardType.STUDENT,pageable);
        return studentPostList.toList().stream().map(s -> new PostDto(s)).collect(Collectors.toList());
    }

    @Transactional
    public List<PostDto> getGraduateBoardAll(Pageable pageable) {
        Page<Post> studentPostList = postRepository.findAllByBoardType(BoardType.GRADUATE,pageable);
        return studentPostList.toList().stream().map(s -> new PostDto(s)).collect(Collectors.toList());
    }

    @Transactional
    public List<PostDto> getQuestionBoardAll(Pageable pageable) {
        Page<Post> studentPostList = postRepository.findAllByBoardType(BoardType.QUESTION,pageable);
        return studentPostList.toList().stream().map(s -> new PostDto(s)).collect(Collectors.toList());
    }

    @Transactional
    public PostDto getPost(Long postId){
        return new PostDto(postRepository.findById(postId).orElse(null));
    }

    @Transactional
    public Boolean isLikedPost(Long postId) {
        User user = findCurrentUser();
        Post post = postRepository.findById(postId).orElse(null);
        return user.isLikedPost(post);
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
        postLike = new PostLike(user,post);
        user.addLike(postLike);
        post.addLike(postLike);
        postLikeRepository.save(postLike);
        return true;
    }

    @Transactional
    public List<PostDto> getTop5(){
        List<Post> top5List = postRepository.findTop5ByOrderByIdDesc();
        return top5List.stream().map(e -> new PostDto(e)).collect(Collectors.toList());
    }

    // Todo : 인기글 서비스 구현
//    @Transactional
//    public List<PostDto> getHot5(){
//        List<Post> oneWeekPosts = postRepository.findAllByCreatedAtBetween()
//    }
}
