package com.example.MPBE.domain.repository;

import com.example.MPBE.domain.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBoardRepository extends JpaRepository<Board, Long> {
}
