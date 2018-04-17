package me.skymc.taboolib.bungeesuite.bukkit.command.bungee;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandExecutor;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandParameter;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandType;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class CommandConnect extends BukkitSubCommandExecutor {
	
	@Override
	public BukkitSubCommandType getType() {
		return BukkitSubCommandType.PLAYER;
	}

	@Override
	public String getName() {
		return "connect";
	}

	@Override
	public String getUsages() {
		return "传送到指定服务器";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { new BukkitSubCommandParameter("名称", true) };
	}

	@Override
	public void run(CommandSender sender, Command arg1, String label, String[] args) {
		TabooLibBukkit.getInst().getBukkitChannelExecutor().connect((Player) sender, args[0]);
	}
}
