package me.blockcat.commands;

import me.blockcat.Configuration;
import me.blockcat.MoneyData;
import me.blockcat.Utils.MoneyCommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandBalance extends MoneyCommand {

	private String name = null;
	
	public CommandBalance() {
	}

	public CommandBalance(String name) {
		this.name = name;
	}

	@Override
	public void execute(Player player, String[] args) {
		if (name == null) {
			player.sendMessage(ChatColor.GREEN + "You have: " + formatMoney(MoneyData.getMoney(player.getName())) + " " + Configuration.currencyName);
		} else {
			player.sendMessage(ChatColor.GREEN + name + " has: " + formatMoney(MoneyData.getMoney(name)) + " " + Configuration.currencyName);
		}
	}

}
