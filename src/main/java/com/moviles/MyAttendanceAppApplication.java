package com.moviles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyAttendanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAttendanceAppApplication.class, args);
	}

}
