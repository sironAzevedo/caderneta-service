package br.com.caderneta.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableAutoConfiguration
public class CadernetaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadernetaServiceApplication.class, args);
	}

}
