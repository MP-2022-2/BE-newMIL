package com.example.MPBE.domain.repository;


import com.example.MPBE.domain.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
