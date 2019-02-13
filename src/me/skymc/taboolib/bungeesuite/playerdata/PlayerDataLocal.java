package me.skymc.taboolib.bungeesuite.playerdata;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;
import org.bukkit.entity.Player;

/**
 * @author Bkm016
 * @since 2018-04-20
 */
public class PlayerDataLocal {
	
	private String username;
	private String ip = "NULL";
	private String server = "NULL";
	
	public PlayerDataLocal(Player player) {
		this.username = player.getName();
		
		TabooLibBukkit.getInst().getBukkitChannelExecutor().whois(player, username, result -> {
			ip = result[0];
			if (!ip.equals("-")) {
				server = result[2];
			}
		});
	}

	public String getUsername() {
		return this.username;
	}

	public String getIp() {
		return this.ip;
	}

	public String getServer() {
		return this.server;
	}
}
