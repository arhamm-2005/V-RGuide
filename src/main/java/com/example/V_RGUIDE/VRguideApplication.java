package com.example.V_RGUIDE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class VRguideApplication {

	public static void main(String[] args) {
		SpringApplication.run(VRguideApplication.class, args);
	}

}
