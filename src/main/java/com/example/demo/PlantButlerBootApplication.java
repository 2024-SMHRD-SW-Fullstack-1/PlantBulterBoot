package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// SpringBootApplication 어노테이션
// : 이 어노테이션이 있는 위치로부터 하위에 있는 클래스들만 객체화시켜줌
// => 일을 할 수 있음
@SpringBootApplication
public class PlantButlerBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantButlerBootApplication.class, args);
	}

}
