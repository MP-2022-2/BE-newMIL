package com.example.MPBE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MpbeApplication {
	@Value("${jasypt.encryptor.password}")
	private static String PASSWORD;

	public static void main(String[] args) {
		System.out.println(PASSWORD);
		SpringApplication.run(MpbeApplication.class, args);
	}

}
