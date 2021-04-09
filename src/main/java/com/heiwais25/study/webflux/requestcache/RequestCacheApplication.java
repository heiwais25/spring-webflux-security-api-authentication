package com.heiwais25.study.webflux.requestcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class RequestCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestCacheApplication.class, args);
	}

}
