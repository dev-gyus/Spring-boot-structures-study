package com.example.helloboot;

import com.example.tobyspring.config.MySpringBootAnnotation;
import org.springframework.boot.SpringApplication;


@MySpringBootAnnotation
public class HellobootApplication {
	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}
}
