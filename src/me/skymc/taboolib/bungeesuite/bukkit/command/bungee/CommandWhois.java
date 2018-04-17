package me.skymc.taboolib.bungeesuite.bukkit.command.bungee;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandExecutor;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandParameter;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class CommandWhois extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "whois";
	}

	@Override
	public String getUsages() {
		return "查看玩家信息";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { new BukkitSubCommandParameter("玩家", true) };
	}

	@Override
	public void run(CommandSender sender, Command arg1, String label, String[] args) {
		TabooLibBukkit.getInst().getBukkitChannelExecutor().whois(getOnlinePlayer(), args[0], new TChannelResult() {
			
			@Override
			public void run(String[] result) {
				if (result[0].equals("-")) {
					TLogger.send(sender, "&4玩家 &c" + args[0] + " &4不在线");
				} else {
					TLogger.send(sender, "&7玩家 &f" + args[0] + " &7的信息:");
					TLogger.send(sender, "&f&m                                 ");
					TLogger.send(sender, "&7Address: &f" + result[0].substring(1));
					TLogger.send(sender, "&7Server: &f" + result[2]);
					TLogger.send(sender, "&7Ping: &f" + result[1]);
					TLogger.send(sender, "&f&m                                 ");
				}
			}
		});
	}
}
