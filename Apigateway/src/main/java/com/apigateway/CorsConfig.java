package com.apigateway;

import java.util.Collections;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.reactive.CorsWebFilter;

import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class CorsConfig {

	@Bean
	public CorsWebFilter corsFilter() {

		
		CorsConfiguration config = new CorsConfiguration();

		System.out.println("Security configured on API gateway");

		config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));

		config.setAllowedMethods(Collections.singletonList("*"));

		config.setAllowCredentials(true);

		config.setAllowedHeaders(Collections.singletonList("*"));

		config.setMaxAge(3600L); // cache for 3600 seconds

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());

		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);

	}

}