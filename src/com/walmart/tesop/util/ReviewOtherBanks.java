package com.walmart.tesop.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.walmart.tesop.beans.AccountOwnerInformation86;
import com.walmart.tesop.beans.ClosingAvailableBalance64;
import com.walmart.tesop.beans.ClosingBalance62F;
import com.walmart.tesop.beans.OpeningBalance60F;
import com.walmart.tesop.beans.StatementLine;
import com.walmart.tesop.beans.StatementLine61;
import com.walmart.tesop.beans.SuplementaryDetails99;
import com.walmart.tesop.beans.TransactionReferenceNumber20;

public class ReviewOtherBanks extends WorkBookXls {
	
	public int numRow = 1;

	public ReviewOtherBanks(String pathName) throws IOException {
		super(pathName);
	}
	
	@Override
	protected void createHeadInXls() {
		HSSFRow rowhead = sheet.createRow((short)0);
		rowhead.createCell(0).setCellValue("tag20");
		rowhead.createCell(1).setCellValue("tag25");
		rowhead.createCell(2).setCellValue("tag28");
        rowhead.createCell(3).setCellValue("tag60-cardType");
        rowhead.createCell(4).setCellValue("tag60-bookingDate");
        rowhead.createCell(5).setCellValue("tag60-currencyCode");
        rowhead.createCell(6).setCellValue("tag60-openingLedgerBalanceAccount");
        rowhead.createCell(7).setCellValue("tag62-cardType");
        rowhead.createCell(8).setCellValue("tag62-bookingDate");
        rowhead.createCell(9).setCellValue("tag62-currencyCode");
        rowhead.createCell(10).setCellValue("tag62-openingLedgerBalanceAccount");
        rowhead.createCell(11).setCellValue("tag64-cardType");
        rowhead.createCell(12).setCellValue("tag64-bookingDate");
        rowhead.createCell(13).setCellValue("tag64-currencyCode");
        rowhead.createCell(14).setCellValue("tag64-openingLedgerBalanceAccount");
        rowhead.createCell(15).setCellValue("tag61-valueDate");
        rowhead.createCell(16).setCellValue("tag61-entryDate");
        rowhead.createCell(17).setCellValue("tag61-cardType");
        rowhead.createCell(18).setCellValue("tag61-foundsCode");
        rowhead.createCell(19).setCellValue("tag61-amount");
        rowhead.createCell(20).setCellValue("tag61-entryMethod");
        rowhead.createCell(21).setCellValue("tag61-entryReason");
        rowhead.createCell(22).setCellValue("tag61-accountOwnerReference");
        rowhead.createCell(23).setCellValue("tag61-inheritedReference");
        rowhead.createCell(24).setCellValue("tag61-institutionReference");
        rowhead.createCell(25).setCellValue("tag61-movementType");
        rowhead.createCell(26).setCellValue("tag99-bankCodeId");
        rowhead.createCell(27).setCellValue("tag99-bankCode");
        rowhead.createCell(28).setCellValue("tag99-bankCodeDescription");
        rowhead.createCell(29).setCellValue("tag86-productTypeId");
        rowhead.createCell(30).setCellValue("tag86-productType");
        rowhead.createCell(31).setCellValue("tag86-branchOperation");
        rowhead.createCell(32).setCellValue("tag86-reference");
        rowhead.createCell(33).setCellValue("tag86-totalAmount");
        rowhead.createCell(34).setCellValue("equals reference");
        
        
	}

