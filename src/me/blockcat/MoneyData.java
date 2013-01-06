package me.blockcat;


public class MoneyData {

	public static double getMoney(String player) {
		Account account = new Account(player);
		return account.getMoney();
	}

	public static void substract(String player, double amount) {
		Account account = new Account(player);
		account.substract(amount);
	}
	
	public static void add(String player, double amount ) {
		Account account = new Account(player);
		account.add(amount);
	}

	public static void load() {
		
	}

	
}
