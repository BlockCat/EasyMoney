package me.blockcat.Shops;

import java.util.HashMap;

import me.blockcat.Shops.commands.CommandAdd;
import me.blockcat.Shops.commands.CommandBrowse;
import me.blockcat.Shops.commands.CommandBuy;
import me.blockcat.Shops.commands.CommandCreate;
import me.blockcat.Shops.commands.CommandDeposit;
import me.blockcat.Shops.commands.CommandHelp;
import me.blockcat.Shops.commands.CommandSelect;
import me.blockcat.Shops.commands.CommandSell;
import me.blockcat.Shops.commands.CommandTake;
import me.blockcat.Shops.commands.CommandWithdraw;
import me.blockcat.Utils.MoneyCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ShopCommandHandler implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
		}

		Player player = (Player) sender;
		
		if (args.length == 0) {
			showHelp(player, args);
			return true;
		}
		
		Commands command = Commands.getCommand(args[0]);
		
		if (command == null) {
			showHelp(player, args);
			return true;
		}
		
		command.newInstance().execute(player, args);
		
		return true;
	}
	
	private void showHelp(Player player, String[] args) {
		Commands.HELP.newInstance().execute(player, args);
	}
	
	public enum Commands {
		HELP("help,?", "Show help.", "easymoney.shop.help", CommandHelp.class),
		BROWSE("browse,b", "Check items of the shop you are in.", "easymoney.shop.browse", CommandBrowse.class),
		BUY("buy", "Buy an item.", "easymoney.shop.buy", CommandBuy.class),
		SELL("sell", "Sell an item.", "easymoney.shop.sell", CommandSell.class),
		DEPOSIT("deposit,d","Check the 10 richest players.", "easymoney.shop.deposit", CommandDeposit.class),
		WITHDRAW("withdraw,w", "Withdraw money from your shop.", "easymoney.shop.withdraw", CommandWithdraw.class),
		TAKE("take", "Take an item from the shop.", "easymoney.shop.take", CommandTake.class),
		ADD("add", "Add an item to the shop.", "easymoney.shop.add", CommandAdd.class),
		SELECT("select", "Select 2 points of your shop.", "easymoney.shop.create", CommandSelect.class),
		CREATE("create,c", "Create a shop between selected points.", "easymoney.shop.create", CommandCreate.class);

		private static HashMap<String, Commands> commandsByName = new HashMap<String, Commands>();

		private String[] _alliases;
		private String _info;
		private String _permission;
		private Class<? extends MoneyCommand> _handler;


		Commands(String alliases, String info, String permission, Class<? extends MoneyCommand> handler) {
			_alliases = alliases.split(",");
			_info = info;
			_permission = permission;
			_handler = handler;
		}

		public String getPermission() {
			return _permission;
		}
		public String getInfo() {
			return _info;
		}
		public String[] getAliases() {
			return _alliases;
		}
		
		public MoneyCommand newInstance() {
			try {
				return _handler.newInstance();
			} catch (Exception e) {
				return null;
			}
		}

		public static Commands getCommand(String command) {
			if (commandsByName.containsKey(command)) {
				return commandsByName.get(command);
			} else {
				return null;
			}
		}
		
		

		static {
			for (Commands c : Commands.values()) {
				for (String a : c._alliases) {
					commandsByName.put(a, c);
				}
			}
		}

	}

	
}