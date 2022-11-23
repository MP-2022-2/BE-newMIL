package com.example.MPBE.service.service;

import com.example.MPBE.domain.model.Post;
import com.example.MPBE.domain.model.User;
import com.example.MPBE.domain.repository.*;
import com.example.MPBE.service.dto.PostDto;
import com.example.MPBE.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final PostRepository freePostRepository;
    private final UserRepository userRepository;

    private User findCurrentUser(){
        User user = userRepository.findById(SecurityUtil.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
        return user;
    }

    @Transactional
    public void addPost(PostDto postDto){
        User user = findCurrentUser();
        Post post = postDto.toModel(user);
        freePostRepository.save(post);
    }
}
