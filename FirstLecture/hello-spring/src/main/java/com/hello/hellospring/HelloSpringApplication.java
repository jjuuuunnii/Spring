package com.hello.hellospring;
//이 패키지 밑에 있는 애들은 메인을 실행시켰을때 스프링이 하위 폴더들을 전부 뒤지면서 스프링 빈으로 등록한다.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
