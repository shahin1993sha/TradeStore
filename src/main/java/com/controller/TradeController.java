package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.Trade;
import com.service.TradeService;

@Controller
public class TradeController {

	@Autowired
	public TradeService tradeService;

	@RequestMapping("/list")
	public String viewTradeStore(Model model) {
		List<Trade> listOfTrades = tradeService.ShowlistAllTrade(); 
		model.addAttribute("listOfTrades", listOfTrades); 
		return "index";
	}
	
	
	@RequestMapping("/new")
	public String showNewTradePage(Model model) {
	    Trade trade = new Trade();
	    model.addAttribute("trade", trade); 
	    return "new_trade";
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveTrade(@ModelAttribute("trade") Trade trade) {
		tradeService.save(trade); 
	    return "redirect:/list";
	}
}
