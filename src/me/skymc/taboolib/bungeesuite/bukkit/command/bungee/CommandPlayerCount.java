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
public class CommandPlayerCount extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "playercount";
	}

	@Override
	public String getUsages() {
		return "查看服务器玩家数量";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { new BukkitSubCommandParameter("名称", true) };
	}

	@Override
	public void run(CommandSender sender, Command arg1, String label, String[] args) {
		TabooLibBukkit.getInst().getBukkitChannelExecutor().playerCount(getOnlinePlayer(), args[0], new TChannelResult() {
			
			@Override
			public void run(String[] result) {
				if (result[0].equals("-")) {
					TLogger.send(sender, "&4服务器 &c" + args[0] + " &4不存在");
				} 
				else if (args[0].equalsIgnoreCase("all")) {
					TLogger.send(sender, "&7当前全服总共 &f" + result[0] + " &7玩家");
				} 
				else {
					TLogger.send(sender, "&7服务器 &f" + args[0] + " &7当前总共 &f" + result[0] + " &7玩家");
				}
			}
		});
	}
}
