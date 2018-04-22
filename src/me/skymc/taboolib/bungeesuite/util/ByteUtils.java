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
	
	public static void main(String[] args) {
		System.out.println("serialize: " + serialize("≤‚ ‘–≈œ¢ ABCD 1234"));
		System.out.println("deSerialize: " + deSerialize("5rWL6K+V5L+h5oGvIEFCQ0QgMTIzNA=="));
	}
	
	public static String serialize(String var) {
		return Base64.getMimeEncoder().encodeToString(var.getBytes(StandardCharsets.UTF_8));
	}
	
	public static String deSerialize(String var) {
		return new String(Base64.getMimeDecoder().decode(var), StandardCharsets.UTF_8);
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
