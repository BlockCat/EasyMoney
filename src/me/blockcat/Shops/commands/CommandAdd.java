package me.blockcat.Shops.commands;

import me.blockcat.Shops.Shop;
import me.blockcat.Shops.ShopHandler;
import me.blockcat.Utils.ItemInfo;
import me.blockcat.Utils.MoneyCommand;
import me.blockcat.Utils.Search;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandAdd extends MoneyCommand{

	@Override
	public void execute(Player player, String[] args) {
		Shop shop = ShopHandler.getShop(player.getLocation());

		if (args.length == 1) {
			player.sendMessage(ChatColor.BLUE + "/shop add <amount> (Item)");
			return;
		}

		if (shop == null) {
			player.sendMessage(ChatColor.RED + "You are not in a shop.");
			return;
		}

		if (!player.getName().equalsIgnoreCase(shop.getOwner())) {
			player.sendMessage(ChatColor.RED + "You can only add items to your own shop.");
			return;
		}

		int amount = 1;

		try {
			amount = Integer.parseInt(args[1]);
		} catch (Exception e) {
			amount = 1;
		}
		ItemInfo itemInfo = null;
		try {
			itemInfo = Search.getItemByName(args[2]);
		} catch (Exception e) {
			itemInfo = Search.getItemByMaterial(player.getItemInHand().getType());
		}

		if (itemInfo == null) {
			player.sendMessage(ChatColor.RED + "Item not found.");
			return;
		}

		if (player.getInventory().contains(itemInfo.getMaterial(), amount)) {
			amount = takeFromPlayer(player, itemInfo, amount);
			shop.addItem(itemInfo, amount);
			player.sendMessage(ChatColor.BLUE + "Added: " + ChatColor.LIGHT_PURPLE + amount + ChatColor.BLUE + " : " + ChatColor.LIGHT_PURPLE + itemInfo.getName());
		} else {
			player.sendMessage(ChatColor.BLUE + "Not enough items.");
		}
	}
	
	private int takeFromPlayer(Player player, ItemInfo itemInfo, int amount) {
		Inventory inv = player.getInventory();
		inv.removeItem(new ItemStack(itemInfo.getMaterial(), amount));
		return amount;
	}
	

}
