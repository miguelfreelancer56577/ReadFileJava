package com.walmart.tesop.beans;

import java.util.regex.Matcher;

import com.walmart.tesop.util.StringUtils;

public class StatementLineHsbc extends StatementLine {

	/*
     * it validates the reference number into tag 86 for Hsbc
     */
	@Override
	public void validateRerefenceNumeric() {
		
//		today there aren't any rules for this bank
        
        toCleanRefAlpha(null);

	}

}
