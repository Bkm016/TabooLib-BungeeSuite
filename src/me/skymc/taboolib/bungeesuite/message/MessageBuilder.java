package me.skymc.taboolib.bungeesuite.message;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import me.skymc.taboolib.bungeesuite.util.ByteUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @Author 坏黑
 * @Since 2019-02-13 9:28
 */
public class MessageBuilder {

    /*
     * {
     *     "uid“: 0000-0000-0000-0000
     *     "progress": {
     *         “current": 1
     *         "all": 100
     *     }
     *     "data“: abc
     * }
     */

    public static int MESSAGE_LENGTH = 30000;
    public static Map<String, List<MessageBuilder>> MESSAGE_CACHES = Maps.newConcurrentMap();

    private String uid;
    private String data;
    private int progressCurrent;
    private int progressAll;

    public MessageBuilder(String uid, String data, int progressCurrent, int progressAll) {
        this.uid = uid;
        this.data = data;
        this.progressCurrent = progressCurrent;
        this.progressAll = progressAll;
    }

    public static String buildMessage(String[] message) {
        JsonArray array = new JsonArray();
        IntStream.range(1, message.length).mapToObj(i -> new JsonPrimitive(message[i])).forEach(array::add);
        return array.toString();
    }

    public static ReadResult readMessage(String message) {
        try {
            JsonObject json = (JsonObject) new JsonParser().parse(message);
            MessageBuilder messageBuilder = new MessageBuilder(json.get("uid").getAsString(), json.get("data").getAsString(), json.get("progress-current").getAsInt(), json.get("progress-all").getAsInt());
            List<MessageBuilder> messageCurrent = MESSAGE_CACHES.computeIfAbsent(message, m -> Lists.newArrayList());
            messageCurrent.add(messageBuilder);
            return new ReadResult(messageCurrent, messageCurrent.size() == messageBuilder.getProgressAll());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public static List<String> createMessage(String[] message) {
        List<String> messages = Lists.newArrayList();
        String data = ByteUtils.serialize(buildMessage(message));
        int times = (int) Math.ceil(data.length() / (double) MESSAGE_LENGTH);
        for (int i = 0; i < times; i++) {
            JsonObject json = new JsonObject();
            json.addProperty("uid", message[0]);
            json.addProperty("progress-current", i + 1);
            json.addProperty("progress-all", times);
            if (data.length() < MESSAGE_LENGTH) {
                json.addProperty("data", data);
            } else {
                json.addProperty("data", data.substring(0, data.length() - (data.length() - MESSAGE_LENGTH)));
                data = data.substring(MESSAGE_LENGTH);
            }
            messages.add(json.toString());
        }
        return messages;
    }

    // *********************************
    //
    //        Getter and Setter
    //
    // *********************************

    public String getUid() {
        return uid;
    }

    public String getData() {
        return data;
    }

    public int getProgressCurrent() {
        return progressCurrent;
    }

    public int getProgressAll() {
        return progressAll;
    }
}
