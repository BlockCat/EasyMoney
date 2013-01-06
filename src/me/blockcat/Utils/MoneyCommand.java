package me.blockcat.Utils;

import java.text.DecimalFormat;

import org.bukkit.entity.Player;

public abstract class MoneyCommand {
	
	DecimalFormat df1 = new DecimalFormat("####.00");

	public abstract void execute(Player player, String[] args);


	public String formatMoney(double money) {
		return df1.format(money);
	}
}
