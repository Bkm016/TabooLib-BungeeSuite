package me.skymc.taboolib.bungeesuite.bungee.command;

import java.util.ArrayList;
import java.util.List;

import me.skymc.taboolib.bungeesuite.TabooLibBungee;
import me.skymc.taboolib.bungeesuite.bungee.command.sub.CommandGroup;
import me.skymc.taboolib.bungeesuite.bungee.command.sub.CommandPermission;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import me.skymc.taboolib.bungeesuite.util.TextCompute;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class BungeeCommand extends Command {
	
	private List<BungeeSubCommandExecutor> subCommandExecutors = new ArrayList<>();
	
	public BungeeCommand(String name) {
		super(name);
		subCommandExecutors.add(new CommandGroup());
		subCommandExecutors.add(new CommandPermission());
	}
	
	@Override
	public String[] getAliases() {
		return new String[] { "tlibbc", "tbc" };
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ConsoleCommandSender)) {
			TLogger.send(sender, "&4该命令只能由控制台执行");
			return;
		}
		if (args.length == 0) {
			TLogger.send(sender, "");
			TLogger.send(sender, "§e§l----- §6§lTabooLib BungeeCord Commands §e§l-----");
			TLogger.send(sender, "");
			for (BungeeSubCommandExecutor subCommand : subCommandExecutors) {
				TLogger.send(sender, subCommand == null ? "" : subCommand.getCommandString("tlibbc"));
			}
			TLogger.send(sender, "");
		} else {
			for (BungeeSubCommandExecutor subCommand : subCommandExecutors) {
				if (subCommand == null || !subCommand.getName().equalsIgnoreCase(args[0])) {
					continue;
				}
				String[] subCommandArgs = ArrayUtils.removeFirst(args);
				if (subCommand.isParameterConform(subCommandArgs)) {
					subCommand.run(sender, subCommandArgs);
				} else {
					TLogger.send(sender, "§7指令 §f" + args[0] + " §7参数不足");
					TLogger.send(sender, "§7正确用法:");
					TLogger.send(sender, subCommand.getCommandString("tlibbc"));
				}
				return;
			}
			TabooLibBungee.getInstance().getProxy().getScheduler().runAsync(TabooLibBungee.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					List<BungeeSubCommandExecutor> commandCompute = new ArrayList<>();
					for (BungeeSubCommandExecutor subCommandExecutor : subCommandExecutors) {
						if (subCommandExecutor != null) {
							commandCompute.add(subCommandExecutor);
						}
					}
					commandCompute.sort((b, a) -> Double.compare(TextCompute.similarDegree(args[0], a.getName()), TextCompute.similarDegree(args[0], b.getName())));
					TLogger.send(sender, "§7指令 §f" + args[0] + " §7不存在");
					TLogger.send(sender, "§7你可能需要:");
					TLogger.send(sender, commandCompute.get(0).getCommandString("tlibbc"));
				}
			});
		}
	}
	
}
