package com.walmart.tesop.beans;

import com.walmart.tesop.util.StringUtils;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Operative Treasury - TESOP
 * WalMart Mexico y Centroamerica
 * Class name: 		  StatementLine
 * Class description: Contiene los atributos de un movimiento en el reporte MT940.
 * Last Modification: 15/11/2016
 * Last Date:         15/11/2016
 */

public class StatementLine {

	private StatementLine61 statementLine61;
	private SuplementaryDetails99 suplementaryDetails99;
	private AccountOwnerInformation86 accountOwnerInformation86;
	private boolean isThereStatementLine61;
	private boolean isThereSuplementaryDetails99;
	private boolean isThereAccountOwnerInformation86;
	private String branchOperation;
	
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
	
	private boolean isDate(String instRef) {
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
	
	private boolean isAccount(String instRef) {
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
