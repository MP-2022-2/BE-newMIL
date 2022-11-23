package com.example.MPBE.domain.repository;

import com.example.MPBE.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
