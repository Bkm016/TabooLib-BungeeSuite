package me.skymc.taboolib.bungeesuite.listener;

import com.google.common.io.ByteStreams;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import me.skymc.taboolib.bungeesuite.events.BungeeCommandEvent;
import me.skymc.taboolib.bungeesuite.message.MessageBuilder;
import me.skymc.taboolib.bungeesuite.message.ReadResult;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class ListenerBungeeMessage implements Listener {

    @EventHandler
    public void onMessage(PluginMessageEvent e) {
        if (!e.isCancelled() && e.getSender() instanceof Server && e.getTag().equalsIgnoreCase("taboolib_in")) {
            try {
                ReadResult readResult = MessageBuilder.readMessage(ByteStreams.newDataInput(e.getData()).readUTF());
                if (!readResult.isFull()) {
                    return;
                }
                UUID uuid = UUID.fromString(readResult.getCurrentMessages().get(0).getUid());
                JsonArray array = (JsonArray) new JsonParser().parse(readResult.build());
                String[] packet = new String[array.size()];
                int bound = array.size();
                for (int i = 0; i < bound; i++) {
                    packet[i] = array.get(i).getAsString();
                }
                BungeeCord.getInstance().getPluginManager().callEvent(new BungeeCommandEvent((Server) e.getSender(), uuid, packet));
                MessageBuilder.MESSAGE_CACHES.remove(uuid.toString());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
