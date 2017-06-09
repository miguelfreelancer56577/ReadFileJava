package com.walmart.tesop.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupBancomer {

	protected HashMap<String, String> transactions = null;
	
	protected Templates templete;
	
	public GroupBancomer(HashMap<String, String> transactions, Templates templete){
		
		this.transactions = transactions;
		
		this.templete = templete;
		
	}

	public HashMap<String, String> getTransactions() {
		return transactions;
	}

	public Templates getTemplete() {
		return templete;
	}
	
}
