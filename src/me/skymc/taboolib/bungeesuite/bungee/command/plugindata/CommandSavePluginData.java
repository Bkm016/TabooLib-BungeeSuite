package me.skymc.taboolib.bungeesuite.bungee.command.plugindata;

import me.skymc.taboolib.bungeesuite.TabooLibBungee;
import me.skymc.taboolib.bungeesuite.bungee.command.BungeeSubCommandExecutor;
import me.skymc.taboolib.bungeesuite.bungee.command.BungeeSubCommandParameter;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import net.md_5.bungee.api.CommandSender;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class CommandSavePluginData extends BungeeSubCommandExecutor {
	
	@Override
	public String getName() {
		return "savedata";
	}

	@Override
	public String getUsages() {
		return "保存插件数据";
	}

	@Override
	public BungeeSubCommandParameter[] getArgs() {
		return new BungeeSubCommandParameter[] {};
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		TabooLibBungee.getInstance().getPluginDataHandler().saveFile();
		TLogger.info("保存 &7" + TabooLibBungee.getInstance().getPluginDataHandler().getPluginData().size() + " &f条插件数据");
	}
}
