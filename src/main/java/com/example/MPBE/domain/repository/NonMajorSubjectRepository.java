package com.example.MPBE.domain.repository;

import com.example.MPBE.domain.model.NonMajorSubject;
import com.example.MPBE.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NonMajorSubjectRepository extends JpaRepository<NonMajorSubject, Long>{
    Optional<NonMajorSubject> findByUserAndSubject(User user, String subject);
    Optional<NonMajorSubject> findBySubject(String subject);

    void deleteByUserAndSubject(User user, String subject);
}
