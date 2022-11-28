package com.example.MPBE.domain.repository;

import com.example.MPBE.domain.model.Post;
import com.example.MPBE.util.enums.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByBoardType(BoardType boardType, Pageable pageable);
    boolean existsById(Long id);

}
