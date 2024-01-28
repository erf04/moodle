package com.example.moodle;

import com.example.moodle.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Arrays;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MoodleApplication {

	static Course[] courses = new Course[0];
	public static void dataSaver(Course course)
	{
		Arrays.copyOf(courses,courses.length+1);
		courses[courses.length-1] = course;
	}


	public static void main(String[] args) {
		SpringApplication.run(MoodleApplication.class, args);
	}

}
//185.205.203.114