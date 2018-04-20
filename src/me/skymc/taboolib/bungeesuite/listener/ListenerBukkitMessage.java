package me.skymc.taboolib.bungeesuite.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import me.skymc.taboolib.bungeesuite.events.BukkitCommandEvent;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import me.skymc.taboolib.bungeesuite.util.ByteUtils;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class ListenerBukkitMessage implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] data) {
		if (channel.equalsIgnoreCase("taboolib|out")) {
			String[] packet = ByteUtils.readPacket(data);
			if (packet.length < 2) {
				TLogger.error("Invalid PluginMessage: &c" + channel);
				return;
			}
			
			UUID uuid = null;
			try {
				uuid = UUID.fromString(packet[0]);
			} catch (Exception err) {
				TLogger.error("Invalid Task UUID: &c" + packet[0]);
				return;
			}
			
			Bukkit.getPluginManager().callEvent(new BukkitCommandEvent(player, uuid, ArrayUtils.removeFirst(packet)));
		}
	}

}
