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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exception.NegativeNumberException;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

import lombok.extern.slf4j.Slf4j;

@Controller@Slf4j
public class TradeController {
	
    // TODO: Inject Trade service
	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private TradeRepository tradeRepository;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
    	log.info("home: show trade/list");
    	
        model.addAttribute("trades", tradeRepository.findAll());
        
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, RedirectAttributes redirectAttributes) {
    	
        // TODO: check data valid and save to db, after saving return Trade list
    	log.info("validate trade: name " + trade.getAccount() + " Type: " + trade.getType());
        
    	try {
    		
            tradeService.validateTrade(trade);
            
        } catch (NegativeNumberException e) {
        	
            redirectAttributes.addAttribute("negative_number", true);
            
            return "redirect:/trade/add";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    	
        // TODO: get Trade by Id and to model then show to the form
    	 log.info("showUpdateForm Trade:  " + id);
    	 
         Trade trade = tradeRepository.findTradeById(id);
         
         if (trade.equals(null)){
        	 
             redirectAttributes.addAttribute("id_not_found", true);
             
             return  "redirect:/trade/list";
         }
         
         model.addAttribute("trade", trade);
         
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
    	log.info("updateRuleName: " + id);
    	
        if(result.hasErrors()){
            redirectAttributes.addAttribute("error", true);
        }
        
        try {
        	
            tradeService.updateTrade(id, trade);
            
        } catch (NegativeNumberException e) {
        	
            redirectAttributes.addAttribute("error_number_negative", true);
            
            return "redirect:/trade/update/{id}";
        }
        
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
    	log.info("delete Trade:" + id);
    	
        tradeService.deleteTrade(id);
        
        return "redirect:/trade/list";
        
    }
    
}
