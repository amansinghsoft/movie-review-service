package com.movie.review.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Review implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private String reviewId;
    
   // @NotBlank(message = "roting.movieInfoId : must not be null")
    private Long movieInfoId;
    
    private String comment;
   
    @Min(value = 0L, message = "rating.negative : please pass a non-negative value")
    private Double rating;
}