package com.example.MPBE.service.service;

import com.example.MPBE.util.RandomCodeUtil;
import com.example.MPBE.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificationService {

	private final RedisUtil redisUtil;

	public String makeCertificationCode(String email) {
		String randomCode = RandomCodeUtil.makeRandomCode(8);
		redisUtil.setDataExpire(email, randomCode, 60 * 5L);
		return randomCode;
	}

	public boolean verifyCertificationCode(String email, String randomCode) {
		String byRedisRandomCode = redisUtil.getData(email);
		if(byRedisRandomCode == null) return false;
		if(byRedisRandomCode.equals(randomCode)) return true;
		return false;
	}
}
