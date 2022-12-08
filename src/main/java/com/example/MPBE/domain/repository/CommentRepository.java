package com.example.MPBE.domain.repository;

import com.example.MPBE.domain.model.Comment;
import com.example.MPBE.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long id);
    Page<Comment> findAllByUser(User user, Pageable pageable);
}
