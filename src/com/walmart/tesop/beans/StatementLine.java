package com.walmart.tesop.beans;

import com.walmart.tesop.util.StringUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.text.SimpleDateFormat;

/**
 * Operative Treasury - TESOP
 * WalMart Mexico y Centroamerica
 * Class name: 		  StatementLine
 * Class description: Contiene los atributos de un movimiento en el reporte MT940.
 * Last Modification: 15/11/2016
 * Last Date:         15/11/2016
 */

public abstract class StatementLine {

	protected StatementLine61 statementLine61;
	protected SuplementaryDetails99 suplementaryDetails99;
	protected AccountOwnerInformation86 accountOwnerInformation86;
	protected boolean isThereStatementLine61;
	protected boolean isThereSuplementaryDetails99;
	protected boolean isThereAccountOwnerInformation86;
	protected String branchOperation;
	
	public StatementLine() {
		super();
		this.isThereStatementLine61 = false;
		this.isThereSuplementaryDetails99 = false;
		this.isThereAccountOwnerInformation86 = false;
	}
	public StatementLine61 getStatementLine61() {
		return statementLine61;
	}
	public void setStatementLine61(StatementLine61 statementLine61) {
		this.statementLine61 = statementLine61;
	}
	public SuplementaryDetails99 getSuplementaryDetails99() {
		return suplementaryDetails99;
	}
	public void setSuplementaryDetails99(SuplementaryDetails99 suplementaryDetails99) {
		this.suplementaryDetails99 = suplementaryDetails99;
	}
	public AccountOwnerInformation86 getAccountOwnerInformation86() {
		return accountOwnerInformation86;
	}
	public void setAccountOwnerInformation86(AccountOwnerInformation86 accountOwnerInformation86) {
		this.accountOwnerInformation86 = accountOwnerInformation86;
	}
	public boolean isThereStatementLine61() {
		return isThereStatementLine61;
	}
	public void setThereStatementLine61(boolean isThereStatementLine61) {
		this.isThereStatementLine61 = isThereStatementLine61;
	}
	public boolean isThereSuplementaryDetails99() {
		return isThereSuplementaryDetails99;
	}
	public void setThereSuplementaryDetails99(boolean isThereSuplementaryDetails99) {
		this.isThereSuplementaryDetails99 = isThereSuplementaryDetails99;
	}
	public boolean isThereAccountOwnerInformation86() {
		return isThereAccountOwnerInformation86;
	}
	public void setThereAccountOwnerInformation86(
			boolean isThereAccountOwnerInformation86) {
		this.isThereAccountOwnerInformation86 = isThereAccountOwnerInformation86;
	}	
	public String getBranchOperation() {
		return branchOperation;
	}
	public void setBranchOperation(String branchOperation) {
		this.branchOperation = branchOperation;
	}
	
	public void calculateBranchOperation() {
		if(this.isThereStatementLine61 && this.isThereSuplementaryDetails99) {
			if(this.getSuplementaryDetails99().getBankCode().trim().equalsIgnoreCase("342") || 
					this.getSuplementaryDetails99().getBankCode().trim().equals("322") || 
					this.isDate(this.getStatementLine61().getInstitutionReference())) {
				this.setBranchOperation("0870");
			} else if(this.isAccount(this.getStatementLine61().getInstitutionReference())) {
				this.setBranchOperation(this.getStatementLine61().getInstitutionReference().substring(4, 8));
			} else {
				this.setBranchOperation("0870");
			}
		}
	}
	
	public abstract void validateRerefenceNumeric();
	
	/**
	 * This function validate the type of document which it can read 
	 * @param nameMt940
	 * @return statementLine
	 */
	public static StatementLine getInstance(String nameMt940){
		
		StatementLine statementLine = null;
		
		if(nameMt940.contains("MT940_CITIBANAMEX_")){
			statementLine = new StatementLineBanamex();
		}else if (nameMt940.contains("MT940_BANCOMER_")){
			statementLine = new StatementLineBancomer();
		}else if (nameMt940.contains("MT940_BANORTE_")){
			statementLine = new StatementLineBanorte();
		}else if (nameMt940.contains("MT940_HSBC_")){
			statementLine = new StatementLineHsbc();
		}
		
		return statementLine;
	}
	
	/*
	 * it cleans the reference alphanumeric into tag 68
	 */
	public void toCleanRefAlpha(String regExpRefAlpha){
		
		String referenceAlphanumeric = accountOwnerInformation86.getReference();
		
		try {
			
			//		it removes each two spaces between each word, then replace the content of the reference numeric with the concurrent pattern
			referenceAlphanumeric = referenceAlphanumeric.replaceAll(regExpRefAlpha, " ").replaceAll(StringUtils.hasTwoSpaces, " ");
			
		} catch (Exception e) {
			
//			if the pattern is null just cleans the string
			referenceAlphanumeric = referenceAlphanumeric.replaceAll(StringUtils.hasTwoSpaces, " ");
			
		}
		
		//		it replaces each unnecessary words into the reference alphanumeric
		for (String word : StringUtils.skipWords) {
			referenceAlphanumeric = referenceAlphanumeric.replace(word, "");
		}
		
//		it sets the new reference into of the tag 86
		accountOwnerInformation86.setReference(StringUtils.rPadAlphanumericReference(referenceAlphanumeric, 20));
		
	}
	
	public boolean isDate(String instRef) {
		boolean result = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("YYMMDD");
//			String date = sdf.format(new Date());
			String date = "161010";
			
			if(instRef != null && instRef.length() > 10 && instRef.substring(0, 2).equals("00")) {
				if(instRef != null && instRef.length() > 10 && instRef.substring(2, 8).equals(date)) {
					result = true;
				} /*else {
					System.out.println("instRef : " + instRef + " is not date");
				}*/
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isAccount(String instRef) {
		boolean result = false;
		try {			
			if(instRef != null && instRef.length() > 10 && instRef.substring(0, 4).equals("0000")) {
//				System.out.println("isAccount.instRef.substring(5, 8): " + instRef.substring(0, 4));
				if(instRef != null && instRef.length() > 10 && StringUtils.isNumber(instRef.substring(4, 8))) {
					result = true;
//					System.out.println("isAccount.instRef: " + instRef);
//					System.out.println("isAccount.instRef.substring(5, 8): " + instRef.substring(4, 8));
				} /*else {
					System.out.println("instRef : " + instRef);
					System.out.println("isAccount.instRef.substring(5, 8): " + instRef.substring(4, 8));
				}*/
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
