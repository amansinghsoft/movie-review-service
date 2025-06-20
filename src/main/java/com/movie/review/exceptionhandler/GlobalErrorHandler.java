package com.movie.review.exceptionhandler;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.movie.review.exception.ReviewDataException;
import com.movie.review.exception.ReviewNotFoundException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalErrorHandler implements ErrorWebExceptionHandler{

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		log.error("Exception Messages is : {}", ex);
		DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
		DataBuffer errorMessage = dataBufferFactory.wrap(ex.getMessage().getBytes());
		
		if(ex instanceof ReviewDataException) {
			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
			return exchange.getResponse().writeWith(Mono.just(errorMessage));
		}
		if(ex instanceof ReviewNotFoundException) {
			exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
			return exchange.getResponse().writeWith(Mono.just(errorMessage));
		}
		exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		return exchange.getResponse().writeWith(Mono.just(errorMessage));

	}

}
