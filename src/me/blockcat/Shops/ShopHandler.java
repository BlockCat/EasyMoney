package me.blockcat.Shops;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.blockcat.EasyMoney;

import org.bukkit.Location;

public class ShopHandler {
	
	private static List<Shop> shops = new ArrayList<Shop>();
	
	public static Shop getShop(Location loc) {
		for (Shop shop : shops) {
			if (shop.inLocation(loc)) {
				return shop;
			}
		}
		return null;
	}
	
	public static void addShop(Shop shop) {
		shops.add(shop);
	}
	
	public static void load() {
		File dir = new File(EasyMoney.getInstance().getDataFolder(), "/shops/");
		
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith(".shop")) {
				shops.add(new Shop(file));
			}
		}
	}

}
