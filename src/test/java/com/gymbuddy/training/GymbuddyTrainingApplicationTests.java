package com.gymbuddy.training;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GymbuddyTrainingApplicationTests {

	private final ApplicationContext context;

	GymbuddyTrainingApplicationTests(ApplicationContext context) {
		this.context = context;
	}

	@Test
	void contextLoads() {
		assertEquals("gymbuddy-training", context.getId());
	}
}
