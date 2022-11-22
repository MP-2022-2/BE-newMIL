package com.example.MPBE.service.service;

import com.example.MPBE.domain.repository.FreeBoardRepository;
import com.example.MPBE.domain.repository.GraduateBoardRepository;
import com.example.MPBE.domain.repository.QuestionBoardRepository;
import com.example.MPBE.domain.repository.StudentBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final FreeBoardRepository freeBoardRepository;
    private final GraduateBoardRepository graduateBoardRepository;
    private final StudentBoardRepository studentBoardRepository;
    private final QuestionBoardRepository questionBoardRepository;

}
