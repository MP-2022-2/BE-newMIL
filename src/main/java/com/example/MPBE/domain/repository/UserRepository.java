package com.example.MPBE.domain.repository;

import com.example.MPBE.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByStudentId(Integer studentId);
    Optional<User> findByUserId(String userId);
    Optional<User> findByNickName(String nickName);
}
