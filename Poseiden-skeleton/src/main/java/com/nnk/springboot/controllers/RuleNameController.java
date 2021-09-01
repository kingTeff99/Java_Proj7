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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

import lombok.extern.slf4j.Slf4j;

@Controller@Slf4j
public class RuleNameController {
	
    // TODO: Inject RuleName service
	@Autowired
	private RuleNameService ruleNameService;
	
	@Autowired
	private RuleNameRepository ruleNameRepository;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
    	 log.info("home: show ruleName/list");
    	 
         model.addAttribute("ruleNames", ruleNameRepository.findAll());
         
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
    	log.info("addRuleForm: show ruleName/add"  );
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName) {
    	
        // TODO: check data valid and save to db, after saving return RuleName list
    	log.info("validate: ruleName: " + ruleName.getName()
        + " Description: " + ruleName.getDescription()
        + " Json: " + ruleName.getJson()
        + " Template:" + ruleName.getTemplate()
        + " SqlStr:" + ruleName.getSqlStr()
        + " SqlPart:" + ruleName.getSqlPart());

    	ruleNameService.validateRuleName(ruleName);
    	
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    	
        // TODO: get RuleName by Id and to model then show to the form
    	log.info("showUpdateForm: Rating  " + id);
    	
        RuleName ruleName = ruleNameRepository.findRuleNameById(id);
        
        if (ruleName.equals(null)){
        	
            redirectAttributes.addAttribute("id_not_found", true);
            
            return  "redirect:/ruleName/list";
        }
        
        model.addAttribute("ruleName", ruleName );
    	
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
    	log.info("updateRuleName: " + id);
    	
        if(result.hasErrors()){
        	
            redirectAttributes.addAttribute("error", true);
            
        }
        
        ruleNameService.updateRuleName(id, ruleName);
        
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
    	log.info("delete RuleName:" + id);
    	
        ruleNameService.deleteRuleName(id);
        
        return "redirect:/ruleName/list";
    }
}
