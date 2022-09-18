package com.example.MPBE.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class RefreshToken {

    @Id
    private String refreshKey;
    private String refreshValue;

    public RefreshToken updateValue(String token) {
        this.refreshValue = token;
        return this;
    }

    @Builder
    public RefreshToken(String key, String value) {
        this.refreshKey = key;
        this.refreshValue = value;
    }
    // RDB 로 구현하면 생성/수정 시간 컬럼 추가하여 배치 작업으로 만료된 토큰들 삭제
}
