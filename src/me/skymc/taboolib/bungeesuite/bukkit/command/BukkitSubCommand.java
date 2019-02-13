package me.skymc.taboolib.bungeesuite.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public interface BukkitSubCommand {
	
	String getName();
	
	String getUsages();
	
	BukkitSubCommandParameter[] getArgs();
	
	void run(CommandSender sender, Command arg1, String label, String[] args);

}
