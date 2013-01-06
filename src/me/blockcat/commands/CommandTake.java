package me.blockcat.commands;

import me.blockcat.MoneyData;
import me.blockcat.Utils.MoneyCommand;
import me.blockcat.Utils.MoneyFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandTake extends MoneyCommand{

	@Override
	public void execute(Player player, String[] args) {
		if (args.length < 3) {
			player.sendMessage(ChatColor.GREEN + "/money give <Player> <amount>");
			return;
		}
		//remember: args[0] is the command executor: 'give'
		Player target = Bukkit.getPlayerExact(args[1]);
		double amount = 0.0D;
		
		if (target == null) {
			player.sendMessage(ChatColor.RED + "Player not found.");
			return;
		}

		try {
			amount = Double.parseDouble(args[2]);
			amount = MoneyFormat.formatDouble(amount);
			if (amount <= 0) throw new Exception();
		} catch (Exception e) {
			player.sendMessage(ChatColor.RED + "Invalid amount.");
			return;
		}
		
		MoneyData.substract(target.getName(), amount);
		
		player.sendMessage(ChatColor.GREEN + "You took: " + ChatColor.WHITE + MoneyFormat.formatString(amount) + ChatColor.GREEN + " from: " + ChatColor.WHITE + target.getDisplayName());
		target.sendMessage(ChatColor.GREEN + "You lost: " + ChatColor.WHITE + MoneyFormat.formatString(amount) + ChatColor.GREEN + " to: " + ChatColor.WHITE + player.getDisplayName());
		
	}

}
