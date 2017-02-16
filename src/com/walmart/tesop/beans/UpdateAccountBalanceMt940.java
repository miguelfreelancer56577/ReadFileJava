package com.walmart.tesop.beans;

import java.math.BigDecimal;
import com.walmart.tesop.util.StringUtils;

public class UpdateAccountBalanceMt940 {
	
	private String movement_dt;
	private BigDecimal opening_bal;
	private BigDecimal closing_bal;
	private BigDecimal calc_opening_bal;
	private BigDecimal calc_closing_bal;
	private int tot_num_cr;
	private BigDecimal tot_cr;
	private int tot_num_dr;
	private BigDecimal tot_dr;
	private int tot_num_calc_cr; // 0
	private int tot_calc_cr; // 0
	private int tot_num_calc_dr; // 0
	private int tot_calc_dr; // 0
	private int tot_num_cash_dep; // 0
	private int tot_cash_dep; // 0
	private int tot_other_form_dep; // 0
	private int tot_dep; // 0
	private String movement_status; // NO
	private String bal_status; // NO
	private String account_inf_status; // NO
	private String authorize_status; // NO
	private String overdraft_status; // NO
	private String user_authorize; // NULL
	private String user_modify; // NULL
	private String user_id; // MPROTSP
	private String last_update; // today yyyy-mm-dd
	private String account_number; // Integer.parseInt(trn20.getAccountIdentification25().substring(4, trn20.getAccountIdentification25().length()))
	private String movement_dt2; //
	
