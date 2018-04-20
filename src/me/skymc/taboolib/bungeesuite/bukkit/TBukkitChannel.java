package me.skymc.taboolib.bungeesuite.bukkit;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.events.BukkitCommandEvent;
import me.skymc.taboolib.bungeesuite.timeable.Timeable;
import me.skymc.taboolib.bungeesuite.util.ByteUtils;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TBukkitChannel implements Listener {
	
	@Getter
	private Plugin plugin;
	@Getter
	private List<TBukkitChannelTask> tasks = new CopyOnWriteArrayList<>();
	
	public TBukkitChannel(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
			
			@Override
			public void run() {
				for (TBukkitChannelTask task : tasks) {
					if (task instanceof Timeable && ((Timeable) task).isTimeLess()) {
						tasks.remove(task);
						if (task.getRunnableTimeless() != null) {
							task.getRunnableTimeless().run();
						}
					}
				}
			}
		}, 20, 20);
	}
	
	@EventHandler
	public void onMessage(BukkitCommandEvent e) {
		if (e.isCancelled()) {
			return;
		}
		for (TBukkitChannelTask task : tasks) {
			if (task.getUid().equals(e.getUid())) {
				task.getRunnable().run(e.getArgs());
				tasks.remove(task);
			}
		}
	}
	
	public void sendBukkitMessage(Player player, String... args) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String value : ByteUtils.serialize(args)) {
			stringBuilder.append(value);
			stringBuilder.append("|");
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
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> player.sendPluginMessage(plugin, "taboolib|in", byteArrayOutputStream.toByteArray()));
	}
	
	public Player getOnlinePlayer() {
		return Bukkit.getOnlinePlayers().size() == 0 ? null : Bukkit.getOnlinePlayers().iterator().next();
	}
}
