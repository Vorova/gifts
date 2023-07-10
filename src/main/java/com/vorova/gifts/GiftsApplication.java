package com.vorova.gifts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GiftsApplication {

	public static void main(String[] args) {
		log.info("Starting app");
		SpringApplication.run(GiftsApplication.class, args);
		log.info("Finished app");
	}

}