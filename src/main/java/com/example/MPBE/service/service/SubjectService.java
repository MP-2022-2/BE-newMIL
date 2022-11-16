package com.example.MPBE.service.service;

import com.example.MPBE.domain.model.Subject;
import com.example.MPBE.domain.model.User;
import com.example.MPBE.domain.repository.SubjectRepository;
import com.example.MPBE.domain.repository.UserRepository;
import com.example.MPBE.service.dto.SubjectDto;
import com.example.MPBE.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    private User findCurrentUser(){
        User user = userRepository.findById(SecurityUtil.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        return user;
    }

    @Transactional
    public void save(Integer studentId, List<SubjectDto> subjectDtoList){
        User user = userRepository.findByStudentId(studentId).orElse(null);
        for(SubjectDto subjectDto : subjectDtoList) {
            subjectRepository.save(subjectDto.toModel(user));
        }
    }

    @Transactional
    public List<SubjectDto> mySubject(){
        User user = findCurrentUser();
        if(user.getSubjectList() == null) return null;
        List<SubjectDto> subjectDtoList = user.getSubjectList().stream().map(subject -> new SubjectDto(subject)).collect(Collectors.toList());
        return subjectDtoList;
    }
}
