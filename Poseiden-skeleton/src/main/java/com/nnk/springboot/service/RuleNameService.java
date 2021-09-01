package com.nnk.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {
	
	@Autowired
	private RuleNameRepository ruleNameRepository;
	
    public void validateRuleName(RuleName ruleName){

        RuleName addRuleName = new RuleName();
        
        addRuleName.setName(ruleName.getName());
        
        addRuleName.setDescription(ruleName.getDescription());
        
        addRuleName.setJson(ruleName.getJson());
        
        addRuleName.setTemplate(ruleName.getTemplate());
        
        addRuleName.setSqlStr(ruleName.getSqlStr());
        
        addRuleName.setSqlPart(ruleName.getSqlPart());
        
        ruleNameRepository.save(addRuleName);
        
    }

    public void updateRuleName(Integer id, RuleName ruleName) {

        ruleName.setId(id);
        
        ruleNameRepository.save(ruleName);
    }

    public void deleteRuleName(Integer id) {
    	
        ruleNameRepository.deleteById(id);
        
    }

}