	public UpdateAccountBalanceMt940() {
		super();
	}
	public String getMovement_dt() {
		return movement_dt;
	}
	public void setMovement_dt(String movement_dt) {
		this.movement_dt = movement_dt;
	}
	public BigDecimal getOpening_bal() {
		return opening_bal;
	}
	public void setOpening_bal(BigDecimal opening_bal) {
		this.opening_bal = opening_bal;
	}
	public BigDecimal getClosing_bal() {
		return closing_bal;
	}
	public void setClosing_bal(BigDecimal closing_bal) {
		this.closing_bal = closing_bal;
	}
	public BigDecimal getCalc_opening_bal() {
		return calc_opening_bal;
	}
	public void setCalc_opening_bal(BigDecimal calc_opening_bal) {
		this.calc_opening_bal = calc_opening_bal;
	}
	public BigDecimal getCalc_closing_bal() {
		return calc_closing_bal;
	}
	public void setCalc_closing_bal(BigDecimal calc_closing_bal) {
		this.calc_closing_bal = calc_closing_bal;
	}
	public int getTot_num_cr() {
		return tot_num_cr;
	}
	public void setTot_num_cr(int tot_num_cr) {
		this.tot_num_cr = tot_num_cr;
	}
	public BigDecimal getTot_cr() {
		return tot_cr;
	}
	public void setTot_cr(BigDecimal tot_cr) {
		this.tot_cr = tot_cr;
	}
	public int getTot_num_dr() {
		return tot_num_dr;
	}
	public void setTot_num_dr(int tot_num_dr) {
		this.tot_num_dr = tot_num_dr;
	}
	public BigDecimal getTot_dr() {
		return tot_dr;
	}
	public void setTot_dr(BigDecimal tot_dr) {
		this.tot_dr = tot_dr;
	}
	public int getTot_num_calc_cr() {
		return tot_num_calc_cr;
	}
	public void setTot_num_calc_cr(int tot_num_calc_cr) {
		this.tot_num_calc_cr = tot_num_calc_cr;
	}
	public int getTot_calc_cr() {
		return tot_calc_cr;
	}
	public void setTot_calc_cr(int tot_calc_cr) {
		this.tot_calc_cr = tot_calc_cr;
	}
	public int getTot_num_calc_dr() {
		return tot_num_calc_dr;
	}
	public void setTot_num_calc_dr(int tot_num_calc_dr) {
		this.tot_num_calc_dr = tot_num_calc_dr;
	}
	public int getTot_calc_dr() {
		return tot_calc_dr;
	}
	public void setTot_calc_dr(int tot_calc_dr) {
		this.tot_calc_dr = tot_calc_dr;
	}
	public int getTot_num_cash_dep() {
		return tot_num_cash_dep;
	}
	public void setTot_num_cash_dep(int tot_num_cash_dep) {
		this.tot_num_cash_dep = tot_num_cash_dep;
	}
	public int getTot_cash_dep() {
		return tot_cash_dep;
	}
	public void setTot_cash_dep(int tot_cash_dep) {
		this.tot_cash_dep = tot_cash_dep;
	}
	public int getTot_other_form_dep() {
		return tot_other_form_dep;
	}
	public void setTot_other_form_dep(int tot_other_form_dep) {
		this.tot_other_form_dep = tot_other_form_dep;
	}
	public int getTot_dep() {
		return tot_dep;
	}
	public void setTot_dep(int tot_dep) {
		this.tot_dep = tot_dep;
	}
	public String getMovement_status() {
		return movement_status;
	}
	public void setMovement_status(String movement_status) {
		this.movement_status = movement_status;
	}
	public String getBal_status() {
		return bal_status;
	}
	public void setBal_status(String bal_status) {
		this.bal_status = bal_status;
	}
	public String getAccount_inf_status() {
		return account_inf_status;
	}
	public void setAccount_inf_status(String account_inf_status) {
		this.account_inf_status = account_inf_status;
	}
	public String getAuthorize_status() {
		return authorize_status;
	}
	public void setAuthorize_status(String authorize_status) {
		this.authorize_status = authorize_status;
	}
	public String getOverdraft_status() {
		return overdraft_status;
	}
	public void setOverdraft_status(String overdraft_status) {
		this.overdraft_status = overdraft_status;
	}
	public String getUser_authorize() {
		return user_authorize;
	}
	public void setUser_authorize(String user_authorize) {
		this.user_authorize = user_authorize;
	}
	public String getUser_modify() {
		return user_modify;
	}
	public void setUser_modify(String user_modify) {
		this.user_modify = user_modify;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getLast_update() {
		return last_update;
	}
	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getMovement_dt2() {
		return movement_dt2;
	}
	public void setMovement_dt2(String movement_dt2) {
		this.movement_dt2 = movement_dt2;
	}
	
	
//		content.append("   SET movement_dt = '").append(trn20.getOpeningBalance60().getBookingDate()).append("', ");
//		content.append("       opening_bal = ").append(trn20.getOpeningBalance60().getOpeningLedgerBalanceAccount()).append(", "); 
//		content.append("       closing_bal = ").append(trn20.getClosingAvailableBalance64().getOpeningLedgerBalanceAccount()).append(", ");
//		content.append("       calc_opening_bal = ").append(trn20.getOpeningBalance60().getOpeningLedgerBalanceAccount()).append(", "); 
//		content.append("       calc_closing_bal = ").append(trn20.getClosingAvailableBalance64().getOpeningLedgerBalanceAccount()).append(", ");
//		content.append("       tot_num_cr = ").append(trn20.getTotalNumberCR()).append(", ");
//		content.append("       tot_cr = ").append(trn20.getTotalCR()).append(", ");
//		content.append("       tot_num_dr = ").append(trn20.getTotalNumberDR()).append(", ");
//		content.append("       tot_dr = ").append(trn20.getTotalDR()).append(", ");
//		content.append("       tot_num_calc_cr = 0, "); 
//		content.append("       tot_calc_cr = 0, ");
//		content.append("       tot_num_calc_dr = 0, "); 
//		content.append("       tot_calc_dr = 0, ");
//		content.append("       tot_num_cash_dep = 0, "); 
//		content.append("       tot_cash_dep = 0, ");
//		content.append("       tot_other_form_dep = 0, ");
//		content.append("       tot_dep = 0, ");
//		content.append("       movement_status = 'NO', "); 
//		content.append("       bal_status = 'NO', ");
//		content.append("       account_inf_status = 'NO', "); 
//		content.append("       authorize_status = 'NO', ");
//		content.append("       overdraft_status = 'NO', ");
//		content.append("       user_authorize = NULL, ");
//		content.append("       user_modify = NULL, ");
//		content.append("       user_id = 'MPROTSP', ");
//	content.append("       last_update = '").append(today).append("' ");
//	content.append(" WHERE account_number = '").append(Integer.parseInt(trn20.getAccountIdentification25().substring(4, trn20.getAccountIdentification25().length()))).append("'");
//	content.append("   AND movement_dt = '2016-10-19';");			

}
