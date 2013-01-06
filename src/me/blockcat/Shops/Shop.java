package me.blockcat.Shops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import me.blockcat.EasyMoney;
import me.blockcat.Utils.ItemInfo;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Shop {

	private HashMap<String, ShopItem> shopStock = new HashMap<String, ShopItem>();
	private double money = 0.0D;
	private String playerName;
	private String name;
	//max vector
	private Vector v1 = null;
	//min vector
	private Vector v2 = null;

	public Shop (String name, String playerName, Vector v1, Vector v2) {
		this.name = name;
		this.playerName = playerName;
		this.v1 = Vector.getMaximum(v1, v2);
		this.v2 = Vector.getMinimum(v1, v2);
	}
	
	public Shop (File file) {
		load(file);
	}


	public void addMoney(double money) {
		this.money += money; 
	}

	public void subtract(double money) {
		this.money -= money;
	}

	public double getMoney() {
		return money;
	}
	
	public String getOwner() {
		return playerName;
	}
	
	public String getName() {
		return name;
	}

	public void addItem(ItemInfo itemInfo, int amount) {
		if (shopStock.containsKey(itemInfo.getName())) {
			ShopItem i = shopStock.get(itemInfo.getName());
			i.add(amount);
		} else {
			ShopItem i = new ShopItem(itemInfo, amount);
			shopStock.put(itemInfo.getName(), i);
		}
	}
	
	public void takeItem(ItemInfo itemInfo, int amount) {
		if (shopStock.containsKey(itemInfo.getName())) {
			ShopItem i = shopStock.get(itemInfo.getName());
			i.subtract(amount);
			
			if (i.getAmount() == 0) {
				shopStock.remove(itemInfo.getName());
			}
		}
	}
	
	public HashMap<String, ShopItem> getStock() {
		return shopStock;
	}
	
	public boolean inLocation (Location loc) {
		Vector location = loc.toVector();
		return location.isInAABB(v2, v1);
	}
	
	public int getItemAmount(String name) {
		if (shopStock.containsKey(name)) {
			return shopStock.get(name).getAmount();
		} else {
			return 0;
		}
	}

	public void save() {
		File file = new File(EasyMoney.getInstance().getDataFolder(), "/shops/" + v1.toString() + ".shop");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {				
			}
		}
		try{
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(playerName);
			out.newLine();
			out.write(name);
			out.newLine();
			out.write(money + "");
			out.newLine();
			out.write(v1.toString());
			out.newLine();
			out.write(v2.toString());
			out.newLine();
			out.write("#NAME:AMOUNT:ID:DATA(byte):SELL:BUY");
			out.newLine();
			
			for (Entry<String, ShopItem> map : shopStock.entrySet()) {
				ShopItem item = map.getValue();
				String name = map.getKey();
				int amount = item.getAmount();
				int id = item.getId();
				byte data = item.getData();
				double sellPrice = item.getSellPrice();
				double buyPrice = item.getBuyPrice();
				String saveLine = name + ":" + amount + ":" + id + ":" + data + ":" + sellPrice +":" + buyPrice;
				out.write(saveLine);
				out.newLine();
			}

			out.close();
		}catch (Exception e){//Catch exception if any
			e.printStackTrace();
		}
	}

	private void load(File file) {
		try {
			Scanner scanner = new Scanner(file);
			
			if (scanner.hasNextLine()) {
				playerName = scanner.nextLine();
			}
			if (scanner.hasNextLine()) {
				name = scanner.nextLine();
			}
			if (scanner.hasNextLine()) {
				try {
				money = Double.parseDouble(scanner.nextLine());
				} catch(Exception e) {
					money = 0.0D;
				}
			}
			if (scanner.hasNextLine()) {
				String[] l = scanner.nextLine().split(",");
				double x,y,z;
				try {
				x = Double.parseDouble(l[0]);
				y = Double.parseDouble(l[1]);
				z = Double.parseDouble(l[2]);
				v1 = new Vector(x, y, z);				
				} catch (Exception e) {
				}
			}
			if (scanner.hasNextLine()) {
				String[] l = scanner.nextLine().split(",");
				double x,y,z;
				try {
				x = Double.parseDouble(l[0]);
				y = Double.parseDouble(l[1]);
				z = Double.parseDouble(l[2]);
				v2 = new Vector(x, y, z);				
				} catch (Exception e) {
				}
			}
			
			while(scanner.hasNextLine()) {
				String saveLine = scanner.nextLine();
				
				if (saveLine.startsWith("#")) continue;
				
				String[] args = saveLine.split(":");
				
				String name = args[0];
				int amount = Integer.parseInt(args[1]);
				int id = Integer.parseInt(args[2]);
				byte data = Byte.parseByte(args[3]);
				double sellPrice = Double.parseDouble(args[4]);
				double buyPrice = Double.parseDouble(args[5]);
				
				ShopItem item = new ShopItem(amount, id, data);
				item.setSellPrice(sellPrice);
				item.setBuyPrice(buyPrice);
				shopStock.put(name, item);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
		}
	}

}
