package me.blockcat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.entity.Player;

public class Account {
	
	private String player;
	private Double balance = null;
	
	public Account(Player player) {
		this.player = player.getName();
		load();
	}
	
	public Account(String player) {
		this.player = player; 
		load();
	}
	
	public void add(double money) {
		balance += money;
		save();
	}
	
	public void substract(double money) {
		balance -= money;
		save();
	}
	
	public void setMoney(double money) {
		balance = money;
		save();
	}
	
	public double getMoney() {
		return balance;
	}
	
	public boolean hasAccount() {
		File save = new File(EasyMoney.getInstance().getDataFolder(), "/data/players/" + player + ".data");
		return save.exists();
	}

	private void load() {
		File save = new File(EasyMoney.getInstance().getDataFolder(), "/data/players/" + player + ".data");
		 save.getParentFile().mkdirs();
		 
			try {
				GZIPInputStream zin = new GZIPInputStream(new FileInputStream(save));
				DataInputStream in = new DataInputStream(zin);
				
				balance = in.readDouble();
		
				in.close();
			} catch (Exception e) {
			}
			
			if (balance == null) {
				balance = Configuration.beginValue;
			}
	}
	
	public void save() {
		File save = new File(EasyMoney.getInstance().getDataFolder(), "/data/players/" + player + ".data");
		 save.getParentFile().mkdirs();
		
			try {
				GZIPOutputStream zout = new GZIPOutputStream(new FileOutputStream(save));
				DataOutputStream out = new DataOutputStream(zout);
				
				out.writeDouble(balance);
		
				out.close();
			} catch (Exception e) {

			}
	}

	

}
