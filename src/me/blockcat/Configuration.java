package me.blockcat;

import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {

	public static boolean useShops = true;
	public static double beginValue = 0;
	public static String currencyName = "";

	private EasyMoney plugin;


	public Configuration(EasyMoney plugin) {
		this.plugin = plugin;
	}

	public void load() {

		currencyName = getString("currency-name", "rupees");
		beginValue = getDouble("begin-value", 0.0D);
		useShops = getBoolean("use-shops", true);

		plugin.saveConfig();
	}

	public double getDouble(String key, double value) {
		FileConfiguration config = plugin.getConfig();

		if (config.contains(key)) {
			return config.getDouble(key);
		} else {
			config.set(key, value);
			return value;
		}
	}

	public String getString(String key, String value) {
		FileConfiguration config = plugin.getConfig();

		if (config.contains(key)) {
			return config.getString(key);
		} else {
			config.set(key, value);
			return value;
		}
	}
	
	public boolean getBoolean(String key, boolean value) {
		FileConfiguration config = plugin.getConfig();

		if (config.contains(key)) {
			return config.getBoolean(key);
		} else {
			config.set(key, value);
			return value;
		}
	}




}
