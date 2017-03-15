package com.walmart.tesop.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.walmart.tesop.beans.StatementLine;
import com.walmart.tesop.beans.TransactionReferenceNumber20;

public class Repo2 extends WorkBookXls {

	public Repo2(String pathName) throws IOException {
		super(pathName);
	}
	
	@Override
	protected void createHeadInXls() {
		HSSFRow rowhead = sheet.createRow((short)0);
		rowhead.createCell(0).setCellValue("Account");
		rowhead.createCell(1).setCellValue("Transaction");
        rowhead.createCell(2).setCellValue("RefNum61");
        rowhead.createCell(3).setCellValue("RefNum86");
        rowhead.createCell(4).setCellValue("Comparison");
	}

	@Override
	protected void creteRowInXls(TransactionReferenceNumber20 trn20, int numRow) {
		
		String account = trn20.getAccountIdentification25();
		
		List<StatementLine> statementLineList = trn20.getStatementLineList();
		
		String equal = "they are equals";
		String noEqual = "they aren't equals";
		
		if(statementLineList != null){
			
			HSSFRow row = sheet.createRow((short)numRow);
			
			for (StatementLine statementLine : statementLineList) {
				
				if(statementLine.getSuplementaryDetails99() != null && statementLine.getSuplementaryDetails99().getBankCode() != null && statementLine.getSuplementaryDetails99().getBankCode().trim().equals("1700")){
					
					row.createCell(0).setCellValue(account);
					row.createCell(1).setCellValue("364");
			        row.createCell(2).setCellValue(statementLine.getStatementLine61().getAccountOwnerReference());
			        row.createCell(3).setCellValue(statementLine.getAccountOwnerInformation86().getBranchOperation());
			        
			        try {
						Integer reference61 = new Integer(statementLine.getStatementLine61().getAccountOwnerReference());
						Integer reference86 = new Integer(statementLine.getAccountOwnerInformation86().getBranchOperation());
						if(reference61.equals(reference86)){
							row.createCell(4).setCellValue(equal);
						}else{
							row.createCell(4).setCellValue(noEqual);
						}
					} catch (NumberFormatException e) {
						row.createCell(4).setCellValue(noEqual);
						System.out.println("Error to parse reference of the tag 61 or 86.");
					}
			        
				}
			}
			
		}
		
		
		
	}

}
