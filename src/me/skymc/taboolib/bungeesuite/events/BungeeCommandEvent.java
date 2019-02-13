package me.skymc.taboolib.bungeesuite.events;

import me.skymc.taboolib.bungeesuite.TabooLibBungee;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

import java.util.UUID;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class BungeeCommandEvent extends Event implements Cancellable {
	
	private Server sender;
	private String[] args;
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
	
	public String getString(int index) {
		return getString(index, "");
	}
	
	public int getInteger(int index) {
		return getInteger(index, 0);
	}
	
	public double getDouble(int index) {
		return getDouble(index, 0);
	}
	
	public long getLong(int index) {
		return getLong(index, 0);
	}
	
	public boolean getBoolean(int index) {
		return getBoolean(index, false);
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
	
	public long getLong(int index, long defaultVault) {
		try {
			return index < args.length ? Long.valueOf(args[index]) : defaultVault;
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

	public Server getSender() {
		return this.sender;
	}

	public String[] getArgs() {
		return this.args;
	}

	public UUID getUid() {
		return this.uid;
	}
}
