package me.skymc.taboolib.bungeesuite.bukkit.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.skymc.taboolib.bungeesuite.bukkit.command.bungee.CommandConnect;
import me.skymc.taboolib.bungeesuite.bukkit.command.bungee.CommandConnectOther;
import me.skymc.taboolib.bungeesuite.bukkit.command.bungee.CommandKickPlayer;
import me.skymc.taboolib.bungeesuite.bukkit.command.bungee.CommandMessage;
import me.skymc.taboolib.bungeesuite.bukkit.command.bungee.CommandPlayerCount;
import me.skymc.taboolib.bungeesuite.bukkit.command.bungee.CommandPlayerList;
import me.skymc.taboolib.bungeesuite.bukkit.command.bungee.CommandServerList;
import me.skymc.taboolib.bungeesuite.bukkit.command.bungee.CommandWhois;
import me.skymc.taboolib.bungeesuite.bukkit.command.playerdata.CommandPlayerDataGet;
import me.skymc.taboolib.bungeesuite.bukkit.command.playerdata.CommandPlayerDataRemove;
import me.skymc.taboolib.bungeesuite.bukkit.command.playerdata.CommandPlayerDataSet;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class BukkitCommand implements CommandExecutor {
	
	private List<BukkitSubCommandExecutor> subCommandExecutors = new ArrayList<>();
	
	public BukkitCommand() {
		subCommandExecutors.add(new CommandConnect());
		subCommandExecutors.add(new CommandConnectOther());
		subCommandExecutors.add(new CommandMessage());
		subCommandExecutors.add(new CommandKickPlayer());
		subCommandExecutors.add(new CommandWhois());
		subCommandExecutors.add(new CommandPlayerCount());
		subCommandExecutors.add(new CommandPlayerList());
		subCommandExecutors.add(new CommandServerList());
		subCommandExecutors.add(null);
		subCommandExecutors.add(new CommandPlayerDataSet());
		subCommandExecutors.add(new CommandPlayerDataRemove());
		subCommandExecutors.add(new CommandPlayerDataGet());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("");
			sender.sendMessage("��e��l----- ��6��lTabooLib BungeeSuite Commands ��e��l-----");
			sender.sendMessage("");
			for (BukkitSubCommandExecutor subCommand : subCommandExecutors) {
				sender.sendMessage(subCommand == null ? "" : subCommand.getCommandString(label));
			}
			sender.sendMessage("");
		} else {
			for (BukkitSubCommandExecutor subCommand : subCommandExecutors) {
				if (subCommand == null || !subCommand.getName().equalsIgnoreCase(args[0])) {
					continue;
				}
				if (!isConfirmType(sender, subCommand.getType())) {
					TLogger.send(sender, "��7ָ�� ��f" + args[0] + " ��7ֻ���� ��f" + subCommand.getType() + " ��7ִ��");
					return true;
				}
				if (!isConfirmSender(subCommand)) {
					TLogger.send(sender, "��7ָ�� ��f" + args[0] + " ��7��Ҫ��������߲���ִ��");
					return true;
				}
				String[] subCommandArgs = ArrayUtils.removeFirst(args);
				if (subCommand.isParameterConform(subCommandArgs)) {
					subCommand.run(sender, command, label, subCommandArgs);
				} else {
					TLogger.send(sender, "��7ָ�� ��f" + args[0] + " ��7��������");
					TLogger.send(sender, "��7��ȷ�÷�:");
					TLogger.send(sender, subCommand.getCommandString(label));
				}
				return true;
			}
		}
		return true;
	}
	
	private boolean isConfirmSender(BukkitSubCommandExecutor subCommand) {
		return !(subCommand.getType() == BukkitSubCommandType.CONSOLE && subCommand.requiredPlayer() && subCommand.getOnlinePlayer() == null);
	}
	
	private boolean isConfirmType(CommandSender sender, BukkitSubCommandType commandType) {
		if (commandType == BukkitSubCommandType.ALL) {
			return true;
		}
		if (sender instanceof Player && commandType == BukkitSubCommandType.PLAYER) {
			return true;
		}
		if (sender instanceof ConsoleCommandSender && commandType == BukkitSubCommandType.CONSOLE) {
			return true;
		}
		return false;
	}
}