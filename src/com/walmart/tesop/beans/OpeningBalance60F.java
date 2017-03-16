package com.walmart.tesop.beans;

/**
 * Operative Treasury - TESOP
 * WalMart Mexico y Centroamerica
 * Class name: 		  OpeningBalance60F
 * Class description: Contiene los atributos de la sección Opening Balance del reporte MT940.
 * Last Modification: 15/11/2016
 * Last Date:         15/11/2016
 */

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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OpeningBalance60F [cardType=");
		builder.append(cardType);
		builder.append(", bookingDate=");
		builder.append(bookingDate);
		builder.append(", currencyCode=");
		builder.append(currencyCode);
		builder.append(", openingLedgerBalanceAccount=");
		builder.append(openingLedgerBalanceAccount);
		builder.append("]");
		return builder.toString();
	}
	
	

}
