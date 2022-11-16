package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.dto.SubjectDto;
import com.example.MPBE.service.request.SubjectRegistrationReq;
import com.example.MPBE.service.response.BaseResponse;
import com.example.MPBE.service.response.SubjectListRes;
import com.example.MPBE.service.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/registration")
    public ResponseEntity<? extends BaseResponse> addSubject(@Valid @RequestBody SubjectRegistrationReq subjectRegistrationReq){
        subjectService.save(subjectRegistrationReq.getStudentId(),subjectRegistrationReq.getSubjectList());
        return ResponseEntity.status(201).body(new BaseResponse("수강 과목 등록에 성공했습니다.",201));
    }

    @GetMapping("/mypage")
    public ResponseEntity<? extends BaseResponse> getSubject(){
        List<SubjectDto> subjectDtoList = subjectService.mySubject();
        return ResponseEntity.status(200).body(new SubjectListRes("수강 과목 조회에 성공했습니다.",200,subjectDtoList));
    }

//    @GetMapping("/mypage")
//    public Map<String,Object> getSubject(){
//        List<SubjectDto> subjectDtoList = subjectService.mySubject();
//        Map<String,Object> subjectList = new HashMap<>();
//        subjectList.put("subject",subjectDtoList);
//        return subjectList;
//    }
 }
