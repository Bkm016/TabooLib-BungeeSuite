package me.skymc.taboolib.bungeesuite.bungee;

import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;
import me.skymc.taboolib.bungeesuite.timeable.Timeable;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TBungeeChannelTask extends Timeable {
	
	private TChannelResult runnable;
	private Runnable runnableTimeless;
	private TBungeeChannel channel;
	private ProxiedPlayer target;
	private String[] commands;
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

	public TChannelResult getRunnable() {
		return this.runnable;
	}

	public Runnable getRunnableTimeless() {
		return this.runnableTimeless;
	}

	public TBungeeChannel getChannel() {
		return this.channel;
	}

	public ProxiedPlayer getTarget() {
		return this.target;
	}

	public String[] getCommands() {
		return this.commands;
	}

	public UUID getUid() {
		return this.uid;
	}
}
