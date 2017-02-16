package com.walmart.tesop.beans;

import com.walmart.tesop.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;

import java.text.DecimalFormat;

/*
 *  20 ................ Transaction reference number
 *  25 ................	Account identification
 *  28 ................	Statement/page
 *  60F	...............	Opening Balance
 *  61 ................	Statement Line
 *  	86 ............	Account Owner Information
 *  	99 /CTC/ ......	Suplementary details
 *  	100 ........... total amount
 *  62F ...............	Closing Balance
 *  64 ................	Closing Available Balance
 * 
 */

public class TransactionReferenceNumber20 {
	
	private boolean isNew;
	private String transactionReference20;
	private String accountIdentification25;
	private String statementPage28;
	private OpeningBalance60F openingBalance60;
	private List<StatementLine> statementLineList;
	private ClosingBalance62F closingBalance62;
	private ClosingAvailableBalance64 closingAvailableBalance64;
	private int totalNumberCR;
	private int totalNumberDR;
	private BigDecimal totalCR;
	private BigDecimal totalDR;
	
	public TransactionReferenceNumber20() {
		super();
		this.statementLineList = new ArrayList<StatementLine>(1);
		this.totalCR = new BigDecimal(0.0);
		this.totalDR = new BigDecimal(0.0);
		this.totalNumberCR = 0;
		this.totalNumberDR = 0;
	}
	public boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	public String getTransactionReference20() {
		return transactionReference20;
	}
	public void setTransactionReference20(String transactionReference20) {
		this.transactionReference20 = transactionReference20;
	}
	public String getAccountIdentification25() {
		return accountIdentification25;
	}
	public void setAccountIdentification25(String accountIdentification25) {
		this.accountIdentification25 = accountIdentification25;
	}
	public String getStatementPage28() {
		return statementPage28;
	}
	public void setStatementPage28(String statementPage28) {
		this.statementPage28 = statementPage28;
	}
	public OpeningBalance60F getOpeningBalance60() {
		return openingBalance60;
	}
	public void setOpeningBalance60(OpeningBalance60F openingBalance60) {
		this.openingBalance60 = openingBalance60;
	}
	public List<StatementLine> getStatementLineList() {
		return statementLineList;
	}
	public void setStatementLineList(List<StatementLine> statementLineList) {
		this.statementLineList = statementLineList;
	}
	public ClosingBalance62F getClosingBalance62() {
		return closingBalance62;
	}
	public void setClosingBalance62(ClosingBalance62F closingBalance62) {
		this.closingBalance62 = closingBalance62;
	}
	public ClosingAvailableBalance64 getClosingAvailableBalance64() {
		return closingAvailableBalance64;
	}
	public void setClosingAvailableBalance64(ClosingAvailableBalance64 closingAvailableBalance64) {
		this.closingAvailableBalance64 = closingAvailableBalance64;
	}
	public int getTotalNumberCR() {
		return totalNumberCR;
	}
	public void setTotalNumberCR(int totalNumberCR) {
		this.totalNumberCR = totalNumberCR;
	}
	public int getTotalNumberDR() {
		return totalNumberDR;
	}
	public void setTotalNumberDR(int totalNumberDR) {
		this.totalNumberDR = totalNumberDR;
	}
	public BigDecimal getTotalCR() {
		return totalCR;
	}
	public void setTotalCR(BigDecimal totalCR) {
		this.totalCR = totalCR;
	}
	public BigDecimal getTotalDR() {
		return totalDR;
	}
	public void setTotalDR(BigDecimal totalDR) {
		this.totalDR = totalDR;
	}
	public void calculateTotals() {
		if(this.statementLineList == null || this.statementLineList.isEmpty()) {
			System.out.println("statementLineList is null or is empty: " + this.statementLineList.isEmpty());
			return;
		}
		
//		DecimalFormat df2 = new DecimalFormat(".##");
		StatementLine61 s61;
		for(StatementLine sl : this.statementLineList) {
			if(sl.isThereStatementLine61() == true) {
				s61 = sl.getStatementLine61();
							
				if(s61.getCardType() != null && s61.getCardType().trim().equalsIgnoreCase("D")) {					
					if(!StringUtils.isNumber(s61.getAmount())) {
						System.out.println("s61.getAmount() no es número: " + s61.getAmount());
						return;
					}
					
					try {
						
						if(new BigDecimal(s61.getAmount().replace(",", ".")).doubleValue() <= 0.0) {
//							System.out.println("Amount is zero: " + s61.getAmount().replace(",", "."));
							continue;
						}
							
						this.totalNumberDR = this.totalNumberDR + 1;
						this.totalDR = this.totalDR.add(new BigDecimal(s61.getAmount().replace(",", "."))); 
					} catch(Exception e1) {
						e1.printStackTrace(); 
					}
				} else if(s61.getCardType() != null && s61.getCardType().trim().equalsIgnoreCase("C")) {
					
					if(!StringUtils.isNumber(s61.getAmount())) {
						System.out.println("s61.getAmount() no es número: " + s61.getAmount());
						return;
					}
					
					try {
						
						if(new BigDecimal(s61.getAmount().replace(",", ".")).doubleValue() <= 0.0) {
//							System.out.println("Amount is zero: " + s61.getAmount().replace(",", "."));
							continue;
						}
						
						this.totalNumberCR = this.totalNumberCR + 1;
						this.totalCR = this.totalCR.add(new BigDecimal(s61.getAmount().replace(",", "."))); 
					} catch(Exception e2) {
						e2.printStackTrace(); 
					}
				}
			}
		}
		
//		this.totalDR = Long.parseLong(df2.format(this.totalDR));
//		this.totalCR = Long.parseLong(df2.format(this.totalCR));
		
	}
	
}
