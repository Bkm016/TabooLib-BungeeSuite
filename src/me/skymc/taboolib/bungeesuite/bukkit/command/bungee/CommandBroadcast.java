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
public class CommandBroadcast extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "broadcast";
	}

	@Override
	public String getUsages() {
		return "发送信息到指定服务器";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { 
				new BukkitSubCommandParameter("名称", true),
				new BukkitSubCommandParameter("文本", true)};
	}

	@Override
	public void run(CommandSender sender, Command command, String label, String[] args) {
		String message = ArrayUtils.arrayJoin(args, 1).replace("&", "§");
		TabooLibBukkit.getInst().getBukkitChannelExecutor().broadcast(getOnlinePlayer(), args[0], message);
	}
}
