package com.example.MPBE.presentation.controller;

import com.example.MPBE.service.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class testController {
    @GetMapping("/test")
    public ResponseEntity<? extends BaseResponse> test(){
        return ResponseEntity.status(200).body(new BaseResponse("TESTCODE", 200));
    }

    @GetMapping("/fxxking")
    public String fxxk(){
        return "xucx";
    }
}
