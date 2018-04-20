package me.skymc.taboolib.bungeesuite.util;

import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import me.skymc.taboolib.bungeesuite.logger.TLogger;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class ByteUtils {
	
	public static String serialize(String var) {
		return Base64.getEncoder().encodeToString(var.getBytes(StandardCharsets.UTF_8));
	}
	
	public static String deSerialize(String var) {
		return new String(Base64.getDecoder().decode(var), StandardCharsets.UTF_8);
	}
	
	public static String[] serialize(String... var) {
		String[] varEncode = new String[var.length];
		for (int i = 0 ; i < var.length ; i++) {
			varEncode[i] = Base64.getEncoder().encodeToString(var[i].getBytes(StandardCharsets.UTF_8));
		}
		return varEncode;
	}
	
	public static String[] deSerialize(String... var) {
		String[] varEncode = new String[var.length];
		for (int i = 0 ; i < var.length ; i++) {
			varEncode[i] = new String(Base64.getDecoder().decode(var[i]), StandardCharsets.UTF_8);
		}
		return varEncode;
	}
	
	public static String[] readPacket(byte[] args) {
		ByteArrayDataInput byteArrayDataInput = null;
		String[] packet = null;
		try {
			byteArrayDataInput = ByteStreams.newDataInput(args);
			packet = deSerialize(byteArrayDataInput.readUTF().split("\\|"));
		} catch (Exception err) {
			TLogger.error("Invalid PluginMessage: &c" + err.getMessage());
		}
		if (packet == null) {
			TLogger.error("Invalid PluginMessage: &cEmpty Packet");
		}
		return packet;
	}
	
	public static void close(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (Exception ignored) {
		}
	}

}
