package me.blockcat.Shops.commands;

import java.util.Map.Entry;

import me.blockcat.Shops.Shop;
import me.blockcat.Shops.ShopHandler;
import me.blockcat.Shops.ShopItem;
import me.blockcat.Utils.MoneyCommand;
import me.blockcat.Utils.MoneyFormat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandBrowse extends MoneyCommand {

	@Override
	public void execute(Player player, String[] args) {
		Shop shop = ShopHandler.getShop(player.getLocation());

		if (shop == null) {
			player.sendMessage(ChatColor.RED + "You are not in a shop.");
			return;
		}
		player.sendMessage(ChatColor.BLUE + "Browsing shop: " + ChatColor.LIGHT_PURPLE + shop.getName());
		player.sendMessage(ChatColor.BLUE + "Name : amount : sell price : buy price");
		for (Entry<String, ShopItem> map : shop.getStock().entrySet()) {
			ShopItem item = map.getValue();
			player.sendMessage(ChatColor.GOLD + map.getKey() + " : " +item.getAmount() + " : " + MoneyFormat.formatString(item.getSellPrice()) + " : " + MoneyFormat.formatString(item.getBuyPrice()));
		}
		
	}

}
