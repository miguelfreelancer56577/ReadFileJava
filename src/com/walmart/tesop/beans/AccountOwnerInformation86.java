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
	
}
