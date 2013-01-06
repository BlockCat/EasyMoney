package me.blockcat.Shops;

import java.util.HashMap;

import org.bukkit.util.Vector;

public class PlayerData {
	
	private static HashMap<String, Data> players = new HashMap<String, Data>();
	
	public static Data getData (String player) {
		if (players.containsKey(player)) {
			return players.get(player);
		} else {
			Data data = new Data();
			players.put(player, data);
			return data;
		}
	}
	
	public static class Data {
		
		private boolean selecting = false;
		private Vector v1 = null;
		private Vector v2 = null;
		
		public boolean isSelecting() {
			return selecting;
		}		
		public void setSelecting(boolean s) {
			this.selecting = s;
		}
		public void setV1 (Vector v) {
			v1 = v;
		}
		public Vector getV1() {
			return v1;
		}
		public void setV2 (Vector v) {
			v2 = v;
		}
		public Vector getV2() {
			return v2;
		}
		
		
	}

}
