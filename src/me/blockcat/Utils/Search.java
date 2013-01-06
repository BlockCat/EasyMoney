package me.blockcat.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class Search {
	
	private static List<ItemInfo> items = new ArrayList<ItemInfo>();
	
	static {
		items.add(new ItemInfo("Stone", Material.STONE, (byte) 0, new String[] {"stone"}));
		items.add(new ItemInfo("Grass", Material.GRASS, (byte) 0, new String[] {"grass"}));
		items.add(new ItemInfo("Dirt", Material.DIRT, (byte) 0, new String[] {"dirt"}));
		items.add(new ItemInfo("Cobblestone", Material.COBBLESTONE, (byte) 0, new String[] {"cobble"}));
		items.add(new ItemInfo("Oak-wood", Material.WOOD, (byte) 0, new String[] {"oak"}));
		items.add(new ItemInfo("Spruce-wood", Material.WOOD, (byte) 1, new String[] {"spruce", "redwood"}));
		items.add(new ItemInfo("Birch-wood", Material.WOOD, (byte) 2,  new String[] {"birch"}));
		items.add(new ItemInfo("Jungle-wood", Material.WOOD, (byte) 3,  new String[] {"jungle"}));
		items.add(new ItemInfo("Sapling", Material.SAPLING, (byte) 0, new String[] {"sapl"}));
		items.add(new ItemInfo("Bedrock", Material.BEDROCK, (byte) 0, new String[] {"rock"}));
		items.add(new ItemInfo("Sand", Material.SAND, (byte) 0, new String[] {"sand"}));
	}
	
	public static ItemInfo getItemByName(String name) {
		for (ItemInfo i : items) {
			if (i.getName().equalsIgnoreCase(name)) {
				return i;
			} else {
				for (String s : i.getAliases()) {
					if (s.equalsIgnoreCase(name)) {
						return i;
					}
				}
			}
		}		
		return null;
	}
	
	public static ItemInfo getItemByMaterial(Material material) {
		if (material == null) {
			return null;
		}
		
		for (ItemInfo i : items) {
			if (i.getMaterial().getId() == material.getId()) {
				return i;
			}
		}
		return null;
	}
	
	public static ItemInfo getItemById (int id) {
		return getItemByMaterial(Material.getMaterial(id));
	}

}
