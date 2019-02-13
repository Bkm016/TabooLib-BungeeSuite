package me.skymc.taboolib.bungeesuite.bungee;

import me.skymc.taboolib.bungeesuite.TabooLibBungee;
import me.skymc.taboolib.bungeesuite.events.BungeeCommandEvent;
import me.skymc.taboolib.bungeesuite.message.MessageBuilder;
import me.skymc.taboolib.bungeesuite.timeable.Timeable;
import me.skymc.taboolib.bungeesuite.util.ByteUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TBungeeChannel implements Listener {

    private List<TBungeeChannelTask> tasks = new CopyOnWriteArrayList<>();

    public TBungeeChannel(Plugin plugin) {
        BungeeCord.getInstance().getPluginManager().registerListener(plugin, this);
        plugin.getProxy().getScheduler().schedule(plugin, () -> {
            for (TBungeeChannelTask task : tasks) {
                if (task instanceof Timeable && task.isTimeLess()) {
                    tasks.remove(task);
                    if (task.getRunnableTimeless() != null) {
                        task.getRunnableTimeless().run();
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
                try {
                    task.getRunnable().run(e.getArgs());
                } catch (Exception ignored) {
                }
                tasks.remove(task);
            }
        }
    }

    public void sendBungeeMessage(ProxiedPlayer player, String... args) {
        sendBungeeMessage(player.getServer(), args);
    }

    public void sendBungeeMessage(Server server, String... args) {
        TabooLibBungee.getInstance().getProxy().getScheduler().runAsync(TabooLibBungee.getInstance(), () -> {
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
                server.sendData("taboolib|out", byteArrayOutputStream.toByteArray());
            }
        });
    }

    public List<TBungeeChannelTask> getTasks() {
        return this.tasks;
    }
}
