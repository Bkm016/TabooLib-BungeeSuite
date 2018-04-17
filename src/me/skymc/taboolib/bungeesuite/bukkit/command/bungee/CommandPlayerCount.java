package me.skymc.taboolib.bungeesuite.bukkit.command.bungee;

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
public class CommandPlayerCount extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "playercount";
	}

	@Override
	public String getUsages() {
		return "�鿴�������������";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { new BukkitSubCommandParameter("����", true) };
	}

	@Override
	public void run(CommandSender sender, Command arg1, String label, String[] args) {
		TabooLibBukkit.getInst().getBukkitChannelExecutor().playerCount(getOnlinePlayer(), args[0], new TChannelResult() {
			
			@Override
			public void run(String[] result) {
				if (result[0].equals("-")) {
					TLogger.send(sender, "&4������ &c" + args[0] + " &4������");
				} 
				else if (args[0].equalsIgnoreCase("all")) {
					TLogger.send(sender, "&7��ǰȫ���ܹ� &f" + result[0] + " &7���");
				} 
				else {
					TLogger.send(sender, "&7������ &f" + args[0] + " &7��ǰ�ܹ� &f" + result[0] + " &7���");
				}
			}
		});
	}
}