	@Override
	protected void creteRowInXls(TransactionReferenceNumber20 trn20, int numRow) {
		
		String tag20 = trn20.getTransactionReference20();
		String tag25 = trn20.getAccountIdentification25();
		String tag28 = trn20.getStatementPage28();
		OpeningBalance60F tag60F = trn20.getOpeningBalance60();
		ClosingBalance62F tag62F = trn20.getClosingBalance62();
		ClosingAvailableBalance64 tag64 = trn20.getClosingAvailableBalance64();
		
		List<StatementLine> statementLineList = trn20.getStatementLineList();
		
        if(statementLineList != null && statementLineList.size() > 0){
        	
        	for (StatementLine item : statementLineList) {
            	
        		HSSFRow rowhead = sheet.createRow((short) this.numRow++);
        		
        		addNewRow(rowhead, trn20);
        		
        		StatementLine61 tag61 = item.getStatementLine61();
        		
        		SuplementaryDetails99 tag99 = item.getSuplementaryDetails99();
        		
        		AccountOwnerInformation86 tag86 = item.getAccountOwnerInformation86();
        		
        		rowhead.createCell(15).setCellValue(tag61.getValueDate());
                rowhead.createCell(16).setCellValue(tag61.getEntryDate());
                rowhead.createCell(17).setCellValue(tag61.getCardType());
                rowhead.createCell(18).setCellValue(tag61.getFoundsCode());
                rowhead.createCell(19).setCellValue(tag61.getAmount());
                rowhead.createCell(20).setCellValue(tag61.getEntryMethod());
                rowhead.createCell(21).setCellValue(tag61.getEntryReason());
                rowhead.createCell(22).setCellValue(tag61.getAccountOwnerReference());
                rowhead.createCell(23).setCellValue(tag61.getInheritedReference());
                rowhead.createCell(24).setCellValue(tag61.getInstitutionReference());
                rowhead.createCell(25).setCellValue(tag61.getMovementType());
                
                rowhead.createCell(26).setCellValue(tag99.getBankCodeId());
                rowhead.createCell(27).setCellValue(tag99.getBankCode());
                rowhead.createCell(28).setCellValue(tag99.getBankCodeDescription());
                
                rowhead.createCell(29).setCellValue(tag86.getProductTypeId());
                rowhead.createCell(30).setCellValue(tag86.getProductType());
                rowhead.createCell(31).setCellValue(tag86.getBranchOperation());
                rowhead.createCell(32).setCellValue(tag86.getReference());
                rowhead.createCell(33).setCellValue(tag86.getTotalAmount());
                rowhead.createCell(34).setCellValue("no");
                
                String reference61 = tag61.getAccountOwnerReference();
                String reference86 = tag86.getBranchOperation();
                
                Matcher m = StringUtils.findPattern(reference86+"$", reference61);
                
                if(reference61.equals(reference86) || m.find() == true){
                	rowhead.createCell(34).setCellValue("si");
                }
                
    		}
        	
        }else{
        	
        	HSSFRow rowhead = sheet.createRow((short) this.numRow++);
    		
    		addNewRow(rowhead, trn20);
            
        }
			
	}
	
	protected void addNewRow(HSSFRow rowhead, TransactionReferenceNumber20 trn20){
		
		String tag20 = trn20.getTransactionReference20();
		String tag25 = trn20.getAccountIdentification25();
		String tag28 = trn20.getStatementPage28();
		OpeningBalance60F tag60F = trn20.getOpeningBalance60();
		ClosingBalance62F tag62F = trn20.getClosingBalance62();
		ClosingAvailableBalance64 tag64 = trn20.getClosingAvailableBalance64();
		
		rowhead.createCell(0).setCellValue(tag20);
		rowhead.createCell(1).setCellValue(tag25);
		rowhead.createCell(2).setCellValue(tag28);
		
        rowhead.createCell(3).setCellValue(tag60F.getCardType());
        rowhead.createCell(4).setCellValue(tag60F.getBookingDate());
        rowhead.createCell(5).setCellValue(tag60F.getCurrencyCode());
        rowhead.createCell(6).setCellValue(tag60F.getOpeningLedgerBalanceAccount());
        
        rowhead.createCell(7).setCellValue(tag62F.getCardType());
        rowhead.createCell(8).setCellValue(tag62F.getBookingDate());
        rowhead.createCell(9).setCellValue(tag62F.getCurrencyCode());
        rowhead.createCell(10).setCellValue(tag62F.getOpeningLedgerBalanceAccount());
        
        rowhead.createCell(11).setCellValue(tag64.getCardType());
        rowhead.createCell(12).setCellValue(tag64.getBookingDate());
        rowhead.createCell(13).setCellValue(tag64.getCurrencyCode());
        rowhead.createCell(14).setCellValue(tag64.getOpeningLedgerBalanceAccount());
		
	}
		

}
