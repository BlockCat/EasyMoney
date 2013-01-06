package me.blockcat.Shops.commands;

import me.blockcat.Shops.PlayerData;
import me.blockcat.Shops.PlayerData.Data;
import me.blockcat.Utils.MoneyCommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandSelect extends MoneyCommand {

	@Override
	public void execute(Player player, String[] args) {
		Data data = PlayerData.getData(player.getName());
		if (!data.isSelecting()) {
			data.setSelecting(true);
			player.sendMessage(ChatColor.BLUE + "Select 2 corners of your shop with your bare hands.");
		} else {
			data.setSelecting(false);
			player.sendMessage(ChatColor.BLUE + "Not selecting anymore.");
		}
	}

}
