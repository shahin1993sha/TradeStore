package com.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.InvalidVersionException;
import com.model.Trade;
import com.util.ValidateTrade;

@Service
public class TradeService {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
 
	Calendar cal = Calendar.getInstance();

	List<Trade> tradeList = new ArrayList<>();

	private ValidateTrade validateTrade = new ValidateTrade();

	 
	// Show all Trades Comming
	public List<Trade> ShowlistAllTrade() {

		// populateDefaultDummyDataList;
		cal.set(2020, 04, 20);
		Date Mdate1 = cal.getTime();
        
		cal.set(2021, 04, 20);
		Date Mdate23 = cal.getTime();

		cal.set(2014, 04, 20);
		Date Mdate3 = cal.getTime();

		Date TodayDate = new Date();
	 
		cal.set(2015, 02, 14);
		Date Cdate2 = cal.getTime();

		if (tradeList.size() == 0) {
			
			tradeList.add(new Trade("T1", 1, "CP-1", "B1", formatter.format(Mdate1), formatter.format(TodayDate), 'N'));
			tradeList.add(new Trade("T2", 2, "CP-2", "B1", formatter.format(Mdate23), formatter.format(TodayDate), 'N'));
			tradeList.add(new Trade("T2", 1, "CP-1", "B2", formatter.format(Mdate23), formatter.format(Cdate2), 'N'));
			tradeList.add(new Trade("T3", 3, "CP-3", "B2", formatter.format(Mdate3), formatter.format(TodayDate), 'Y'));
		}

		// Update Expire flag if trade crosses the maturity date.
		if(tradeList!=null) {
		for (Trade trade : tradeList) {
			
			String flag=validateTrade.ValidateMaturityDate(formatter.format(TodayDate),trade.getMaturityDate());
			if(flag.length() > 0)
			{
				trade.setExpired('Y');
			}
		}
	}

		return tradeList;

	}
 

	// add New Trade
	public void save(Trade trade) {

		Date todayDate = new Date();
		int flag;
		String errorMessage = "";
		String Tdate = formatter.format(todayDate);
		String Mdate = trade.getMaturityDate();
		try {

			flag = validateTrade.ValidVersion(trade, tradeList);

			//System.out.println(flag);

			errorMessage = validateTrade.ValidateMaturityDate(Tdate, Mdate);

			if (flag == 2) {
				throw new InvalidVersionException("Invalid Trade Version");
			}
			if (errorMessage.length() > 0) {
				System.out.println(errorMessage);
			}
			if (flag == 1) {
				// Overide Existing Trade
				int count = 0;
				for (Trade trade2 : tradeList) {

					if (trade.getTradeId().equals(trade2.getTradeId()) && trade.getVersion() == trade2.getVersion()) {
						count++;
					}
				}
				tradeList.remove(count - 1);

				tradeList.add(new Trade(trade.getTradeId(), trade.getVersion(), trade.getCounterPartyId(),
						trade.getBookId(), formatter.format(todayDate), trade.getMaturityDate(), 'N'));
			}

			else if (errorMessage == "") {
				tradeList.add(new Trade(trade.getTradeId(), trade.getVersion(), trade.getCounterPartyId(),
						trade.getBookId(), formatter.format(todayDate), trade.getMaturityDate(), 'N'));
			}

		} catch (Exception e) {
			//System.out.println(e.getMessage());
			System.out.println("Exception occured: "+e);
		}

	}

}
