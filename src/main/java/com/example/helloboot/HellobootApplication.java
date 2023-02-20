package com.example.helloboot;

import com.example.tobyspring.config.MySpringBootAnnotation;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


@MySpringBootAnnotation
public class HellobootApplication {

	@Bean
	ApplicationRunner applicationRunner(Environment env) {
		return args -> {
			System.out.println("my.name = " + env.getProperty("my.name"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}
}
