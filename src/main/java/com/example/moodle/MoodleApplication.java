package com.example.moodle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MoodleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodleApplication.class, args);
	}

}
//185.205.203.114