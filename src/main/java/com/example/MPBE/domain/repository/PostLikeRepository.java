package com.example.MPBE.domain.repository;

import com.example.MPBE.domain.model.Post;
import com.example.MPBE.domain.model.PostLike;
import com.example.MPBE.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    Optional<PostLike> findByUserAndPost(User user, Post post);
}
