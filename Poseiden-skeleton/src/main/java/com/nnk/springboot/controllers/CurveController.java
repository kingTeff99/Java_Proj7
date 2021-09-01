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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exception.NegativeNumberException;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;

import lombok.extern.slf4j.Slf4j;

@Controller@Slf4j
public class CurveController {
	
    // TODO: Inject Curve Point service
	@Autowired
	private CurvePointService curvePointService;
	
	@Autowired
	private CurvePointRepository curvePointRepository;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
    	log.info("home : show curvePoint/list");
    	
    	model.addAttribute("curvePoints", curvePointRepository.findAll());
    	
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, RedirectAttributes redirectAttributes) {
        // TODO: check data valid and save to db, after saving return Curve list
    	log.info("validate: curveId : " + curvePoint.getCurveId() 
		+ "; Term :" + curvePoint.getTerm() 
		+ "; Value :" + curvePoint.getValue());

    	try {

    		curvePointService.validateCurvePoint(curvePoint);

    	} catch (NegativeNumberException e) {

    		redirectAttributes.addAttribute("negative_number", true);

    		return "redirect:/curvePoint/add";
    	}	
    	
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        // TODO: get CurvePoint by Id and to model then show to the form
    	log.info("showUpdateForm: " + id);
        CurvePoint curvePoint = curvePointRepository.findCurvePointById(id);
        if (curvePoint == null){
            redirectAttributes.addAttribute("id_not_found", true);
            return  "redirect:/curvePoint/list";
        }
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
    	log.info("updateBid: " + id);
        if(result.hasErrors()){
            redirectAttributes.addAttribute("error", true);
        }
        try {
            curvePointService.updateCurvePoint(id, curvePoint);
        } catch (NegativeNumberException e) {
            redirectAttributes.addAttribute("error_number_negative",true);
            return "redirect:/curvePoint/update/{id}";
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
    	log.info("deleteBid:" + id);
    	curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
