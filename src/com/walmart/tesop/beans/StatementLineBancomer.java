package com.walmart.tesop.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import com.walmart.tesop.util.StringUtils;

public class StatementLineBancomer extends StatementLine {

	/*
     * it validates the reference number into tag 86 for Hsbc
     */
	@Override
	public void validateRerefenceNumeric() {
		
//		it empty the value of reference into the StatementLine61 object  
		statementLine61.setAccountOwnerReference("NULL");
		
//		it never has reference inherited
		statementLine61.setInheritedReference("NULL");
		
		Templates templates = getTemplete();
		
		switch (templates) {
		
		case BCMl2:
			
			// the rules
			
			break;
			
		case BCMl3:
			
			break;
			
		case BCMl4:
			
			// the rules
			
			break;
			
		case BCMl5:
			
			// the rules
			
			break;

		default:
			
//			if the code of the transaction is not any of the above is emptied
			accountOwnerInformation86.setBranchOperation("NULL");
			
			break;
		}
		
	}
	
	private Templates getTemplete(){
		
//		it's set by default the layout 1
		Templates templates = Templates.BCMl1;
		
//		it gets all the groupings available 
		List<GroupBancomer> groupings = GroupingBancomer.getConfigurador().getGroupings();
		
//		it gets the code of the concurrent transaction    
		String codeTransaction = statementLine61.getInstitutionReference();
		
		group:
		for (GroupBancomer groupBancomer : groupings) {
			
			Map<String, String> transactions = groupBancomer.getTransactions();
			
			transaction:
			for (String key : transactions.keySet()) {
			    if(key.equalsIgnoreCase(codeTransaction)){
			    	templates = groupBancomer.getTemplete();
			    	break group;
			    }
			}
			
		}
		
		return templates;
	}

}
