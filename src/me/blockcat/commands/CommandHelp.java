package me.blockcat.commands;

import me.blockcat.EasyMoney;
import me.blockcat.MoneyCommandHandler.Commands;
import me.blockcat.Utils.MoneyCommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandHelp extends MoneyCommand{

	@Override
	public void execute(Player player, String[] args) {
		player.sendMessage(ChatColor.BLUE + "~~~" + ChatColor.GREEN + "EasyMoney" + ChatColor.BLUE + "~~~");
		for (Commands c : Commands.values()) {
			if (EasyMoney.hasPermission(player, c.getPermission()))
				player.sendMessage(ChatColor.GREEN + "/shop " + c.getAliases()[0] + ChatColor.AQUA + " ~ " + ChatColor.GOLD + c.getInfo());
		}
	}

}
