package me.skymc.taboolib.bungeesuite.bukkit.command.bungee;

import java.util.Arrays;

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
public class CommandPlayerList extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "playerlist";
	}

	@Override
	public String getUsages() {
		return "查看服务器玩家";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { new BukkitSubCommandParameter("名称", true) };
	}

	@Override
	public void run(CommandSender sender, Command arg1, String label, String[] args) {
		TabooLibBukkit.getInst().getBukkitChannelExecutor().playerList(getOnlinePlayer(), args[0], result -> {
			if (result[0].equals("-")) {
				TLogger.send(sender, "&4服务器 &c" + args[0] + " &4不存在");
			}
			else if (args[0].equalsIgnoreCase("all")) {
				TLogger.send(sender, "&7当前全服玩家: &f" + Arrays.asList(result));
			}
			else {
				TLogger.send(sender, "&7服务器 &f" + args[0] + " &7当前玩家: &f" + Arrays.asList(result));
			}
		});
	}
}
