package com.agileengine.imageparser;

import com.agileengine.imageparser.client.AgileEngineImageApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageParserApplication implements CommandLineRunner {

	@Autowired
	AgileEngineImageApi agileEngineImageApi;

	public static void main(String[] args) {
		SpringApplication.run(ImageParserApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("App started");
	}

}
