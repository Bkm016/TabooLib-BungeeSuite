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
public class CommandServerList extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "serverlist";
	}

	@Override
	public String getUsages() {
		return "查看服务器列表";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[0];
	}

	@Override
	public void run(CommandSender sender, Command arg1, String label, String[] args) {
		TabooLibBukkit.getInst().getBukkitChannelExecutor().serverList(getOnlinePlayer(), result -> TLogger.send(sender, "&7当前全部服务器: &f" + Arrays.asList(result)));
	}
}
