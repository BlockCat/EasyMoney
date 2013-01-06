package me.blockcat.commands;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import me.blockcat.Account;
import me.blockcat.EasyMoney;
import me.blockcat.Utils.MoneyCommand;
import me.blockcat.Utils.MoneyFormat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandTop extends MoneyCommand {

	@Override
	public void execute(Player player, String[] args) {
		player.sendMessage(ChatColor.WHITE + "Top 10 wealthiest players.");
		player.sendMessage(ChatColor.GREEN + "~~~~~~~~~~~~~");

		TreeMap<String, Double> order = getTopTen();
		
		int place = 1;
		for (Entry<String, Double> entry : order.entrySet()) {
			if (place <= 10) {
				String playerName = entry.getKey();
				String money = MoneyFormat.formatString(entry.getValue());
				player.sendMessage(ChatColor.WHITE + "" +  place +"~ " + ChatColor.GREEN + playerName + ": " + ChatColor.AQUA + money);
				place++;
			}
		}
		
		player.sendMessage(ChatColor.GREEN + "~~~~~~~~~~~~~");
	}

	private TreeMap<String, Double> getTopTen() {

		HashMap<String,Double> map = new HashMap<String,Double>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Double> list = new TreeMap<String,Double>(bvc);
		
		File dir = new File(EasyMoney.getInstance().getDataFolder(), "/data/players/");
		dir.mkdirs();
		
		for (String name : dir.list()) {
			name = name.replace(".data", "");
			Account account = new Account(name);
			double money = account.getMoney();
			map.put(name, money);			
		}
        list.putAll(map);
		return list;
	}

	class ValueComparator implements Comparator<String> {

	    Map<String, Double> base;
	    public ValueComparator(Map<String, Double> base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    @Override
	    public int compare(String a, String b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }
	}

}
