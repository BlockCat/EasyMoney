package me.blockcat.Shops;

public class ShopItem {

	private int amount;
	private int id;
	private byte data;

	public ShopItem(int amount, int id, byte data) {
		this.amount = amount;
		this.id = id;
		this.data = data;
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

}
