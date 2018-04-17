package me.skymc.taboolib.bungeesuite.bukkit.command.bungee;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandExecutor;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandParameter;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class CommandKickPlayer extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "kickplayer";
	}

	@Override
	public String getUsages() {
		return "踢出玩家";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { 
				new BukkitSubCommandParameter("玩家", true),
				new BukkitSubCommandParameter("理由", true)};
	}

	@Override
	public void run(CommandSender sender, Command command, String label, String[] args) {
		String reason = ArrayUtils.arrayJoin(args, 1).replace("&", "§");
		TabooLibBukkit.getInst().getBukkitChannelExecutor().kickPlayer(getOnlinePlayer(), args[0], reason);
	}
}
