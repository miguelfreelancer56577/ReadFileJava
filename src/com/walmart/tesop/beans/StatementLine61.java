package com.walmart.tesop.beans;

/**
 * Operative Treasury - TESOP
 * WalMart Mexico y Centroamerica
 * Class name: 		  StatementLine61
 * Class description: Contiene los atributos de la sección Statement Line del reporte MT940.
 * Last Modification: 15/11/2016
 * Last Date:         15/11/2016
 */

public class StatementLine61 {
	
	private String valueDate;
	private String entryDate;
	private String cardType;
	private String foundsCode;
	private String amount;
	private String entryMethod;
	private String entryReason;
	private String accountOwnerReference;
	private String inheritedReference;
	private String institutionReference;
	private int movementType;
	
	public StatementLine61() {
		super();
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
		
		if(cardType != null && cardType.trim().equals("C"))
			this.setMovementType(1);
		else if(cardType != null && cardType.trim().equals("D")) 
			this.setMovementType(-1);
	}
	public String getFoundsCode() {
		return foundsCode;
	}
	public void setFoundsCode(String foundsCode) {
		this.foundsCode = foundsCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount==null?"0.0":amount.trim().replace(",", ".");
	}
	public String getEntryMethod() {
		return entryMethod;
	}
	public void setEntryMethod(String entryMethod) {
		this.entryMethod = entryMethod;
	}
	public String getEntryReason() {
		return entryReason;
	}
	public void setEntryReason(String entryReason) {
		this.entryReason = entryReason;
	}
	public String getAccountOwnerReference() {
		return accountOwnerReference;
	}
	public void setAccountOwnerReference(String accountOwnerReference) {
		this.accountOwnerReference = accountOwnerReference;
	}
	public String getInheritedReference() {
		return inheritedReference;
	}
	public void setInheritedReference(String inheritedReference) {
		this.inheritedReference = inheritedReference;
	}
	public String getInstitutionReference() {
		return institutionReference;
	}
	public void setInstitutionReference(String institutionReference) {
		this.institutionReference = institutionReference;
	}
	public int getMovementType() {
		return movementType;
	}
	public void setMovementType(int movementType) {
		this.movementType = movementType;
	}	
}
