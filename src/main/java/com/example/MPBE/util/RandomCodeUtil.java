package com.example.MPBE.util;

import java.util.UUID;

public class RandomCodeUtil {
	public static String makeRandomCode(int length) {
		int start = (int) (Math.random() * 27);
		return UUID.randomUUID().toString().replace("-", "").substring(start, start + length);
	}
}
