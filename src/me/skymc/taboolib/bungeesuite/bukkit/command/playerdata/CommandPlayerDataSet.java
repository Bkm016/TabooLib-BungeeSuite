package me.skymc.taboolib.bungeesuite.bukkit.command.playerdata;

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
public class CommandPlayerDataSet extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "setdata";
	}

	@Override
	public String getUsages() {
		return "�����������";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { 
				new BukkitSubCommandParameter("���", true),
				new BukkitSubCommandParameter("��", true),
				new BukkitSubCommandParameter("ֵ", true)};
	}

	@Override
	public void run(CommandSender sender, Command command, String label, String[] args) {
		TabooLibBukkit.getInst().getPlayerDataHandler().setPlayerData(args[0], args[1], ArrayUtils.arrayJoin(args, 2));
	}
}
