package me.skymc.taboolib.bungeesuite.bungee.command;

import net.md_5.bungee.api.CommandSender;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public interface BungeeSubCommand {
	
	String getName();
	
	String getUsages();
	
	BungeeSubCommandParameter[] getArgs();
	
	void run(CommandSender sender, String[] args);

}
