package me.skymc.taboolib.bungeesuite.permission;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.bungee.TBungeeChannel;
import me.skymc.taboolib.bungeesuite.bungee.TBungeeChannelTask;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class PermissionBungeeHandler {
	
	@Getter
	private TBungeeChannel channel;
	
	public PermissionBungeeHandler(TBungeeChannel channel) {
		this.channel = channel;
	}
	
	public void hasPermission(ProxiedPlayer player, String permission, TChannelResult runnable, Runnable timeless) {
		TBungeeChannelTask.createTask()
			.channel(channel)
			.target(player)
			.command("Permission", "Has", player.getName(), permission)
			.result(runnable)
			.timeless(timeless)
			.run();
	}
	
	public void primaryGroup(ProxiedPlayer player, TChannelResult runnable, Runnable timeless) {
		TBungeeChannelTask.createTask()
			.channel(channel)
			.target(player)
			.command("Permission", "Group", player.getName())
			.result(runnable)
			.timeless(timeless)
			.run();
	}
}
