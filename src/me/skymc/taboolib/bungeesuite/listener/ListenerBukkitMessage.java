package me.skymc.taboolib.bungeesuite.listener;

import com.google.common.io.ByteStreams;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import me.skymc.taboolib.bungeesuite.events.BukkitCommandEvent;
import me.skymc.taboolib.bungeesuite.message.MessageBuilder;
import me.skymc.taboolib.bungeesuite.message.ReadResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class ListenerBukkitMessage implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] data) {
        if (channel.equalsIgnoreCase("taboolib:out")) {
            try {
                ReadResult readResult = MessageBuilder.readMessage(ByteStreams.newDataInput(data).readUTF());
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
                Bukkit.getPluginManager().callEvent(new BukkitCommandEvent(player, uuid, packet));
                MessageBuilder.MESSAGE_CACHES.remove(uuid.toString());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
