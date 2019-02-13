package me.skymc.taboolib.bungeesuite.bukkit;

import me.skymc.taboolib.bungeesuite.events.BukkitCommandEvent;
import me.skymc.taboolib.bungeesuite.message.MessageBuilder;
import me.skymc.taboolib.bungeesuite.timeable.Timeable;
import me.skymc.taboolib.bungeesuite.util.ByteUtils;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TBukkitChannel implements Listener {

    private Plugin plugin;
    private List<TBukkitChannelTask> tasks = new CopyOnWriteArrayList<>();

    public TBukkitChannel(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (TBukkitChannelTask task : tasks) {
                if (task instanceof Timeable && task.isTimeLess()) {
                    tasks.remove(task);
                    if (task.getRunnableTimeless() != null) {
                        task.getRunnableTimeless().run();
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
                try {
                    task.getRunnable().run(e.getArgs());
                } catch (Exception ignored) {
                }
                tasks.remove(task);
            }
        }
    }

    public void sendBukkitMessage(Player player, String... args) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            for (String data : MessageBuilder.createMessage(args)) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeUTF(data);
                } catch (Exception ignored) {
                } finally {
                    ByteUtils.close(dataOutputStream);
                    ByteUtils.close(byteArrayOutputStream);
                }
                player.sendPluginMessage(plugin, "taboolib|in", byteArrayOutputStream.toByteArray());
            }
        });
    }

    public Player getOnlinePlayer() {
        return Bukkit.getOnlinePlayers().size() == 0 ? null : Bukkit.getOnlinePlayers().iterator().next();
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public List<TBukkitChannelTask> getTasks() {
        return this.tasks;
    }
}
