package com.agileengine.imageparser;

import com.agileengine.imageparser.client.AgileEngineImageApi;
import com.agileengine.imageparser.service.impl.AgileEngineApiParsingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ImageParserApplication implements CommandLineRunner {

	@Autowired
	AgileEngineImageApi agileEngineImageApi;

	@Autowired
	private Environment env;

	private static final Logger log = LoggerFactory.getLogger(ImageParserApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ImageParserApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("==================================================");
		log.info("====================App started===================");
		log.info("External API parsing frequency: {}ms}", env.getProperty("parsing.frequency.time.milliseconds"));
		log.info("Cache TTL: {}ms}", env.getProperty("cache.time-to-live.milliseconds"));
		log.info("==================================================");
	}

}
