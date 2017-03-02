package com.walmart.tesop.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

	public static final String lowerAlphabet = "abcdefghijklmnñopqrstuvwxyz";
	public static final String upperAlphabet = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	public static final String numbers = "0123456789";
	public static final String characters = "<([{\\^=$!|]})?*+>";
	public static final String priceCharacters = ",.";
	public static final String space = " ";
	/*
	 * Regular Expressions
	 */
//	this expression is to validate a string with characters not numeric 
	public static final String noNumeric = "\\D";
//	this expression validate if the string has more of two spaces
	public static final String hasTwoSpaces = "\\s{2,}";
	
	public static boolean isString(String value) {
		boolean isString = false;
		try {
			
			if(value == null || value.trim().equalsIgnoreCase(""))
				return false;
			
			String c;
			for(int index = 0; index < value.length(); index++) {
				if(index+1 > value.length())
					return false;
				
				c = value.substring(index, index+1);
				
				if(StringUtils.lowerAlphabet.contains(c))
					isString = true;

				if(StringUtils.upperAlphabet.contains(c))
					isString = true;

				if(StringUtils.numbers.contains(c))
					isString = true;
				
				if(StringUtils.characters.contains(c))
					isString = true;
				
				if(StringUtils.priceCharacters.contains(c))
					isString = true;
			}
						
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return isString;
	}
	
	
	public static boolean isNumber(String value) {
		boolean isString = false;
		try {
			
			if(value == null || value.trim().equalsIgnoreCase(""))
				return false;
			
			String c;
			for(int index = 0; index < value.length(); index++) {
				if(index+1 > value.length())
					return false;
				
				c = value.substring(index, index+1);
				
				if(StringUtils.lowerAlphabet.contains(c))
					return false;

				if(StringUtils.upperAlphabet.contains(c))
					return false;
				
				if(StringUtils.characters.contains(c))
					return false;

				if(StringUtils.numbers.contains(c))
					isString = true;
			}
						
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return isString;
	}
	
	public static boolean isPrice(String value) {
		boolean isString = false;
		try {
			
			if(value == null || value.trim().equalsIgnoreCase(""))
				return false;
				
				if(StringUtils.isNumber(value) || value.contains(",") || value.contains("."))
					return true;
				else 
					return false;
						
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return isString;
	}
	
	public static String formatShortDate(String value) {
		try {
	    	Date d = new Date(Integer.parseInt("1".concat(value.substring(0,2))), Integer.parseInt(value.substring(2, 4))-1, Integer.parseInt(value.substring(4, 6)));
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	return sdf.format(d);
		} catch(Exception ex) {
			ex.printStackTrace();
			return value;
		}
	}
	
	public static BigDecimal string2BigDecimal(String string) {
		BigDecimal bd = null;
		try {
			if(!StringUtils.isNumber(string))
				return new BigDecimal(0);
				
			bd = new BigDecimal(string);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return bd;
	}
	
	public static int string2int(String string) {
		int val = 0;
		try {
			if(!StringUtils.isNumber(string))
				return val;
				
			val = Integer.valueOf(string);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return val;
	}

    public static String lPadNumericReference(String value, int referenceLenght, int spacesLength) {
		StringBuilder sb = new StringBuilder("");
		try {
			
			if(value == null || value.trim().equals(""))
				return "0000000000          ";
			
			if(value.length() > 10) {
				sb.append( value.substring(value.length()-10, value.length()));
			} else {
				for(int i = 0; i < (referenceLenght - value.length()); i++) {
					sb.append("0");
				}
				sb.append(value);
			}
			
			for(int k = 0; k < spacesLength; k++) {
				sb.append(" ");
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return sb.toString();
    }
    
    public static String rPadAlphanumericReference(String value, int referenceLenght) {
    	StringBuilder sb = new StringBuilder("");
    	try {
    		if(value == null || value.trim().equals(""))
    			return "";
    		
    		value = value.replaceAll(StringUtils.hasTwoSpaces, " ");
    		
    		if(value.length() > 20) {
    			return value.substring(0, 20);
    		} else {
    			sb.append(value);
    			for(int i = 0; i < (referenceLenght - value.length()); i++) {
    				sb.append(" ");
    			}
    		}
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return sb.toString();
    }
	
}
