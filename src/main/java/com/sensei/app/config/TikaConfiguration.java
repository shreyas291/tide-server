package com.sensei.app.config;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TikaConfiguration {

	private final Logger log = LoggerFactory.getLogger(TikaConfiguration.class);
	
	@Bean
	public Tika getTika() {
		log.debug("Configuring Apache Tika");
		
		return new Tika();
	}
}
