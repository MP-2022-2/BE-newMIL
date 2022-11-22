package com.example.MPBE.service.service;

import com.example.MPBE.domain.model.User;
import com.example.MPBE.domain.repository.MajorSubjectRepository;
import com.example.MPBE.domain.repository.NonMajorSubjectRepository;
import com.example.MPBE.domain.repository.UserRepository;
import com.example.MPBE.service.dto.SubjectDto;
import com.example.MPBE.util.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectService {
    private final UserRepository userRepository;
    private final MajorSubjectRepository majorSubjectRepository;
    private final NonMajorSubjectRepository nonMajorSubjectRepository;

    private User findCurrentUser(){
        User user = userRepository.findById(SecurityUtil.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        return user;
    }

    private boolean save(User user, List<SubjectDto> subjectDtoList, boolean isMajor){
        if(isMajor){
            for(SubjectDto subjectDto : subjectDtoList) {
                if(majorSubjectRepository.findByUserAndSubject(user,subjectDto.getSubject()).orElse(null)!=null) return false;
                majorSubjectRepository.save(subjectDto.toMajorModel(user));
            }
        }
        else {
            for(SubjectDto subjectDto : subjectDtoList) {
                if(nonMajorSubjectRepository.findByUserAndSubject(user,subjectDto.getSubject()).orElse(null)!=null) return false;
                nonMajorSubjectRepository.save(subjectDto.toNonMajorModel(user));
            }
        }
        return true;
    }

    @Transactional
    public void saveWhenSignUp(Integer studentId, List<SubjectDto> subjectDtoList){
        User user = userRepository.findByStudentId(studentId).orElse(null);
        save(user,subjectDtoList,true);
    }

    @Transactional
    public boolean saveWhenLogined(List<SubjectDto> subjectDtoList, boolean isMajor){
        User user = findCurrentUser();
        return save(user,subjectDtoList,isMajor);
    }

    @Transactional
    public List<SubjectDto> myMajorSubject(){
        User user = findCurrentUser();
        if(user.getMajorSubjectList() == null) return null;
        List<SubjectDto> majorSubjectDtoList = user.getMajorSubjectList().stream().map(majorSubject -> new SubjectDto(majorSubject)).collect(Collectors.toList());
        return majorSubjectDtoList;
    }

    @Transactional
    public List<SubjectDto> myNonMajorSubject(){
        User user = findCurrentUser();
        if(user.getMajorSubjectList() == null) return null;
        List<SubjectDto> nonMajorSubjectDtoList = user.getNonMajorSubjectList().stream().map(nonMajorSubject -> new SubjectDto(nonMajorSubject)).collect(Collectors.toList());
        return nonMajorSubjectDtoList;
    }

    @Transactional
    public boolean deleteMySubject(String subject, Boolean isMajor){
        User user = findCurrentUser();
        if(user.getMajorSubjectList() == null) return false;

        if(isMajor){
            majorSubjectRepository.deleteByUserAndSubject(user,subject);
        }
        else{
            nonMajorSubjectRepository.deleteByUserAndSubject(user,subject);
        }
        return true;
    }
}
