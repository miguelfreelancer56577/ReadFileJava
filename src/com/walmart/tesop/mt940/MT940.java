package com.walmart.tesop.mt940;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.walmart.tesop.util.Repo1;
import com.walmart.tesop.util.StringUtils;
import com.walmart.tesop.beans.StatementLine;
import com.walmart.tesop.beans.TransactionReferenceNumber20;
import com.walmart.tesop.beans.UpdateAccountBalanceMt940;
import com.walmart.tesop.beans.InsertMovementMt940;

/**
 * Operative Treasury - TESOP
 * WalMart Mexico y Centroamerica
 * Class name: MT940
 * Class description: Test class.
 */
public class MT940 {

	public static void main(String[] args) {
		try {
			MT940Reader reader = new MT940Reader();
			
			StringBuilder content = new StringBuilder("");
			File file = new File("C:\\Users\\vn0x53q\\logs\\MT940.log");

			if (!file.exists()) {
				file.createNewFile();
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String today = sdf.format(new Date());
			
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			String shortToday = "2016-10-19";//sdf2.format(new Date()); 
			
			List<TransactionReferenceNumber20> objList = reader.readMT940();
			UpdateAccountBalanceMt940 ab;
			InsertMovementMt940 im;
			
			System.out.println("objList: " + objList.size());
			
			StringBuilder xlsFileName = new StringBuilder("C:\\Users\\vn0x53q\\workspaceKepler\\reports\\");
			xlsFileName.append("reportTag25BANCOMER");
			xlsFileName.append(".xls");
			
			Repo1 repo1 = new Repo1(xlsFileName.toString());
			repo1.setSheetName("BANCOMER");
			repo1.setCellHeadName("Accounts");
			repo1.createBodyXls(objList);
			repo1.createFileXls();
			
			for(TransactionReferenceNumber20 trn20 : objList) {
				
				ab = new UpdateAccountBalanceMt940();
				ab.setMovement_dt(StringUtils.formatShortDate(trn20.getOpeningBalance60().getBookingDate()));
				ab.setOpening_bal(StringUtils.string2BigDecimal((trn20.getOpeningBalance60().getOpeningLedgerBalanceAccount())));
				ab.setClosing_bal(StringUtils.string2BigDecimal(trn20.getClosingAvailableBalance64().getOpeningLedgerBalanceAccount()));
				ab.setCalc_opening_bal(StringUtils.string2BigDecimal(trn20.getOpeningBalance60().getOpeningLedgerBalanceAccount()));
				ab.setCalc_closing_bal(StringUtils.string2BigDecimal(trn20.getClosingAvailableBalance64().getOpeningLedgerBalanceAccount()));
				ab.setTot_num_cr(trn20.getTotalNumberCR());
				ab.setTot_cr(trn20.getTotalCR());
				ab.setTot_num_dr(trn20.getTotalNumberDR());
				ab.setTot_dr(trn20.getTotalDR());
				ab.setTot_num_calc_cr(0);
				ab.setTot_calc_cr(0);
				ab.setTot_num_calc_dr(0);
				ab.setTot_calc_dr(0);
				ab.setTot_num_cash_dep(0);
				ab.setTot_cash_dep(0);
				ab.setTot_other_form_dep(0);
				ab.setTot_dep(0);
				ab.setMovement_status("NO");
				ab.setBal_status("NO");
				ab.setAccount_inf_status("NO");
				ab.setAuthorize_status("NO");
				ab.setOverdraft_status("NO");
				ab.setUser_authorize("NULL");
				ab.setUser_modify("NULL");
				ab.setUser_id("MPROTSP");
				ab.setLast_update(today);
				ab.setAccount_number(trn20.getAccountIdentification25().substring(4, trn20.getAccountIdentification25().length()));
				ab.setMovement_dt2(StringUtils.formatShortDate(trn20.getOpeningBalance60().getBookingDate()));
				
				content.append("\n UPDATE 'informix'.ACCOUNT_BALANCE ");
				content.append("   SET movement_dt = '").append(ab.getMovement_dt()).append("', ");
				content.append("       opening_bal = ").append(ab.getOpening_bal()).append(", ");
				content.append("       closing_bal = ").append(ab.getClosing_bal()).append(", ");
				content.append("       calc_opening_bal = ").append(ab.getCalc_opening_bal()).append(", ");
				content.append("       calc_closing_bal = ").append(ab.getCalc_closing_bal()).append(", ");
				content.append("       tot_num_cr = ").append(ab.getTot_num_cr()).append(", ");
				content.append("       tot_cr = ").append(ab.getTot_cr()).append(", ");
				content.append("       tot_num_dr = ").append(ab.getTot_num_dr()).append(", ");
				content.append("       tot_dr = ").append(ab.getTot_dr()).append(", ");
				content.append("       tot_num_calc_cr = ").append(ab.getTot_num_calc_cr()).append(", ");
				content.append("       tot_calc_cr = ").append(ab.getTot_calc_cr()).append(", ");
				content.append("       tot_num_calc_dr = ").append(ab.getTot_num_calc_dr()).append(", ");
				content.append("       tot_calc_dr = ").append(ab.getTot_calc_dr()).append(", ");
				content.append("       tot_num_cash_dep = ").append(ab.getTot_num_cash_dep()).append(", ");
				content.append("       tot_cash_dep = ").append(ab.getTot_cash_dep()).append(", ");
				content.append("       tot_other_form_dep = ").append(ab.getTot_other_form_dep()).append(", ");
				content.append("       tot_dep = ").append(ab.getTot_dep()).append(", ");
				content.append("       movement_status = '").append(ab.getMovement_status()).append("', ");
				content.append("       bal_status = '").append(ab.getBal_status()).append("', ");
				content.append("       account_inf_status = '").append(ab.getAccount_inf_status()).append("', ");
				content.append("       authorize_status = '").append(ab.getAuthorize_status()).append("', ");
				content.append("       overdraft_status = '").append(ab.getOverdraft_status()).append("', ");
				content.append("       user_authorize = ").append(ab.getUser_authorize()).append(", ");
				content.append("       user_modify = ").append(ab.getUser_modify()).append(", ");
				content.append("       user_id = '").append(ab.getUser_id()).append("', ");
				content.append("       last_update = '").append(ab.getLast_update()).append("' ");
				content.append(" WHERE account_number = '").append(ab.getAccount_number()).append("' ");
				content.append("   AND movement_dt = '").append(ab.getMovement_dt2()).append("'; ");
				
				String branch = "NULL";
				String subBranch1 = "NULL";
				String subBranch2 = "NULL";
				String reference = "NULL";
							
				for(StatementLine sl : trn20.getStatementLineList()) {
					if(sl != null && sl.isThereStatementLine61() == true) {
																	
						branch = sl.isThereAccountOwnerInformation86()?sl.getAccountOwnerInformation86().validateBranchOperation():"NULL";
						reference = "NULL";
						reference = (sl.isThereStatementLine61() && StringUtils.isNumber(sl.getStatementLine61().getAccountOwnerReference()))?sl.getStatementLine61().getAccountOwnerReference():"NULL";       
						
						im = new InsertMovementMt940();
						im.setAccount_number(String.valueOf(Integer.parseInt(trn20.getAccountIdentification25().substring(4, trn20.getAccountIdentification25().length()))));
						im.setMovement_dt(StringUtils.formatShortDate(trn20.getOpeningBalance60().getBookingDate()));
						im.setMovement_dt2(StringUtils.formatShortDate(trn20.getOpeningBalance60().getBookingDate()));
						im.setConcil_folio(0);
						im.setBanking_code(sl.getSuplementaryDetails99().getBankCode());
						im.setMovement_type(sl.getStatementLine61().getMovementType());
						im.setAmount(StringUtils.string2BigDecimal(sl.getStatementLine61().getAmount()));
						
						if(reference.trim().equals("NULL")) {
							if(sl.isThereAccountOwnerInformation86()) {
								reference = sl.getAccountOwnerInformation86().validateBranchOperation();
							}
							
							if(reference.trim().equals("NULL")) {
								if(sl.isThereStatementLine61() && !sl.getStatementLine61().getInheritedReference().trim().equals("NULL")) {
//									reference = sl.getStatementLine61().getInheritedReference();
									reference = "0000000000";
								}
							}
						}
						
						if(StringUtils.isNumber(reference)) {
							im.setReference(StringUtils.lPadNumericReference(reference, 10, 10));
						} else {
							im.setReference(reference);
						}
						
						im.setBranch_operation(sl.getBranchOperation());
						im.setReference_desc((!sl.isThereAccountOwnerInformation86() || sl.getAccountOwnerInformation86().getReference()==null)?"NULL":sl.getAccountOwnerInformation86().getReference());
						im.setConcil_status(3);
						im.setEcoupon_snd_status("PE");
						im.setConf_snd_status("PE");
						im.setSap_snd_status("PE");
						im.setChk_status("NO");
						im.setAccounting_status("PE");
						im.setUser_id("MPROTSP");
						im.setLast_update(today);
						im.setReference_left0(branch);
						
						try {
							
							if(branch != null && !branch.trim().equalsIgnoreCase("NULL") && branch.length() >= 4)
								subBranch1 = branch.substring(0, 4);
							else 
								subBranch1 = branch;
											
							im.setReference_first4(subBranch1);
							
						} catch(Exception e10) {
							im.setReference_first4("NULL");
						}
						
						try {
							
							if(branch != null && !branch.trim().equalsIgnoreCase("NULL") && branch.length() >= 5)
								subBranch2 = branch.substring(branch.length()-5, branch.length());
							else 
								subBranch2 = branch;
							
							im.setReference_last6(subBranch2);
							
						} catch(Exception ex11) {
							im.setReference_last6("NULL");
						}
												
						content.append("\n INSERT INTO 'informix'.MOVEMENT (movement_id, account_bal_id, movement_dt, concil_folio, banking_code, movement_type, amount,");
						content.append("reference, branch_operation, reference_desc, concil_status, ecoupon_snd_status, conf_snd_status, sap_snd_status,");
						content.append("chk_status, accounting_status, user_id, last_update, reference_left0, reference_first4, reference_last6) VALUES(0,"); 
						content.append("(SELECT account_bal_id FROM 'informix'.ACCOUNT_BALANCE where account_number = ");
						content.append("'").append(im.getAccount_number()).append("'").append(" AND movement_dt = ");
						content.append("'").append(im.getMovement_dt()).append("'), ");
						content.append("'").append(im.getMovement_dt2()).append("', ");
						content.append(im.getConcil_folio()).append(", ");
						content.append("'").append(im.getBanking_code()).append("', ");
						content.append(im.getMovement_type()).append(", ");
						content.append(im.getAmount()).append(", ");
						content.append("'").append(im.getReference()).append("', ");
						content.append("'").append(im.getBranch_operation()).append("', ");
						content.append("'").append(im.getReference_desc()).append("', ");
						content.append(im.getConcil_status()).append(", ");
						content.append("'").append(im.getEcoupon_snd_status()).append("', ");
						content.append("'").append(im.getConf_snd_status()).append("', ");
						content.append("'").append(im.getSap_snd_status()).append("', ");
						content.append("'").append(im.getChk_status()).append("', ");
						content.append("'").append(im.getAccounting_status()).append("', ");
						content.append("'").append(im.getUser_id()).append("', ");
						content.append("'").append(im.getLast_update()).append("', ");
						content.append("'").append(im.getReference_left0()).append("', ");
						content.append("'").append(im.getReference_first4()).append("', ");
						content.append("'").append(im.getReference_last6()).append("'");
						content.append(");");
						
						
					}
				}
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content.toString());
			bw.close();
			
			System.out.println(content.toString());
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
