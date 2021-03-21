package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.model.Trade;

public class ValidateTrade {

	public ValidateTrade(){
		
	}
	 
	public int ValidVersion(Trade trade, List<Trade> tradeList) {
		
		//1 duplicate
		//2 invaid
		//3 valid
		String tradeId=trade.getTradeId();
		int version=trade.getVersion();
		int flag=0;
		 
		HashSet<Integer> set=new HashSet<>();
		
		for (Trade tradeFromList : tradeList) {
			if(tradeFromList.getTradeId().equals(tradeId))
			set.add(tradeFromList.getVersion());
		}
		 if(set.contains(version)) {
			 flag= 1;
			 return flag;
		 }
			
		// if(flag==0) {
		 for (Integer tradeVersion : set) {
			 
			 if(version < tradeVersion) {
				  flag= 2;
				  return flag;
			 }
		     
			 else if(version > tradeVersion)
			 {
				 flag= 3;
				 return flag;
			 }
			 
	}
		 
		return flag;
		
	 
	}

	//Validating Maturity Date with Created date
	public String ValidateMaturityDate(String Tdate,String Mdate) {
		 
		  String ErrMsg=""; 
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	      DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	      SimpleDateFormat  sdfo   = new SimpleDateFormat("yyyy-MM-dd"); 

		 try {
			    String d1=Mdate.replaceAll("/", "-");
				String d2=Tdate.replaceAll("/", "-");
				System.out.println(d1);
				System.out.println(d2);
				
				 Date dM = sdfo.parse(LocalDate.parse(d1, formatter).format(formatter2)); 
		         Date dC = sdfo.parse(LocalDate.parse(d2, formatter).format(formatter2)); 
		         
		         if (dM.compareTo(dC) < 0) {  
		        	    ErrMsg="MaturityDate is Before Todaydate";
			            System.out.println("MaturityDate is Before Todaydate"); 
			        }
			 
			        
		} catch (ParseException e) {
			 
			e.printStackTrace();
		}  
		return ErrMsg; 
	} 
	
	  
}
