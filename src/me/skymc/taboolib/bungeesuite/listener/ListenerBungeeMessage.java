package me.skymc.taboolib.bungeesuite.listener;

import java.util.UUID;

import me.skymc.taboolib.bungeesuite.events.BungeeCommandEvent;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import me.skymc.taboolib.bungeesuite.util.ByteUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class ListenerBungeeMessage implements Listener {
	
	@EventHandler
	public void onMessage(PluginMessageEvent e) {
		if (!e.isCancelled() && e.getSender() instanceof Server && e.getTag().equalsIgnoreCase("taboolib|in")) {
			String[] packet = ByteUtils.getStringArray(e.getData());
			if (packet.length < 2) {
				TLogger.error("Invalid PluginMessage: " + e.getTag());
				return;
			}
			
			UUID uuid = null;
			try {
				uuid = UUID.fromString(packet[0]);
			} catch (Exception err) {
				TLogger.error("Invalid Task UUID: &c" + packet[0]);
				return;
			}
			
			BungeeCord.getInstance().getPluginManager().callEvent(new BungeeCommandEvent((Server) e.getSender(), uuid, ArrayUtils.removeFirst(packet)));
		}
	}
}
