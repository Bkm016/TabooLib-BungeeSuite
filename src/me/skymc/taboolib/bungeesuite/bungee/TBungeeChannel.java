package me.skymc.taboolib.bungeesuite.bungee;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.TabooLibBungee;
import me.skymc.taboolib.bungeesuite.events.BungeeCommandEvent;
import me.skymc.taboolib.bungeesuite.timeable.Timeable;
import me.skymc.taboolib.bungeesuite.util.ByteUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TBungeeChannel implements Listener {
	
	@Getter
	private List<TBungeeChannelTask> tasks = new CopyOnWriteArrayList<>();
	
	public TBungeeChannel(Plugin plugin) {
		BungeeCord.getInstance().getPluginManager().registerListener(plugin, this);
		plugin.getProxy().getScheduler().schedule(plugin, new Runnable() {
			
			@Override
			public void run() {
				for (TBungeeChannelTask task : tasks) {
					if (task instanceof Timeable && ((Timeable) task).isTimeLess()) {
						tasks.remove(task);
						if (task.getRunnableTimeless() != null) {
							task.getRunnableTimeless().run();
						}
					}
				}
			}
		}, 1, 1, TimeUnit.SECONDS);
	}
	
	@EventHandler
	public void onCommand(BungeeCommandEvent e) {
		if (e.isCancelled()) {
			return;
		}
		for (TBungeeChannelTask task : tasks) {
			if (task.getUid().equals(e.getUid())) {
				task.getRunnable().run(e.getArgs());
				tasks.remove(task);
			}
		}
	}
	
	public void sendBungeeMessage(ProxiedPlayer player, String...args) {
		TabooLibBungee.getInstance().getProxy().getScheduler().runAsync(TabooLibBungee.getInstance(), () -> player.sendData("taboolib|out",asByteArray(args)));
	}
	
	public void sendBungeeMessage(Server server, String... args) {
		TabooLibBungee.getInstance().getProxy().getScheduler().runAsync(TabooLibBungee.getInstance(), () -> server.sendData("taboolib|out",asByteArray(args)));
	}
	
	private byte[] asByteArray(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String value : args) {
			stringBuilder.append(value);
			stringBuilder.append(" ");
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
		try {
			dataOutputStream.writeUTF(stringBuilder.toString().trim());
		} catch (Exception ignored) {
		} finally {
			ByteUtils.close(dataOutputStream);
			ByteUtils.close(byteArrayOutputStream);
		}
		return byteArrayOutputStream.toByteArray();
	}
}
