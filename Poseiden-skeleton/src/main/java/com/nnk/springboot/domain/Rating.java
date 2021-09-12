package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "Rating")
public class Rating {
	
	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id ;
	
	private String moodysRating ;
	
	private String sandPRating ;
	
	private String fitchRating ;
	
	private Integer orderNumber ;
	
}
