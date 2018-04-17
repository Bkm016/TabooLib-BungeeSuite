package me.skymc.taboolib.bungeesuite.bukkit;

import java.util.UUID;

import org.bukkit.entity.Player;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;
import me.skymc.taboolib.bungeesuite.timeable.Timeable;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TBukkitChannelTask extends Timeable {
	
	@Getter
	private TChannelResult runnable;
	@Getter
	private Runnable runnableTimeless;
	@Getter
	private TBukkitChannel channel;
	@Getter
	private Player sender;
	@Getter
	private String[] commands;
	@Getter
	private UUID uid = UUID.randomUUID();
	
	private TBukkitChannelTask(long effective) {
		super(effective);
	}
	
	public static TBukkitChannelTask createTask() {
		return new TBukkitChannelTask(5 * 1000L);
	}
	
	public static TBukkitChannelTask createTask(long effective) {
		return new TBukkitChannelTask(effective);
	}
	
	public TBukkitChannelTask channel(TBukkitChannel channel) {
		this.channel = channel;
		return this;
	}
	
	public TBukkitChannelTask sender(Player sender) {
		this.sender = sender;
		return this;
	}
	
	public TBukkitChannelTask result(TChannelResult runnable) {
		this.runnable = runnable;
		return this;
	}
	
	public TBukkitChannelTask timeless(Runnable runnable) {
		this.runnableTimeless = runnable;
		return this;
	}
	
	public TBukkitChannelTask command(String... commands) {
		this.commands = ArrayUtils.addFirst(commands, uid.toString());
		return this;
	}
	
	public void run() {
		if (sender == null || channel == null || commands == null) {
			TLogger.error("Invalid PluginCommand: &cMissing parameters");
			TLogger.error("Sender: &c" + (sender != null));
			TLogger.error("Channel: &c" + (channel != null));
			TLogger.error("Commands: &c" + (commands != null));
			return;
		}
		channel.sendBukkitMessage(sender, commands);
		if (runnable != null) {
			channel.getTasks().add(this);
		}
	}
}
