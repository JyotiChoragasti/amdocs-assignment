package com.amdocs.media.assignement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProfileApplication {



	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
	}


	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}


}
