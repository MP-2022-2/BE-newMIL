package com.example.MPBE.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Subject {
    MAJOR("major"),
    NON_MAJOR("non-major");

    private final String subjectName;

    public static boolean verify(String majorOrNot) throws IllegalAccessException {
        for(Subject s : Subject.values()){
            if(s.subjectName.equals(majorOrNot)&&s==MAJOR) return true;
            else if(s.subjectName.equals(majorOrNot)&&s==NON_MAJOR) return false;
        }
        throw new IllegalAccessException("잘못된 URI입니다.");
    }
}
