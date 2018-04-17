package me.skymc.taboolib.bungeesuite.bukkit.command.playerdata;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandExecutor;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitSubCommandParameter;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class CommandPlayerDataRemove extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "removedata";
	}

	@Override
	public String getUsages() {
		return "ɾ���������";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { 
				new BukkitSubCommandParameter("���", true),
				new BukkitSubCommandParameter("��", true)};
	}

	@Override
	public void run(CommandSender sender, Command command, String label, String[] args) {
		TabooLibBukkit.getInst().getPlayerDataHandler().removePlayerData(args[0], args[1]);
	}
}
