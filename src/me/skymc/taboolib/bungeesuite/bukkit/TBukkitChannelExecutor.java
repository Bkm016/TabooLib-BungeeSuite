package me.skymc.taboolib.bungeesuite.bukkit;

import org.bukkit.entity.Player;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TBukkitChannelExecutor {
	
	@Getter
	private TBukkitChannel channel;
	
	public TBukkitChannelExecutor(TBukkitChannel channel) {
		this.channel = channel;
	}
	
	public void connect(Player target, String server) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(target)
			.command("BungeeCord", "ConnectOther", target.getName(), server)
			.run();
	}
	
	public void connectOther(Player sender, String target, String server) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(sender)
			.command("BungeeCord", "ConnectOther", target, server)
			.run();
	}
	
	public void message(Player sender, String target, String message) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(sender)
			.command("BungeeCord", "Message", target, message)
			.run();
	}
	
	public void broadcast(Player sender, String target, String message) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(sender)
			.command("BungeeCord", "Broadcast", target, message)
			.run();
	}
	
	public void kickPlayer(Player sender, String target, String reason) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(sender)
			.command("BungeeCord", "KickPlayer", target, reason)
			.run();
	}
	
	public void whois(Player sender, String target, TChannelResult runnable) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(sender)
			.command("BungeeCord", "Whois", target)
			.result(runnable)
			.run();
	}
	
	public void playerCount(Player sender, String target, TChannelResult runnable) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(sender)
			.command("BungeeCord", "PlayerCount", target)
			.result(runnable)
			.run();
	}
	
	public void playerList(Player sender, String target, TChannelResult runnable) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(sender)
			.command("BungeeCord", "PlayerList", target)
			.result(runnable)
			.run();
	}
	
	public void serverList(Player sender, TChannelResult runnable) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(sender)
			.command("BungeeCord", "ServerList")
			.result(runnable)
			.run();
	}
}
