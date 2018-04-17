package me.skymc.taboolib.bungeesuite.bukkit.command.bungee;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandExecutor;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandParameter;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class CommandConnectOther extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "connectother";
	}

	@Override
	public String getUsages() {
		return "传送玩家到指定服务器";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { 
				new BukkitSubCommandParameter("玩家", true),
				new BukkitSubCommandParameter("名称", true)};
	}

	@Override
	public void run(CommandSender sender, Command arg1, String label, String[] args) {
		TabooLibBukkit.getInst().getBukkitChannelExecutor().connectOther(getOnlinePlayer(), args[0], args[1]);
	}
}
