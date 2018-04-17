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
		return "设置玩家数据";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { 
				new BukkitSubCommandParameter("玩家", true),
				new BukkitSubCommandParameter("键", true),
				new BukkitSubCommandParameter("值", true)};
	}

	@Override
	public void run(CommandSender sender, Command command, String label, String[] args) {
		TabooLibBukkit.getInst().getPlayerDataHandler().setPlayerData(args[0], args[1], ArrayUtils.arrayJoin(args, 2));
	}
}
