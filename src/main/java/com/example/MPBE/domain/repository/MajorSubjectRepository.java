package com.example.MPBE.domain.repository;


import com.example.MPBE.domain.model.MajorSubject;
import com.example.MPBE.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MajorSubjectRepository extends JpaRepository<MajorSubject, Long> {
    Optional<MajorSubject> findByUserAndSubject(User user, String subject);
    void deleteByUserAndSubject(User user, String subject);
}
