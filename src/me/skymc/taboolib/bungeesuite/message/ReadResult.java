package me.skymc.taboolib.bungeesuite.message;

import me.skymc.taboolib.bungeesuite.util.ByteUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 坏黑
 * @Since 2019-02-13 11:07
 */
public class ReadResult {

    private List<MessageBuilder> currentMessages;
    private boolean full;

    public ReadResult(List<MessageBuilder> currentMessages, boolean full) {
        this.currentMessages = currentMessages;
        this.full = full;
    }

    public String build() {
        currentMessages.sort(Comparator.comparingInt(MessageBuilder::getProgressCurrent));
        return ByteUtils.deSerialize(currentMessages.stream().map(MessageBuilder::getData).collect(Collectors.joining()));
    }

    // *********************************
    //
    //        Getter and Setter
    //
    // *********************************

    public boolean isFull() {
        return full;
    }

    public List<MessageBuilder> getCurrentMessages() {
        return currentMessages;
    }
}
