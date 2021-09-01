package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
	
	RuleName findRuleNameById(Integer id);
	
	RuleName findIdByNameAndDescription(String name, String description);
	
}

