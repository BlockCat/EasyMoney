package me.blockcat.Shops.commands;

import me.blockcat.Shops.PlayerData;
import me.blockcat.Shops.PlayerData.Data;
import me.blockcat.Shops.Shop;
import me.blockcat.Shops.ShopHandler;
import me.blockcat.Utils.MoneyCommand;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandCreate extends MoneyCommand {

	@Override
	public void execute(Player player, String[] args) {
		Data data = PlayerData.getData(player.getName());
		
		if (data.getV1() == null) {
			player.sendMessage(ChatColor.RED + "Please select the first point!");
			return;
		}
		if (data.getV2() == null) {
			player.sendMessage(ChatColor.RED + "Please select the first point!");
			return;
		}
		
		if (args.length == 1) {
			player.sendMessage(ChatColor.BLUE + "/shop create <name>");
			return;
		}
		
		Shop shop = new Shop(args[1], player.getName(), data.getV1(), data.getV2());
		ShopHandler.addShop(shop);
		player.sendMessage(ChatColor.BLUE + "Shop: " + args[1] + " created!");
		shop.save();
		
	}

}
