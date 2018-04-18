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
	
	public String getString(int index, String defaultVault) {
		return index < args.length ? args[index] : defaultVault;
	}
	
	public int getInteger(int index, int defaultVault) {
		try {
			return index < args.length ? Integer.valueOf(args[index]) : defaultVault;
		} catch (Exception e) {
			return defaultVault;
		}
	}
	
	public double getDouble(int index, double defaultVault) {
		try {
			return index < args.length ? Double.valueOf(args[index]) : defaultVault;
		} catch (Exception e) {
			return defaultVault;
		}
	}
	
	public boolean getBoolean(int index, boolean defaultVault) {
		try {
			return index < args.length ? Boolean.valueOf(args[index]) : defaultVault;
		} catch (Exception e) {
			return defaultVault;
		}
	}
	
	public long getLong(int index, long defaultVault) {
		try {
			return index < args.length ? Long.valueOf(args[index]) : defaultVault;
		} catch (Exception e) {
			return defaultVault;
		}
	}
}
