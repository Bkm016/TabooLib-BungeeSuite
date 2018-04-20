package me.skymc.taboolib.bungeesuite.playerdata;

import org.bukkit.Bukkit;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;

/**
 * @author Bkm016
 * @since 2018-04-20
 */
public class PlayerDataLocal {
	
	@Getter
	private String username;
	@Getter
	private String ip = "NULL";
	@Getter
	private String server = "NULL";
	
	public PlayerDataLocal(String username) {
		this.username = username;
		TabooLibBukkit.getInst().getBukkitChannelExecutor().whois(Bukkit.getOnlinePlayers().iterator().next(), username, new TChannelResult() {
			
			@Override
			public void run(String[] result) {
				ip = result[0];
				if (!ip.equals("-")) {
					server = result[2];
				}
			}
		});
	}
}
