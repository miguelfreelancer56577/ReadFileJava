package com.walmart.tesop.beans;

import java.util.regex.Matcher;

import com.walmart.tesop.util.StringUtils;

public class StatementLineBanamex extends StatementLine {

	/*
     * it validates the reference number into tag 86 for Banamex
     */
	@Override
	public void validateRerefenceNumeric() {
        
        String referenceAlphanumeric = "";
        
        String concurrentRegAlpha = StringUtils.refeIntoDescTag86;
        
        Matcher m = StringUtils.findPattern(concurrentRegAlpha, accountOwnerInformation86.getReference());
        
        if(m.find() == true){
            
//            it removes all spaces between REF: and the reference numeric  
            referenceAlphanumeric = m.group(0).replaceAll(StringUtils.hasTwoSpaces, "");
            
//            it removes the word REF: 
            String referenceNumeric  = referenceAlphanumeric.replaceAll("REF:", "").trim();
            
//            it sets the value of the correct reference numeric  
            accountOwnerInformation86.setBranchOperation(referenceNumeric);
            
//            it sets the value of reference numeric into tag 61 in null  
            statementLine61.setAccountOwnerReference("NULL");
            
        }
        
        toCleanRefAlpha(concurrentRegAlpha);

	}

}
