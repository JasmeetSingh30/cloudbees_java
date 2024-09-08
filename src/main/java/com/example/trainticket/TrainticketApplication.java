package com.example.trainticket;

import com.example.trainticket.service.TrainService;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainticketApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(TrainticketApplication.class, args);
		TrainService trainService = appContext.getBean(TrainService.class);
		trainService.initialise();
	}

}
