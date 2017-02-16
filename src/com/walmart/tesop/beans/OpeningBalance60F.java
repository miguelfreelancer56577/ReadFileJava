package com.walmart.tesop.beans;

public class OpeningBalance60F {
	
	private String cardType;
	private String bookingDate;
	private String currencyCode;
	private String openingLedgerBalanceAccount;
	
	public OpeningBalance60F() {
		super();
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getOpeningLedgerBalanceAccount() {
		return openingLedgerBalanceAccount;
	}
	public void setOpeningLedgerBalanceAccount(String openingLedgerBalanceAccount) {
		this.openingLedgerBalanceAccount = openingLedgerBalanceAccount==null?"0.0":openingLedgerBalanceAccount.replace(",", ".");
	}
	
	

}
