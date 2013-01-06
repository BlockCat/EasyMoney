package me.blockcat.Utils;

import org.bukkit.Material;

public class ItemInfo {
	
	private String name;
	private Material material;
	private byte data;
	private String[] aliases;

	public ItemInfo(String name, Material material, byte data, String[] aliases) {
		this.name = name; 
		this.material = material;
		this.data = data;
		this.aliases = aliases;
	}

	public String getName() {
		return name;
	}

	public String[] getAliases() {
		return aliases;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public byte getData() {
		return data;
	}

}
