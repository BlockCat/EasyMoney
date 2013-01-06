package me.blockcat;

import me.blockcat.Utils.MoneyFormat;

public class MoneyExe  {
	
	public static MoneyExe instance = null;
	public MoneyHandler handler = null;
	
	static {
		if (instance == null) {
			instance = new MoneyExe();
		}
	}
	
	private MoneyExe() {
		handler = new MoneyHandler();
	}
	
	public static MoneyExe getInstance() {
		return instance;
	}
	
	
	public void add(String player, double amount) {
		handler.add(player, amount);
	}
	
	public void substract(String player, double amount) {
		handler.substract(player, amount);
	}
	
	public boolean has(String player, double amount) {
		return getBalance(player) >= amount;
	}
	
	public double getBalance(String player) {
		return handler.getBalance(player);
	}
	
	public String getCurrencyName() {
		return Configuration.currencyName;
	}
	
	public double getDefaultBalance() {
		return Configuration.beginValue;
	}
	
	public String format(double amount) {
		return MoneyFormat.formatString(amount);		
	}

}
