package com.gymbuddy.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gymbuddy")
public class GymbuddyTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymbuddyTrainingApplication.class, args);
	}

}
