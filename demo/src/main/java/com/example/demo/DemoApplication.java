package com.example.demo;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

class Coffee{
	private final String id; //특정 커피 종류의 고유 식별값	
	private String name; //커피(종류) 이름

	public Coffee (String id, String name){
		this.id = UUID.randomUUID().toString();
		this.name = name;
		//무작위로 생성된 고유한 식별자를 반환합니다. toString() 메서드를 호출하여 UUID를 문자열로 변환
		//ex) "cbb44562-7da4-4c0c-a5bc-57f84d0a01c3"
	}
	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
}

