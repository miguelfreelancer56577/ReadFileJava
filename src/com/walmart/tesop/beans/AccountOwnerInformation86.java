package com.walmart.tesop.beans;

import java.util.Arrays;
import java.util.regex.Matcher;

import com.walmart.tesop.util.StringUtils;

/**
 * Operative Treasury - TESOP
 * WalMart Mexico y Centroamerica
 * Class name: 		  AccountOwnerInformation86
 * Class description: Contiene los atributos de la sección Account Owner Information del reporte MT940.
 * Last Modification: 15/11/2016
 * Last Date:         15/11/2016
 */

public class AccountOwnerInformation86 {

	private String productTypeId;
	private String productType;
	private String branchOperation;
	private String reference;
	private String totalAmount;
	private String[] skipWords;
	
	public AccountOwnerInformation86() {
		super();
		skipWords = new String[]{"NONREF"};
	}
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getBranchOperation() {
		return branchOperation;
	}
	public void setBranchOperation(String branchOperation) {
		this.branchOperation = branchOperation;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount==null?"0.0":totalAmount.replace(",", "");
	}
	public String validateBranchOperation() {
		try {
			
			if(this.branchOperation == null || this.getBranchOperation().trim().equalsIgnoreCase("NULL") || !StringUtils.isNumber(this.branchOperation))
				return "NULL";
			else 
				return this.branchOperation;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return "NULL";
	}
	
	public void validateCurrency(String currencyCode, String bankCode){
		final String currencyMxn = "MXN";
		final String currencyUsd = "USD";
		String[] arrayBankCode = new String[]{"7104","879","878"};
		Arrays.sort(arrayBankCode);
		if (currencyCode.trim().equals(currencyUsd)){
			if(Arrays.binarySearch(arrayBankCode, bankCode) >= 0){
				Matcher m = StringUtils.findPattern(StringUtils.refeIntoDescTag86, reference);
				if(m.find() == true){
					branchOperation = m.group(0).replaceAll(StringUtils.hasTwoSpaces, "");
					branchOperation = branchOperation.replaceAll("REF:", "").trim();
//					branchOperation = branchOperation.trim();
				}
			}
		}
//			suplementaryDetails99.getBankCode() 
		reference = reference.replaceAll(StringUtils.hasTwoSpaces, "").replaceAll(StringUtils.refeIntoDescTag86,  " ");
		for (String word : skipWords) {
			reference = reference.replace(word, "");
		}
		reference = StringUtils.rPadAlphanumericReference(reference, 20);
//		reference = statementLine.getAccountOwnerInformation86().getReference();
//		StringUtils.rPadAlphanumericReference(statementLine.getAccountOwnerInformation86().getReference(), 20);
	}
	
}
