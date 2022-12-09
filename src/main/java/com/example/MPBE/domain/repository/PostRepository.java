package com.example.MPBE.domain.repository;

import com.example.MPBE.domain.model.Post;
import com.example.MPBE.domain.model.User;
import com.example.MPBE.util.enums.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByBoardType(BoardType boardType, Pageable pageable);
    boolean existsById(Long id);
    Page<Post> findAllByUser(User user, Pageable pageable);
    List<Post> findTop5ByOrderByIdDesc();
    List<Post> findAllByCreatedAtBetween(Date sevenDaysAgo, Date now);
}
