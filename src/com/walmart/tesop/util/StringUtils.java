package com.walmart.tesop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Operative Treasury - TESOP
 * WalMart Mexico y Centroamerica
 * Class name: 		  StringUtils
 * Class description: Contiene algunos métodos útiles para poder determinar el tipo de dato en el reporte MT940.
 * Last Modification: 15/11/2016
 * Last Date:         15/11/2016
 */

public class StringUtils {
	
	public static Pattern pattern;
	public static Matcher matcher;

	private static final Logger	log	= LoggerFactory.getLogger(StringUtils.class);
	
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
//	this expression validate if the tag 86 has into it the reference number in the sub-field "Further Payment Description", when the payment type was made it in MXN 
	public static final String refeAtfirstIntoDescTag86 = "^\\d{1,16}";
//	this expression validate if the tag 86 has into it the reference number in the sub-field "Further Payment Description", when the payment type was made it in USD
	public static final String refeIntoDescTag86 = "REF:\\s{0,}\\d{1,16}";
//	valida si cuenta con ceros a la izquierda
	public static final String cerosIzquierda = "^0{1,}";
//	array de palabras que se deben de omitir en la referencia alfanumerica del tag 86  
	public static final String[] skipWords = new String[]{"NONREF"};
//	this expression validates if the reference alphanumeric has reference numeric for Bancomer
	public static final String refeTag86Bancomer = "(B[ANCOMER]{2,7}\\s?\\d+)|(RECIBIDO\\D*\\d{1,})|(B[ANCOS]{1,5}\\s*\\d+)|(TERCERO\\D*\\d+)|(CE\\d+)|(CB\\d+)";
//	this expression validates if the reference alphanumeric has reference numeric for Banorte
	public static final String refeTag86Banorte = "(REFERENCIA:\\D*\\d+)|(\\d+\\s+REFERENCIA)|((REF.|REF)\\s+\\d+)|(REFERENCIA CLIENTE\\s+\\d+)";
	
	/**
	 * This method receives two String, the first contents the regular expression and the second contents the concurrent string to match 
	 * @param regex
	 * @param input
	 * @return matcher
	 * @throws ParseException 
	 */
	
//	valida si contiene la fecha al inicio en los formatos yyyyddMM y yyyyMMdd deacuerdo al anio	
	public static String expDate(String date){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyMMdd");
		Calendar cal = Calendar.getInstance();
		String string = "^(year)([3][0-1]|[0-2]\\d{1})([0][1-9]|[1][0-2])$|^(year)([0][1-9]|[1][0-2])([3][0-1]|[0-2]\\d{1})";
		try {
			cal.setTime(dateFormatter.parse(date));
			return string.replaceAll("year", String.valueOf(cal.get(Calendar.YEAR)));
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static final Matcher findPattern(String regex, String input){
		pattern = Pattern.compile(regex);
	    matcher = pattern.matcher(input);
		return matcher;
	}
	
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

    public static String getFileName(File pathIn) {
    	try {
	        File[] filesList = pathIn.listFiles();
	        for(File f : filesList) {
	            if(f.isDirectory())
	            	getFileName(f);
	            if(f.isFile() && f.getName().contains("MT940_CITIBANAMEX_")) {
	            	return f.getName();
	            }
	        }
    	} catch(Exception e) {
    		log.error("MT940 StringUtils (getFileName) error: ", e);
    	}
        return "MT940_CITIBANAMEX_";
    }
    
    public static int backUpFile(String pathIn, String pathBackUp, String fileName) {
    	InputStream inStream = null;
    	OutputStream outStream = null;

        try {
            File afile =new File(pathIn + fileName);
            File bfile =new File(pathBackUp + fileName);

            inStream = new FileInputStream(afile);
        	outStream = new FileOutputStream(bfile);

        	byte[] buffer = new byte[1024];

        	int length;
        	    //copy the file content in bytes
        	while ((length = inStream.read(buffer)) > 0) {
        		outStream.write(buffer, 0, length);
        	}

        	inStream.close();
        	outStream.close();

        	//delete the original file
        	afile.delete();
        	return 1;
//        	    System.out.println("File is copied successful!");

        } catch(IOException e) {
        	log.error("MT940 StringUtils (backUpFile) error: ", e);
        	return 0;
        }
    }
    
    
}