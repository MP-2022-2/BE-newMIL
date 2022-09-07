package com.example.MPBE;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MpbeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void jasypt_encrypt_decrypt_test() {
		String plainText = "plainText";

		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword("password");

		String encryptedText = jasypt.encrypt(plainText);
		String decryptedText = jasypt.decrypt(encryptedText);

		System.out.println(encryptedText);
		System.out.println(decryptedText);
	}

}
