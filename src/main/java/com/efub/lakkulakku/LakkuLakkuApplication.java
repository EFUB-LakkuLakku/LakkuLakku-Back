package com.efub.lakkulakku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LakkuLakkuApplication {

	public static void main(String[] args) {
		SpringApplication.run(LakkuLakkuApplication.class, args);
	}

}
