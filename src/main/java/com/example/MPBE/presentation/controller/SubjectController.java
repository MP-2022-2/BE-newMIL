package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.dto.SubjectDto;
import com.example.MPBE.service.request.SubjectRegistrationReq;
import com.example.MPBE.service.request.SubjectRemovalReq;
import com.example.MPBE.service.response.BaseResponse;
import com.example.MPBE.service.response.SubjectListRes;
import com.example.MPBE.service.service.SubjectService;
import com.example.MPBE.util.enums.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/registration")
    public ResponseEntity<? extends BaseResponse> addSubjectWhenSignUp(@Valid @RequestBody SubjectRegistrationReq subjectRegistrationReq){
        subjectService.saveWhenSignUp(subjectRegistrationReq.getStudentId(),subjectRegistrationReq.getSubjectList());
        return ResponseEntity.status(201).body(new BaseResponse("수강 과목 등록에 성공했습니다.",201));
    }

    @PostMapping("/registration/{major-or-non-major}")
    public ResponseEntity<? extends BaseResponse> addSubject(@Valid @PathVariable(value = "major-or-non-major") String majorOrNot, @RequestBody SubjectRegistrationReq subjectRegistrationReq) throws IllegalAccessException {
        boolean isMajor = Subject.verify(majorOrNot);
        if(!subjectService.saveWhenLogined(subjectRegistrationReq.getSubjectList(),isMajor))
            return ResponseEntity.status(409).body(new BaseResponse("이미 등록된 과목이 존재합니다.", 409));
        return ResponseEntity.status(201).body(new BaseResponse("수강 과목 등록에 성공했습니다.",201));
    }

    @GetMapping("/mypage")
    public ResponseEntity<? extends BaseResponse> getSubject(){
        List<SubjectDto> majorSubjectDtoList = subjectService.myMajorSubject();
        List<SubjectDto> nonMajorSubjectDtoList = subjectService.myNonMajorSubject();
        return ResponseEntity.status(200).body(new SubjectListRes("수강 과목 조회에 성공했습니다.",200,majorSubjectDtoList,nonMajorSubjectDtoList));
    }

    @DeleteMapping("/removal/{major-or-non-major}")
    public ResponseEntity <? extends BaseResponse> deleteSubject(@Valid @PathVariable(value = "major-or-non-major") String majorOrNot,
                                                                 @RequestBody SubjectRemovalReq subjectRemovalReq) throws IllegalAccessException {
        boolean isMajor = Subject.verify(majorOrNot);
        subjectService.deleteMySubject(subjectRemovalReq.getSubject(),isMajor);
        return ResponseEntity.status(200).body(new BaseResponse("수강 과목 삭제에 성공했습니다.",200));
    }
 }
