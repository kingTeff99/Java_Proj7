package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.NegativeNumberException;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller@Slf4j
public class BidListController {
	
    // TODO: Inject Bid service
	@Autowired
	private BidListService bidListService;
	
	@Autowired
	private BidListRepository bidListRepository;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        // TODO: call service find all bids to show to the view
    	log.info("home : show bidList/list");
    	
    	model.addAttribute("bidLists", bidListRepository.findAll());
    	
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, RedirectAttributes redirectAttributes) {
    	
        // TODO: check data valid and save to db, after saving return bid list
    	log.info("validate: account : " + bid.getAccount() 
    				+ "; type :" + bid.getType() 
    				+ "; Quantity :" + bid.getBidQuantity());
    	
    	try {
    		
			bidListService.validate(bid);
			
		} catch (NegativeNumberException e) {
			
			redirectAttributes.addAttribute("id_not_find", redirectAttributes);
			
			return "redirect:/bidList/add";
		}
    	
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    	
        // TODO: get Bid by Id and to model then show to the form
    	log.info("showUpdateForm :" + id);
    	
    	BidList bidList = bidListRepository.findBidListById(id);
    	
    	if(bidList.equals(null)) {
    		
    		redirectAttributes.addAttribute("id_not_found", true);
    		
    		return "redirect:/bidList/list";
    	}
    	
    	model.addAttribute("bidList", bidList);
    	
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, RedirectAttributes redirectAttributes) {
    	
        // TODO: check required fields, if valid call service to update Bid and return list Bid
    	log.info("updateBid : " + id);
    	
    	if(result.hasErrors())
    		redirectAttributes.addAttribute("error", true);
    	
    	try {
    		
			bidListService.updateBid(id, bidList);
			
		} catch (NegativeNumberException e) {
			
			redirectAttributes.addAttribute("negative_number", true);
			
			return "redirect:/bidList/update/{id}";
		}
    	
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
    	
    	log.info("deleteBid :" + id);
    	
    	bidListService.deleteBid(id);
    	
        return "redirect:/bidList/list";
    }
}
