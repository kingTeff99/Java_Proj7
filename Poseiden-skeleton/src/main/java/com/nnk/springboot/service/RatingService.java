package com.nnk.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.NegativeNumberException;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	public void validateRating(Rating rating) throws NegativeNumberException {
		
        if (rating.getOrderNumber() < 0 ){
            throw new NegativeNumberException("Order Number cannot be negative");
        }
        
        Rating addRating = new Rating();
        
        addRating.setMoodysRating(rating.getMoodysRating());
        
        addRating.setSandPRating(rating.getSandPRating());
        
        addRating.setFitchRating(rating.getFitchRating());
        
        addRating.setOrderNumber(rating.getOrderNumber());
        
        ratingRepository.save(addRating);

    }

    public void updateRating(Integer id, Rating rating) throws NegativeNumberException {
    	
        if (rating.getOrderNumber() < 0 ){
            throw new NegativeNumberException("Order Number cannot be negative");
        }
        
        rating.setId(id);
        
        ratingRepository.save(rating);

    }

    public void deleteRating(Integer id) {
    	
       ratingRepository.deleteById(id);
       
    }

}
