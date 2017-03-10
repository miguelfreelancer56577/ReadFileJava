package com.walmart.tesop.beans;

public class ClosingAvailableBalance64 {
	
	private String cardType;
	private String bookingDate;
	private String currencyCode;
	private String openingLedgerBalanceAccount;
	
	public ClosingAvailableBalance64() {
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
		builder.append("ClosingAvailableBalance64 [cardType=");
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
