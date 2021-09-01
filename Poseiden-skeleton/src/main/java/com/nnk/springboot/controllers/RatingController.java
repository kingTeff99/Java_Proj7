package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.NegativeNumberException;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;

import lombok.extern.slf4j.Slf4j;

@Controller@Slf4j
public class RatingController {
	
    // TODO: Inject Rating service
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private RatingRepository ratingRepository;

    @RequestMapping("/rating/list")
    public String home(Model model) {
        // TODO: find all Rating, add to model
    	log.info("home: show rating/list");
    	
        model.addAttribute("ratings", ratingRepository.findAll());
        
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating,  RedirectAttributes redirectAttributes) {
    	
        // TODO: check data valid and save to db, after saving return Rating list
    	log.info("validate: FitchRating; " + rating.getFitchRating()
        + " MoodysRating; " + rating.getMoodysRating()
        + " SandPRating; " + rating.getSandPRating()
        + "OrderNumber" + rating.getOrderNumber());

    	try {
    		
    		ratingService.validateRating(rating);
    		
    	} catch (NegativeNumberException e) {
    		
    		redirectAttributes.addAttribute("error_number_negative",true);
    		
    		return "redirect:/rating/add";
    	}
    	
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,  RedirectAttributes redirectAttributes) {
        // TODO: get Rating by Id and to model then show to the form
    	log.info("showUpdateForm: Rating  " + id);
    	
        Rating rating = ratingRepository.findRatingById(id);
        
        if (rating.equals(null)){
        	
            redirectAttributes.addAttribute("id_not_found", true);
            
            return  "redirect:/rating/list";
            
        }
        
        model.addAttribute("rating", rating);
        
        return "rating/update";
        
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result,  RedirectAttributes redirectAttributes) {
    	
        // TODO: check required fields, if valid call service to update Rating and return Rating list
    	log.info("updateRating: " + id);
    	
        if(result.hasErrors()){
        	
            redirectAttributes.addAttribute("error", true);
            
        }
        
        try {
        	
            ratingService.updateRating(id, rating);
            
        } catch (NegativeNumberException e) {
        	
            redirectAttributes.addAttribute("error_number_negative",true);
            
            return "redirect:/rating/update/{id}";
            
        }
        
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {
    	
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
    	log.info("deleteRating:" + id);
    	
        ratingService.deleteRating(id);
        
        return "redirect:/rating/list";
        
    }
    
}
