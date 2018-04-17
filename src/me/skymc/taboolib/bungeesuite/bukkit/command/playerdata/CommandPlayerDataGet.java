package me.skymc.taboolib.bungeesuite.bukkit.command.playerdata;

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
public class CommandPlayerDataGet extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "getdata";
	}

	@Override
	public String getUsages() {
		return "�鿴�������";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { 
				new BukkitSubCommandParameter("���", true),
				new BukkitSubCommandParameter("��", true)};
	}

	@Override
	public void run(CommandSender sender, Command command, String label, String[] args) {
		TabooLibBukkit.getInst().getPlayerDataHandler().getPlayerData(args[0], args[1], new TChannelResult() {
			
			@Override
			public void run(String[] result) {
				TLogger.send(sender, "&7��� &f" + args[0] + " &7������ &f" + args[1] + " &7ֵΪ: &f" + (result[0].equals("null") ? "��" : result[0]));
			}
		});
	}
}
