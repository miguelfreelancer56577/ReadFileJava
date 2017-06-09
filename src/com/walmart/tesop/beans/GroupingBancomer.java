package com.walmart.tesop.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupingBancomer {
	
	protected List<GroupBancomer> groupings = new ArrayList<GroupBancomer>();
	
	protected static GroupingBancomer groupingBancomer;
	
	protected GroupingBancomer(){
		
		HashMap<String, String> transactions = new HashMap<String, String> ();
		
		transactions.put("V04","DEVOLUCION TARJETAS(En Mt940)/ CREDITO ELECTRONICO(BE)");
		transactions.put("V02","COMISION TARJETAS");
		transactions.put("V03","IVA COMISION TARJETAS");
		transactions.put("V06","IVA COMISION DEVOLUCION");
		transactions.put("V01","VENTAS TARJETAS BANCARIAS");
		transactions.put("T69","IVA COM VTAS TDD BANCOMER");
		transactions.put("T68","COM VTAS TDD BANCOME");
		transactions.put("T67","VENTAS TDD BANCOMER");
		transactions.put("V40","CUOTA TRANSACCION EXITOSA");
		transactions.put("V41","IVA TRANSACCION EXITOSA");
		transactions.put("T76","VENTAS TDC OTROS BANCOS");
		transactions.put("V45","VENTAS CREDITO");
		transactions.put("T77","COM VTA TDC OTROS BANCOS");
		transactions.put("T78","IVA COM VTA TDC OTROS BCO");
		transactions.put("T91","VENTAS PUNTOS TDC BANCOME");
		transactions.put("T70","VENTAS TDC BANCOMER");
		transactions.put("NONREF","PAGARE TEF DEVUELTO.");
		transactions.put("T92","COM VTA PUNTOS TDC BANCOM");
		transactions.put("T93","IVA COM VTA PUNTOS BCMER");
		transactions.put("T72","IVA COM VTAS TDC BANCOMER");
		transactions.put("T71","COM VTAS TDC BANCOMER    ");
		transactions.put("C02","DEPOSITO EN EFECTIVO");
		transactions.put("N06","PAGO CUENTA DE TERCERO");
		
		groupings.add(new GroupBancomer(transactions, Templates.BCMl2));
		
		transactions = new HashMap<String, String> ();
		
		transactions.put("T17","SPEI ENVIADO BANAMEX");
		transactions.put("W02","DEPOSITO DE TERCERO");
		
		groupings.add(new GroupBancomer(transactions, Templates.BCMl3));
		
		transactions = new HashMap<String, String> ();
		
		transactions.put("Y01","CE");
		transactions.put("Y05","CB");
		transactions.put("Y15","CE");
		
		groupings.add(new GroupBancomer(transactions, Templates.BCMl4));
		
		transactions = new HashMap<String, String> ();
		
		transactions.put("T20","SPEI RECIBIDOBANAMEX");
		
		groupings.add(new GroupBancomer(transactions, Templates.BCMl5));
		
	}
	
	public static GroupingBancomer getConfigurador() {
		
		if(groupingBancomer == null){
			groupingBancomer = new GroupingBancomer(); 
		}
		
		return groupingBancomer;
		 
	 }

	public List<GroupBancomer> getGroupings() {
		return groupings;
	}

}
