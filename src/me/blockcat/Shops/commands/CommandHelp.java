package me.blockcat.Shops.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.blockcat.EasyMoney;
import me.blockcat.Shops.ShopCommandHandler.Commands;
import me.blockcat.Utils.MoneyCommand;

public class CommandHelp extends MoneyCommand {

	@Override
	public void execute(Player player, String[] args) {
		player.sendMessage(ChatColor.BLUE + "~~~" + ChatColor.GREEN + "EasyShops" + ChatColor.BLUE + "~~~");
		for (Commands c :Commands.values()) {
			if (EasyMoney.hasPermission(player, c.getPermission())) {
				player.sendMessage(ChatColor.GREEN + "/money " + c.getAliases()[0] + ChatColor.AQUA + " ~ " + ChatColor.GOLD + c.getInfo());
			}
		}
	}

}
