package com.example.MPBE.domain.repository;

import com.example.MPBE.domain.model.MediaStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaStudentRepository extends JpaRepository<MediaStudent, Long> {
    Optional<MediaStudent> findByNameAndStudentId(String name, Integer studentId);
}
