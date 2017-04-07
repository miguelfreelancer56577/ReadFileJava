package com.walmart.tesop.beans;

import java.util.regex.Matcher;

import com.walmart.tesop.util.StringUtils;

public class StatementLineBanorte extends StatementLine {

	/*
     * it validates the reference number into tag 86 for Banorte
     */
	@Override
	public void validateRerefenceNumeric() {
        
        String concurrentRegAlpha = StringUtils.refeTag86Banorte;
        
        Matcher m = StringUtils.findPattern(concurrentRegAlpha, accountOwnerInformation86.getReference());
        
        if(m.find() == true){
        	
//            it cleans the reference alphanumeric   
        	String referenceNumeric  = m.group(0).replaceAll("\\D", "");
        	
//            it sets the value of the correct reference numeric  
            accountOwnerInformation86.setBranchOperation(referenceNumeric);
            
//            it sets the value of reference numeric into tag 61 in null  
            statementLine61.setAccountOwnerReference("NULL");
            
        }
        
        toCleanRefAlpha(concurrentRegAlpha);
            
	}

}
