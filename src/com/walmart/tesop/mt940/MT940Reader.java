package com.walmart.tesop.mt940;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.walmart.tesop.util.StringUtils;
import com.walmart.tesop.beans.OpeningBalance60F;
import com.walmart.tesop.beans.ClosingBalance62F;
import com.walmart.tesop.beans.ClosingAvailableBalance64;
import com.walmart.tesop.beans.StatementLine;
import com.walmart.tesop.beans.StatementLine61;
import com.walmart.tesop.beans.SuplementaryDetails99;
import com.walmart.tesop.beans.AccountOwnerInformation86;
import com.walmart.tesop.beans.TransactionReferenceNumber20;
import com.walmart.tesop.beans.LastReference;

/**
 * Operative Treasury - TESOP
 * WalMart Mexico y Centroamerica
 * Class name: MT940Reader
 * Class description: MT940 report reader class.
 */
public class MT940Reader {
	
	/*
	 *  20 ................ Transaction reference number
	 *  25 ................	Account identification
	 *  28 ................	Statement/page
	 *  60F	...............	Class: Opening Balance
	 *  61 ................	List - Class: Statement Line
	 *  	99 /CTC/ ......	List - Class: Suplementary details
	 *  	86 ............	List - Class: Account Owner Information
	 *  	100 ........... Second amount (Reference)
	 *  62F ...............	Class: Closing Balance
	 *  64 ................	Class: Closing Available Balance
	 * 
	 */
	
