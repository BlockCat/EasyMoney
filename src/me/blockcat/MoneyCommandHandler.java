package me.blockcat;

import java.util.HashMap;

import me.blockcat.Utils.MoneyCommand;
import me.blockcat.commands.CommandBalance;
import me.blockcat.commands.CommandGive;
import me.blockcat.commands.CommandHelp;
import me.blockcat.commands.CommandPay;
import me.blockcat.commands.CommandTake;
import me.blockcat.commands.CommandTop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommandHandler implements CommandExecutor {

	private EasyMoney plugin;

	public MoneyCommandHandler(EasyMoney plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command com, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
		}

		Player player = (Player) sender;

		if (args.length == 0 || args == null) {
			try {
				Commands command = Commands.BALANCE;
				if (EasyMoney.hasPermission(player, command._permission)) {
					command.newInstance().execute(player, args);
				} else {
					player.sendMessage(ChatColor.RED + "No permissions.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		Commands command = Commands.getCommand(args[0]);

		if (command == null) {
			String name = args[0];
			Player target = null;
			target = Bukkit.getPlayer(args[0]);
			
			if (target != null) {
				name = target.getName();
			}
			Account account = new Account(name);
			
			if (account.hasAccount()) {
				
				if (EasyMoney.hasPermission(player, "easymoney.balance.others")) {
					CommandBalance b = new CommandBalance(name);
					b.execute(player, args);
				} else {
					player.sendMessage(ChatColor.RED + "No permissions.");
				}
				return true;
			} else {
				player.sendMessage(ChatColor.RED + "Player not found.");
			}
		}

		if (command == Commands.HELP) {
			try {
				Commands.HELP.newInstance().execute(player, args);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} else {
			try {
				if (EasyMoney.hasPermission(player, command._permission)) {
					command.newInstance().execute(player, args);
				} else {
					player.sendMessage(ChatColor.RED + "No permissions.");
				}
			} catch (Exception e) {

			}
		}


		return true;
	}

	public enum Commands {
		BALANCE("", "Check your balance.", "easymoney.balance", CommandBalance.class),
		HELP("help,?", "Show help", "easymoney.help", CommandHelp.class),
		PAY("pay,p", "Pay money to a player.", "easymoney.pay", CommandPay.class),
		TOP("top,t","Check the 10 richest players.", "easymoney.top", CommandTop.class),
		TAKE("take", "Take money from a player.", "easymoney.take", CommandTake.class),
		GIVE("give", "Give money to a player.", "easymoney.give", CommandGive.class);

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
