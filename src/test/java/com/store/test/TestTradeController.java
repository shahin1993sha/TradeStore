package com.store.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.controller.TradeController;
import com.model.Trade;
import com.service.TradeService;
import org.springframework.ui.Model;
 
public class TestTradeController {

	@InjectMocks
	TradeController tradeController;
	
	 @Mock
	 TradeService tradeService;
     
	 @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	 
	 
	 @Test
	 public void testViewTradeStore() {
			List<Trade> tradeList = new ArrayList<>();
			tradeList.add(new Trade("T1", 1, "CP-1", "B1", "20/05/2020", "21/03/2021", 'N'));
			tradeList.add(new Trade("T2", 2, "CP-2", "B1", "20/05/2021", "21/03/2021", 'N'));
			tradeList.add(new Trade("T2", 1, "CP-1", "B2", "20/05/2021", "14/03/2015", 'N'));
			tradeList.add(new Trade("T3", 3, "CP-3", "B2", "20/05/2014", "21/03/2021", 'Y'));
		
		 when(tradeService.ShowlistAllTrade()).thenReturn(tradeList);
	 
		 List<Trade> list= tradeService.ShowlistAllTrade();
		 assertEquals(4, list.size());
		 verify(tradeService, times(1)).ShowlistAllTrade();
	 }
	 
	 
	 @Test
	 public void testSaveTrade() {
			 
			Trade t=new Trade("T7", 1, "CP-1", "B1", "20/05/2020", "21/03/2021", 'N');
			tradeController.saveTrade(t);
		    verify(tradeService, times(1)).save(t);
	 }
}
