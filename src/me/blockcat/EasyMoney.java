package me.blockcat;

import me.blockcat.Shops.EasyShops;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class EasyMoney extends JavaPlugin{

	public static Permission permission = null;
	private static EasyMoney instance = null;
	private EasyShops shops = null;
	private Configuration config;
	
	public void onEnable() {
		instance = this;
		config = new Configuration(this);
		config.load();
		MoneyData.load();
		this.setupPermissions();
		
		if (Configuration.useShops) {
			shops = new EasyShops(this);
		}
		
		this.getCommand("money").setExecutor(new MoneyCommandHandler(this));
		
	}
	
	public static EasyMoney getInstance() {
		return instance;
	}
	

    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

	
	public static boolean hasPermission(Player player, String node) {
		if (permission == null) {
			return player.hasPermission(node);
		} else {
			return permission.has(player, node);					
		}
	}
	
}
