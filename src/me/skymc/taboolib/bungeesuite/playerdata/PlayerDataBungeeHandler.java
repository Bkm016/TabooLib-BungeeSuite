package me.skymc.taboolib.bungeesuite.playerdata;

import java.util.Arrays;

import me.skymc.taboolib.bungeesuite.events.BungeeCommandEvent;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import me.skymc.taboolib.bungeesuite.util.ProxiedPlayerTag;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class PlayerDataBungeeHandler implements Listener {
	
	public PlayerDataBungeeHandler(Plugin plugin) {
		BungeeCord.getInstance().getPluginManager().registerListener(plugin, this);
	}
	
	@EventHandler
	public void onCommand(BungeeCommandEvent e) {
		if (e.isCancelled() || !e.getArgs()[0].equals("PlayerData")) {
			return;
		}
		try {
			if (e.getArgs()[1].equalsIgnoreCase("Set")) {
				ProxiedPlayerTag.getInst().set(e.getArgs()[3], ArrayUtils.arrayJoin(e.getArgs(), 4), e.getArgs()[2]);
			}
			else if (e.getArgs()[1].equalsIgnoreCase("Remove")) {
				ProxiedPlayerTag.getInst().remove(e.getArgs()[3], e.getArgs()[2]);
			}
			else if (e.getArgs()[1].equalsIgnoreCase("Get")) {
				String result = ProxiedPlayerTag.getInst().getString(e.getArgs()[3], e.getArgs()[2]);
				e.response(result == null ? "null" : result);
			}
		} catch (Exception err) {
			TLogger.error("Invalid BungeeCommand: &c" + Arrays.asList(e.getArgs()));
			TLogger.error("Exception: &c" + err.getMessage());
		}
	}
}
