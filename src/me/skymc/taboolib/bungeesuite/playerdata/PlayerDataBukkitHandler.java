package me.skymc.taboolib.bungeesuite.playerdata;

import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannel;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannelTask;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class PlayerDataBukkitHandler {
	
	private TBukkitChannel channel;
	private HashMap<String, PlayerDataLocal> playerData = new HashMap<>();
	
	public PlayerDataBukkitHandler(TBukkitChannel channel) {
		this.channel = channel;
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(x -> loadPlayerDataLocal(x));
			}
		}.runTaskTimerAsynchronously(channel.getPlugin(), 0, 20 * 30);
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
	
	public PlayerDataLocal getPlayerDataLocal(Player player) {
		return playerData.get(player.getName());
	}
	
	public void loadPlayerDataLocal(Player player) {
		playerData.put(player.getName(), new PlayerDataLocal(player));
	}
	
	public void unloadPlayerDataLocal(Player player) {
		playerData.remove(player.getName());
	}

	public TBukkitChannel getChannel() {
		return this.channel;
	}

	public HashMap<String, PlayerDataLocal> getPlayerData() {
		return this.playerData;
	}
}
