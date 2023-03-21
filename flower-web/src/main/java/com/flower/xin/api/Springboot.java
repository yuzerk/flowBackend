package com.flower.xin.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.flower.xin")
@MapperScan(value = "com.flower.xin.orm.mapper")
public class Springboot {
	public static void main(String[] args) {
		SpringApplication.run(Springboot.class, args);
	}
}
