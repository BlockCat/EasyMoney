package me.blockcat.Shops;

import me.blockcat.Shops.PlayerData.Data;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShopListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		System.out.println("interact!");
		if (event.getPlayer().getItemInHand().getType() != Material.AIR) return;
		if (event.getClickedBlock() == null) return;
		
		Player player = event.getPlayer();
		Data data = PlayerData.getData(player.getName());
		if (data.isSelecting()) {
			event.setCancelled(true);
			if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
				data.setV1(event.getClickedBlock().getLocation().toVector());
				player.sendMessage(ChatColor.BLUE + "Point 1 selected at: " + ChatColor.LIGHT_PURPLE + data.getV1().toString());
			} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				data.setV2(event.getClickedBlock().getLocation().toVector());
				player.sendMessage(ChatColor.BLUE + "Point 2 selected at: " + ChatColor.LIGHT_PURPLE + data.getV2().toString());
			}
			return;
		}	
	}
}
