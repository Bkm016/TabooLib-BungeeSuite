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
public class CommandPlayerList extends BukkitSubCommandExecutor {
	
	@Override
	public boolean requiredPlayer() {
		return true;
	}
	
	@Override
	public String getName() {
		return "playerlist";
	}

	@Override
	public String getUsages() {
		return "�鿴���������";
	}

	@Override
	public BukkitSubCommandParameter[] getArgs() {
		return new BukkitSubCommandParameter[] { new BukkitSubCommandParameter("����", true) };
	}

	@Override
	public void run(CommandSender sender, Command arg1, String label, String[] args) {
		TabooLibBukkit.getInst().getBukkitChannelExecutor().playerList(getOnlinePlayer(), args[0], result -> {
			if (result[0].equals("-")) {
				TLogger.send(sender, "&4������ &c" + args[0] + " &4������");
			}
			else if (args[0].equalsIgnoreCase("all")) {
				TLogger.send(sender, "&7��ǰȫ�����: &f" + Arrays.asList(result));
			}
			else {
				TLogger.send(sender, "&7������ &f" + args[0] + " &7��ǰ���: &f" + Arrays.asList(result));
			}
		});
	}
}
