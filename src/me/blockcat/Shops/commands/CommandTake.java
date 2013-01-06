package me.blockcat.Shops.commands;

import me.blockcat.Shops.Shop;
import me.blockcat.Shops.ShopHandler;
import me.blockcat.Utils.ItemInfo;
import me.blockcat.Utils.MoneyCommand;
import me.blockcat.Utils.Search;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CommandTake extends MoneyCommand {

	@Override
	public void execute(Player player, String[] args) {
		Shop shop = ShopHandler.getShop(player.getLocation());

		if (args.length == 1) {
			player.sendMessage(ChatColor.BLUE + "/shop take <amount> (Item)");
			return;
		}

		if (shop == null) {
			player.sendMessage(ChatColor.RED + "You are not in a shop.");
			return;
		}

		if (!player.getName().equalsIgnoreCase(shop.getOwner())) {
			player.sendMessage(ChatColor.RED + "You can only take items from your own shop.");
			return;
		}

		int amount = 1;

		try {
			amount = Integer.parseInt(args[1]);
		} catch (Exception e) {
			amount = 1;
		}

		if (amount == 0) {
			player.sendMessage(ChatColor.RED + "ye... don't be stupid.");
			return;
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

		int am = shop.getItemAmount(itemInfo.getName());
		
		if (am == 0) {
			player.sendMessage(ChatColor.BLUE + "The shop doesn't have this item.");
			return;
		}
		if (am > amount) {
			amount = addToPlayer(player, itemInfo, amount);
			player.sendMessage(ChatColor.BLUE + "Took: " + ChatColor.LIGHT_PURPLE + amount + ChatColor.BLUE + " : " + ChatColor.LIGHT_PURPLE + itemInfo.getName());
			shop.takeItem(itemInfo, amount);
			addToPlayer(player, itemInfo, amount);
		} else {
			amount = am;
			amount = addToPlayer(player, itemInfo, amount);
			player.sendMessage(ChatColor.BLUE + "Took: " + ChatColor.LIGHT_PURPLE + amount + ChatColor.BLUE + " : " + ChatColor.LIGHT_PURPLE + itemInfo.getName());
			shop.takeItem(itemInfo, amount);
		}
		
	}
	
	private int addToPlayer(Player player, ItemInfo itemInfo, int amount) {
		Inventory inv = player.getInventory();
		int availableSpace = countAvailableSpaceForItemInInventory(inv, itemInfo.getMaterial());
		if (amount > availableSpace) amount = availableSpace;
		inv.addItem(new ItemStack(itemInfo.getMaterial(), amount));
		return amount;
	}
	
	private int countAvailableSpaceForItemInInventory(Inventory inv, Material material) {
		int count = 0;
		for (ItemStack thisSlot : inv.getContents()) {
			if (thisSlot == null || thisSlot.getType() == Material.AIR) {
				count += 64;
				continue;
			}
			if (thisSlot.getType() == material) {
				count += 64 - thisSlot.getAmount();
			}
		}
		return count;
	}

}
