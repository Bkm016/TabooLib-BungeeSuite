package me.skymc.taboolib.bungeesuite.bungee;

import java.util.UUID;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;
import me.skymc.taboolib.bungeesuite.timeable.Timeable;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TBungeeChannelTask extends Timeable {
	
	@Getter
	private TChannelResult runnable;
	@Getter
	private Runnable runnableTimeless;
	@Getter
	private TBungeeChannel channel;
	@Getter
	private ProxiedPlayer target;
	@Getter
	private String[] commands;
	@Getter
	private UUID uid = UUID.randomUUID();
	
	private TBungeeChannelTask(long effective) {
		super(effective);
	}
	
	public static TBungeeChannelTask createTask() {
		return new TBungeeChannelTask(5 * 1000L);
	}
	
	public static TBungeeChannelTask createTask(long effective) {
		return new TBungeeChannelTask(effective);
	}
	
	public TBungeeChannelTask channel(TBungeeChannel channel) {
		this.channel = channel;
		return this;
	}
	
	public TBungeeChannelTask target(ProxiedPlayer target) {
		this.target = target;
		return this;
	}
	
	public TBungeeChannelTask result(TChannelResult runnable) {
		this.runnable = runnable;
		return this;
	}
	
	public TBungeeChannelTask timeless(Runnable runnable) {
		this.runnableTimeless = runnable;
		return this;
	}
	
	public TBungeeChannelTask command(String... commands) {
		this.commands = ArrayUtils.addFirst(commands, uid.toString());
		return this;
	}
	
	public void run() {
		if (target == null || channel == null || commands == null) {
			TLogger.error("Invalid PluginCommand: &cMissing parameters");
			TLogger.error("Sender: &c" + (target != null));
			TLogger.error("Channel: &c" + (channel != null));
			TLogger.error("Commands: &c" + (commands != null));
			return;
		}
		channel.sendBungeeMessage(target.getServer(), commands);
		if (runnable != null) {
			channel.getTasks().add(this);
		}
	}
}
