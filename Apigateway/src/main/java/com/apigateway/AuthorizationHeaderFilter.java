package com.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

	@Autowired
	Environment environment;
	
	public AuthorizationHeaderFilter() {
		super(Config.class);
	}
	
	public static class Config{
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) ->{
			ServerHttpRequest request=exchange.getRequest();
			if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				return onError(exchange,"No Authorization header",HttpStatus.UNAUTHORIZED);
			}
			String authHeader=request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String jwt=authHeader.replace("Bearer", "");
			if(!isJwtValid(jwt)) {
				return onError(exchange,"JWT token in not valid",HttpStatus.UNAUTHORIZED);
			}
			return chain.filter(exchange);
		};
	}
	private Mono<Void> onError(ServerWebExchange exchange,String err,HttpStatus status){
		ServerHttpResponse response=exchange.getResponse();
		response.setStatusCode(status);
		return response.setComplete();
	}
	private boolean isJwtValid(String jwt) {
		String subject="";
		try {
			subject=Jwts
					.parser()
					.setSigningKey(environment.getProperty("token.secret"))
					.parseClaimsJws(jwt)
					.getBody()
					.getSubject();
		}catch(Exception e) {
			return false;
		}
		if(subject==null || subject.isEmpty()) {
			return false;
		}
		return true;
	}
}
