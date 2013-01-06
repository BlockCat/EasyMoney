package me.blockcat.Shops;

import me.blockcat.EasyMoney;

public class EasyShops {
	
	private EasyMoney plugin;

	public EasyShops(EasyMoney plugin) {
		this.plugin = plugin; 
		plugin.getServer().getPluginManager().registerEvents(new ShopListener(), plugin);
		plugin.getCommand("shop").setExecutor(new ShopCommandHandler());
	}

}
