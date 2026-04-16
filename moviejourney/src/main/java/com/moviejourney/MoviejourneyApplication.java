package com.moviejourney;

import com.moviejourney.global.config.TmdbProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TmdbProperties.class)
public class MoviejourneyApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(MoviejourneyApplication.class, args);
	}
}
