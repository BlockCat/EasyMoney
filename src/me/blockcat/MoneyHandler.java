package me.blockcat;

import java.util.ArrayList;
import java.util.List;

public class MoneyHandler {
	
	List<String> unsavedAccounts = new ArrayList<String>();

	public double getBalance(String player) {
		return MoneyData.getMoney(player);
	}

	public void substract(String player, double amount) {
		MoneyData.substract(player, amount);
	}

	public void add(String player, double amount) {
		MoneyData.add(player, amount);		
	}
}
