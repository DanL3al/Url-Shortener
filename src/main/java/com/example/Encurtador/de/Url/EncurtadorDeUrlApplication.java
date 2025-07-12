package com.example.Encurtador.de.Url;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EncurtadorDeUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncurtadorDeUrlApplication.class, args);
	}

}
