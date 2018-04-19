package me.skymc.taboolib.bungeesuite.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import me.skymc.taboolib.bungeesuite.logger.TLogger;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class ByteUtils {
	
	public static String[] encode(String... var) {
		String[] varEncode = new String[var.length];
		for (int i = 0 ; i < var.length ; i++) {
			varEncode[i] = Base64.getEncoder().encodeToString(var[i].getBytes(StandardCharsets.UTF_8));
		}
		return varEncode;
	}
	
	public static String[] decode(String... var) {
		String[] varEncode = new String[var.length];
		for (int i = 0 ; i < var.length ; i++) {
			varEncode[i] = new String(Base64.getDecoder().decode(var[i]), StandardCharsets.UTF_8);
		}
		return varEncode;
	}
	
	public static String encode(String var) {
		return Base64.getEncoder().encodeToString(var.getBytes(StandardCharsets.UTF_8));
	}
	
	public static String decode(String var) {
		return new String(Base64.getDecoder().decode(var), StandardCharsets.UTF_8);
	}
	
	public static String[] getStringArray(byte[] args) {
		ByteArrayInputStream byteArrayInputStream = null;
		String[] packet = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(args);
			byte[] result = read(byteArrayInputStream);
			String source = new String(result, Charset.forName("UTF-8"));
			packet = source.trim().substring(1).split(" ");
		} catch (Exception err) {
			TLogger.error("Invalid PluginMessage: &c" + err.getMessage());
		} finally {
			close(byteArrayInputStream);
		}
		if (packet == null) {
			TLogger.error("Invalid PluginMessage: &cEmpty Packet");
		}
		return packet;
	}
	
	public static byte[] read(InputStream in) {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			while((len = in.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
		} catch (Exception ignored) {
		} finally {
			close(bos);
		}
		return bos.toByteArray();
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