	public List<TransactionReferenceNumber20> readMT940() {
		boolean success = false;
		BufferedReader br = null;
		List<TransactionReferenceNumber20> objList = new ArrayList<TransactionReferenceNumber20>(1);

		try {
			String sCurrentLine;
			String value;
			String reference;
			br = new BufferedReader(new FileReader("C:\\Users\\vn0x53q\\workspaceKepler\\repoFiles\\MT940_HSBC_20170309"));
			
			TransactionReferenceNumber20 trn20 = new TransactionReferenceNumber20();
			StatementLine statementLine = null;
			
			trn20.setIsNew(true);
			int lastTagId = 0;
			LastReference lastReference = new LastReference();

			while (true) {
				
				sCurrentLine = br.readLine();
				
				if(sCurrentLine == null) {
					if(statementLine != null) {
						statementLine.calculateBranchOperation();
						if(statementLine.getAccountOwnerInformation86() != null)
							statementLine.getAccountOwnerInformation86().validateCurrency(trn20.getOpeningBalance60().getCurrencyCode(), statementLine.getSuplementaryDetails99().getBankCode());
						trn20.getStatementLineList().add(statementLine);
						trn20.calculateTotals();
						objList.add(trn20);
						System.out.println("Fin de archivo | objList.size: " + objList.size());
						return objList;
					}
				}
				
				if(sCurrentLine == null || sCurrentLine.trim().equalsIgnoreCase(""))
					continue;
				
				int tagId = this.recordTagidentifier(sCurrentLine, lastTagId);
				
				if(tagId == 0) {
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 20) {
					if(trn20 != null && trn20.getIsNew() == false) {
						if(statementLine != null) {
							statementLine.calculateBranchOperation();
							if(statementLine.getAccountOwnerInformation86() != null)
								statementLine.getAccountOwnerInformation86().validateCurrency(trn20.getOpeningBalance60().getCurrencyCode(), statementLine.getSuplementaryDetails99().getBankCode());
							trn20.getStatementLineList().add(statementLine);
							statementLine = new StatementLine();
							trn20.calculateTotals();
							objList.add(trn20);
						}
					}
					
					trn20 = new TransactionReferenceNumber20();
					trn20.setIsNew(false);
					value = sCurrentLine.substring(4, sCurrentLine.length());
					trn20.setTransactionReference20(value);
					lastTagId = tagId;
					continue;
				}

				if(tagId == 25) {
					value = sCurrentLine.substring(4, sCurrentLine.length());
					trn20.setAccountIdentification25(value);
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 28) {
					value = sCurrentLine.substring(4, sCurrentLine.length());
					trn20.setStatementPage28(value);
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 60) {
					value = sCurrentLine.substring(5, sCurrentLine.length());
					OpeningBalance60F openingBalance = this.getOpeningBalanceObj(value);
					trn20.setOpeningBalance60(openingBalance);
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 61) {
					if(statementLine == null) {
						statementLine = new StatementLine();
					} else if(statementLine.isThereStatementLine61() || 
							statementLine.isThereSuplementaryDetails99() || 
							statementLine.isThereAccountOwnerInformation86()) {
							statementLine.calculateBranchOperation();
							if(statementLine.getAccountOwnerInformation86() != null)
								statementLine.getAccountOwnerInformation86().validateCurrency(trn20.getOpeningBalance60().getCurrencyCode(), statementLine.getSuplementaryDetails99().getBankCode());
							trn20.getStatementLineList().add(statementLine);
							statementLine = new StatementLine();
					}
					
					value = sCurrentLine.substring(4, sCurrentLine.length());
					StatementLine61 statementLine61 = this.getStatementLineObj(value, lastReference);
					statementLine.setStatementLine61(statementLine61);
					statementLine.setThereStatementLine61(true);
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 99) {
					value = sCurrentLine.substring(0, sCurrentLine.length());
					SuplementaryDetails99 suplementaryDetails99 = this.getSuplementaryDetilsObj(value);
					statementLine.setSuplementaryDetails99(suplementaryDetails99);
					statementLine.setThereSuplementaryDetails99(true);
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 86) {
					value = sCurrentLine.substring(0, sCurrentLine.length());		
					AccountOwnerInformation86 accountOwnerInformation86 = this.getAccountOwnerInformationObj(value);
					statementLine.setAccountOwnerInformation86(accountOwnerInformation86);
					statementLine.setThereAccountOwnerInformation86(true);
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 101) {
					value = sCurrentLine.substring(0, sCurrentLine.length());
					
					if(statementLine.isThereAccountOwnerInformation86() == true) {
						reference = statementLine.getAccountOwnerInformation86().getReference();
						reference = new StringBuilder().append(reference).append(value).toString();
//						statementLine.getAccountOwnerInformation86().setReference(StringUtils.rPadAlphanumericReference(reference, 20));
						statementLine.getAccountOwnerInformation86().setReference(reference);
					}
						
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 100) {
					value = sCurrentLine.substring(0, sCurrentLine.length());
					
					if(statementLine.isThereAccountOwnerInformation86() == true) {
						reference = statementLine.getAccountOwnerInformation86().getReference(); 
						statementLine.getAccountOwnerInformation86().setTotalAmount(value.trim());
					}
					
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 62) {
					value = sCurrentLine.substring(5, sCurrentLine.length());
					ClosingBalance62F closingBalance62 = this.getClosingBalanceObj(value);
					trn20.setClosingBalance62(closingBalance62);
					lastTagId = tagId;
					continue;
				}
				
				if(tagId == 64) {
					value = sCurrentLine.substring(4, sCurrentLine.length());
					ClosingAvailableBalance64 closingAvailableBalance64 = this.getClosingAvailableBalanceObj(value);
					trn20.setClosingAvailableBalance64(closingAvailableBalance64);
					lastTagId = tagId;
					continue;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
//			return objList;
		} finally {
			try {
				if (br != null)br.close();
				System.out.println("BufferedReader closed.");
			} catch (IOException ex) {
				ex.printStackTrace();
//				return objList;
			}
		}
		
	    return objList;
	}
	
	private int recordTagidentifier(String currentLine, int lastTagId) {
		int tagId = 0;
		try {
			
			if(currentLine == null || currentLine.trim().equalsIgnoreCase(""))
				return 0;
			
			int  length = currentLine.length();
			
			if(length <= 1)
				return 0;
			
			if( (currentLine != null && currentLine.trim().contains("-") && currentLine.trim().length() == 1) || currentLine.trim().contains(":65:") )
				return 0;
			if(currentLine != null && currentLine.trim().contains(":20:"))
				return 20;
			if(currentLine != null && currentLine.trim().contains(":25:"))
				return 25;
			if(currentLine != null && currentLine.trim().contains(":28:"))
				return 28;
			if(currentLine != null && currentLine.trim().contains(":60F"))
				return 60;	
			if(currentLine != null && currentLine.trim().contains(":61:"))
				return 61;
			if(currentLine != null && currentLine.trim().contains(":62F"))
				return 62;
			if(currentLine != null && currentLine.trim().contains(":64:"))
				return 64;
			if(currentLine != null && currentLine.trim().contains(":86:"))
				return 86;
			if(currentLine != null && currentLine.trim().contains("/CTC"))
				return 99;
			if(currentLine != null && currentLine.trim().contains(","))
				return 100;
			if(currentLine != null && StringUtils.isString(currentLine))
				return 101;
				
			return tagId;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return tagId;
	}
	
	private AccountOwnerInformation86 getAccountOwnerInformationObj(String value) {
		AccountOwnerInformation86 aoi = null;
		try {
			
			aoi = new AccountOwnerInformation86();
			aoi.setProductTypeId(value.split("/")[1]);
			aoi.setProductType(value.split("/")[2]);
			 
			try {
				
				String branch = "";
					
//					Se limpia la cadena a solo un espacio entre cada palabra 
					branch = value.split("/")[4].replaceAll(StringUtils.hasTwoSpaces, " ");
					/*
					 * Se divide la cadena a partir de cada espacio en blanco entre cada palabra y se obtiene la primer posicion
					 * del arreglo remplazando todos los ceros a la izquierda de la referencia alfanumerica. 
					 */
					
					branch = branch.split(" ")[0].replaceAll(StringUtils.cerosIzquierda, "");
					
//					Se valida que la referencia alfanumerica no este vacia, que no contenga la palabra NONREF, que sea numerica y que sea mayor a 3
					if(branch == null || branch.trim().contains("NONREF") || branch.trim().equalsIgnoreCase("") || !StringUtils.isNumber(branch.trim()) || branch.length() < 3)
						aoi.setBranchOperation("NULL");
					else
						aoi.setBranchOperation(branch);
					
			} catch(Exception ex1) {
				aoi.setBranchOperation("NULL"); 
			}
			
			try { 
//				String[] values = value.split("/")[4].split("     ");
//				String[] values = value.split("/")[4].replaceAll(StringUtils.hasTwoSpaces, " ").split(" ");
//				if(numberSlash > 4){
//					String alpRef  = value.split("/")[4].replaceAll(StringUtils.hasTwoSpaces, " ");
//				}
				
				String alpRef = "";
				
				alpRef  = value.split("/")[4].replaceAll(StringUtils.hasTwoSpaces, " ").replaceAll(StringUtils.refeAtfirstIntoDescTag86, "");
				
				alpRef  = alpRef.replaceAll(StringUtils.refeAtfirstIntoDescTag86, "");
//	    		String alpRef = values[values.length-1];
//	    		StringBuilder sb = new StringBuilder("");
//	    		boolean initialSpace = true;
//	    		
//	    		if(values != null && !StringUtils.isNumber(alpRef)) {
//	    			for(int index = 0; index < alpRef.length(); index++) {
//	    				if(String.valueOf(alpRef.charAt(index)).equals(StringUtils.space) && initialSpace == true) {
//	    					continue;
//	    				} else {
//	    					sb.append(String.valueOf(alpRef.charAt(index)));
//	    					initialSpace = false;
//	    				}
//	    			}
//	    		} else {
//	    			sb.append(StringUtils.rPadAlphanumericReference(alpRef.trim(), 20));
//	    		}
	    		
	    		aoi.setReference(alpRef.trim());
				
			} catch(Exception ex2) { aoi.setReference(""); }
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return aoi;
	}
		
	private StatementLine61 getStatementLineObj(String value, LastReference lastReference) {
		StatementLine61 sl = null;
		try {
			
			sl = new StatementLine61();
			sl.setValueDate(value.substring(0, 6));
			sl.setEntryDate(value.substring(6, 10));
			sl.setCardType(value.substring(10, 11));
			sl.setFoundsCode(value.substring(11, 12));
			
//			if the foundsCode variable has a the third character of the Currency Code 
			
			if(sl.getFoundsCode() != null && !sl.getFoundsCode().trim().equalsIgnoreCase("N")) {
				
				StringBuilder sba = new StringBuilder("");
				
				/*
				 * Search into the variable value.
				 * Check if the concurrent character is a number, comma or point. If is correct add to sba variable.
				 * When the character is not number, comma or point, break out the loop, and setter amount value. 
				 * 
				 * 
				 */
				for(int k = 12; k < value.length(); k ++) {
					String v = value.substring(k, k+1);
					
					if(v != null && StringUtils.isPrice(v)) {
						sba.append(v);
					} else {
						break;
					}
				}
				
//				sl.setAmount(value.split(sl.getFoundsCode())[1].split("N")[0]);
				sl.setAmount(sba.toString());
//				Every is setter to "N"
				sl.setEntryMethod("N");
			} else if(sl.getFoundsCode() != null && sl.getFoundsCode().trim().equalsIgnoreCase("N")) {
//				he split the value in 3 parts and else gets its price, this value has a third character of the currency code  
				sl.setAmount(value.split("N")[1]);
//				Every is setter to "N"
				sl.setEntryMethod("N");
			} 
//			else {
//				System.out.println("getStatementLineObj.value: " + value);
//			}
			
			try {
				if(value.contains("INT")) {
					sl.setEntryReason("INT");
				} else {
//					the Method no setter the Entry Reason because It assume that is not Found Code
//					if it is into here, it has the third character currency code.
//					Assign null because not have two character "N", throw an Exception
					sl.setEntryReason(value.split("N")[2].substring(0, 3));
				}
			} catch(Exception e) {
//				Assign "INT" by default 
				sl.setEntryReason("INT");
			}
			try {
				if(value.contains("NONREF")) {				
					sl.setAccountOwnerReference("NULL");
					sl.setInheritedReference(lastReference.getLastReference());
				} else {
					try {
						String substring = value.split("N")[2].split("//")[0].substring(3, value.split("N")[2].split("//")[0].length());
						StringBuilder sbsb = new StringBuilder("");
						
						for(int i = substring.length()-1; i >= 0; i--) {
							
							if(StringUtils.isNumber(String.valueOf(substring.charAt(i)))) {
								sbsb.append(String.valueOf(substring.charAt(i)));
							} else {
//								throw new Tag61Exception("tag: " + value);
								continue;
							}
						}
						sl.setAccountOwnerReference(sbsb.reverse().toString());
					}
//					catch (Tag61Exception e) {
//						e.printStackTrace();
////						System.exit(0);
//					} 
					catch (Exception e) {
//						System.out.println("tag: " + value);
						String substring = value.split("N")[1].split("//")[0];
						substring = substring.substring(3, substring.length());
						if(StringUtils.isNumber(substring)){
							sl.setAccountOwnerReference(substring.replaceAll(StringUtils.noNumeric ,""));
						}else{
							sl.setAccountOwnerReference("NULL");
						}
//						System.out.println("sub-field: " + substring);
//						cleans up
//						System.out.println("accountOwnerReference: " + sl.getAccountOwnerReference() + "\n--------------------------------");
					}
					
					lastReference.setLastReference(sl.getAccountOwnerReference());
					sl.setInheritedReference("NULL");
					
//					sl.setAccountOwnerReference(value.split("N")[2].split("//")[0].substring(3, value.split("N")[2].split("//")[0].length()));
//					lastReference.setLastReference(sl.getAccountOwnerReference());
//					sl.setInheritedReference("NULL");
//					System.out.println("getStatementLineObj.lastRef = " + lastReference.getLastReference());
				}
			} catch(Exception exs) {
				sl.setAccountOwnerReference(lastReference.getLastReference());
			}
			sl.setInstitutionReference(value.split("//")[1]);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return sl;
	}
	
	private OpeningBalance60F getOpeningBalanceObj(String value) {
		OpeningBalance60F ob = null;
		try {
			
			ob = new OpeningBalance60F();
			ob.setCardType(value.substring(0, 1));
			ob.setBookingDate(value.substring(1, 7));
			ob.setCurrencyCode(value.substring(7, 10));
			ob.setOpeningLedgerBalanceAccount(value.substring(10, value.length()));
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return ob;
	}
	
	private ClosingBalance62F getClosingBalanceObj(String value) {
		ClosingBalance62F cb = null;
		try {
			
			cb = new ClosingBalance62F();
			cb.setCardType(value.substring(0, 1));
			cb.setBookingDate(value.substring(1, 7));
			cb.setCurrencyCode(value.substring(7, 10));
			cb.setOpeningLedgerBalanceAccount(value.substring(10, value.length()));
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return cb;
	}
	
	private ClosingAvailableBalance64 getClosingAvailableBalanceObj(String value) {
		ClosingAvailableBalance64 cab = null;
		try {
			
			cab = new ClosingAvailableBalance64();
			cab.setCardType(value.substring(0, 1));
			cab.setBookingDate(value.substring(1, 7));
			cab.setCurrencyCode(value.substring(7, 10));
			cab.setOpeningLedgerBalanceAccount(value.substring(10, value.length()));
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return cab;
	}
	
	private SuplementaryDetails99 getSuplementaryDetilsObj(String value) {
		SuplementaryDetails99 sd = null;
		try {
			
			sd = new SuplementaryDetails99();
			sd.setBankCodeId(value.split("/")[1]);
			sd.setBankCode(value.split("/")[2]);
			sd.setBankCodeDescription(value.split("/")[3]);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return sd;
	}
	
}
