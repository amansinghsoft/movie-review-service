package com.movie.review.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.movie.review.handler.ReviewHandler;

@Configuration
public class ReviewRouter {

	@Bean
	public RouterFunction<ServerResponse> reviewsRoute(ReviewHandler reviewHandler){
		
		//Simple Router
/*		return RouterFunctions.route()  //RouterFunctions.route()
				.GET("/helloworld", (request -> ServerResponse.ok().bodyValue("Hello World")))
				.POST("/reviews", request -> reviewHandler.addReview(request))
				.GET("/reviews", request -> reviewHandler.getAllReview(request))
				.GET("/reviews/{id}", request -> reviewHandler.getReviewById(request))
				.PUT("/reviews/{id}", request -> reviewHandler.updateReviews(request))
				.DELETE("/reviews/{id}", request -> reviewHandler.deleteReview(request))
				.GET("/reviews/stream", request -> reviewHandler.getReviewsStream(request))
				.build();
	
*/ 	
		//nested router
		return RouterFunctions.route()  //RouterFunctions.route()
				.nest(RequestPredicates.path("/reviews"), builder -> {
					builder
						.POST("", request -> reviewHandler.addReview(request))
						.GET("", request -> reviewHandler.getAllReview(request))
						.GET("/{id}", request -> reviewHandler.getReviewById(request))
						.PUT("/{id}",  request -> reviewHandler.updateReviews(request))
						.DELETE("/{id}",  request -> reviewHandler.deleteReview(request))
						.GET("/stream",  request -> reviewHandler.getReviewsStream(request));
				})
				.GET("/helloworld", (request -> ServerResponse.ok().bodyValue("Hello World")))
				.build();

	}
	
}
