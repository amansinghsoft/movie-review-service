package com.movie.review.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.movie.review.domain.Review;

import reactor.core.publisher.Flux;

public interface ReviewReactiveRepository extends ReactiveMongoRepository<Review, String>{

	Flux<Review> findReviewsByMovieInfoId(Long movieInfoId);
}
