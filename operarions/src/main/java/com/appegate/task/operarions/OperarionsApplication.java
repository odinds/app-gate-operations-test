package com.appegate.task.operarions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OperarionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperarionsApplication.class, args);
	}

}
