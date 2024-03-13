package com.jsrss.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DayalBookstoreMavenApplication {

static Logger log = LoggerFactory.getLogger(DayalBookstoreMavenApplication.class);
	
	public static void main(String[] args)
	{
		log.info("My Boot App - Main() begins");
		log.debug("My Boot App - Main() begins");
		log.warn("My Boot App - Main() begins");
		log.info("Update 1 by Shankar");
		log.info("Update 2 by Shankar");
		log.info("Update 3 by Shankar");
		log.info("Update 4 by Shankar");
		SpringApplication.run(DayalBookstoreMavenApplication.class, args);
		log.info("My Boot App - Main() ends");
		log.debug("My Boot App - Main() ends");
		log.warn("My Boot App - Main() ends");
	}
}
