package com.example.MPBE.service.service;

import com.example.MPBE.domain.model.User;
import com.example.MPBE.domain.repository.UserRepository;
import com.example.MPBE.service.request.SignUpReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public boolean isExistEmail(String email) {
        User byEmail = userRepository.findByEmail(email).orElse(null);
        return byEmail != null;
    }

    public boolean isExistUserId(String userId) {
        User byUserId = userRepository.findByUserId(userId).orElse(null);
        return byUserId != null;
    }

    public void save(SignUpReq signUpReq) {
        userRepository.save(signUpReq.toUserModel());
    }
}
