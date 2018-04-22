package me.skymc.taboolib.bungeesuite.playerdata;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannel;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannelTask;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class PlayerDataBukkitHandler {
	
	@Getter
	private TBukkitChannel channel;
	
	public PlayerDataBukkitHandler(TBukkitChannel channel) {
		this.channel = channel;
	}
	
	public void setPlayerData(String target, String key, String value) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(channel.getOnlinePlayer())
			.command("PlayerData", "Set", target, key, value)
			.run();
	}
	
	public void removePlayerData(String target, String key) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(channel.getOnlinePlayer())
			.command("PlayerData", "Remove", target, key)
			.run();
	}
	
	public void getPlayerData(String target, String key, TChannelResult runnable) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(channel.getOnlinePlayer())
			.command("PlayerData", "Get", target, key)
			.result(runnable)
			.run();
	}
}
