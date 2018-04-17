package me.skymc.taboolib.bungeesuite.events;

import java.util.UUID;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.TabooLibBungee;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class BungeeCommandEvent extends Event implements Cancellable {
	
	@Getter
	private Server sender;
	@Getter
	private String[] args;
	@Getter
	private UUID uid;
	private boolean cancel;
	
	public BungeeCommandEvent(Server sender, UUID uid, String[] args) {
		this.sender = sender;
		this.args = args;
		this.uid = uid;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancel;
	}
	
	public void response(String... args) {
		TabooLibBungee.getInstance().getBungeeChannel().sendBungeeMessage(sender, ArrayUtils.addFirst(args, uid.toString()));
	}
}
