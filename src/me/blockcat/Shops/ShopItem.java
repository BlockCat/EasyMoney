package me.blockcat.Shops;

import me.blockcat.Utils.ItemInfo;

public class ShopItem {

	private int amount;
	private int id;
	private byte data;
	private double sell = 0.0;
	private double buy = 0;

	public ShopItem(int amount, int id, byte data) {
		this.amount = amount;
		this.id = id;
		this.data = data;
	}

	public ShopItem(ItemInfo itemInfo, int amount) {
		this.amount = amount;
		this.id = itemInfo.getMaterial().getId();
		this.data = itemInfo.getData();
	}

	public void add(int amount) {
		this.amount += amount;		
	}
	public void subtract(int amount) {
		this.amount -= amount;
	}

	public int getAmount() {
		return amount;
	}

	public int getId() {
		return id;
	}

	public byte getData() {
		return data;
	}

	public double getSellPrice() {
		return sell;
	}
	
	public void setSellPrice(double sell) {
		this.sell = sell;
	}
	
	public double getBuyPrice() {
		return buy;
	}
	
	public void setBuyPrice(double buy) {
		this.buy = buy;
	}

}
