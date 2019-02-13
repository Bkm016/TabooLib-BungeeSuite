package me.skymc.taboolib.bungeesuite.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import me.skymc.taboolib.bungeesuite.yaml.YamlConfiguration;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author Bkm016
 * @since 2018-06-21
 */
public class FileUtils {
	
	public static YamlConfiguration saveDefaultConfig(Plugin plugin, String filename) {
		saveResource(plugin, filename, false);
		return YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), filename));
	}
	
	public static void saveResource(Plugin plugin, String filename, boolean replace) {
		File file = new File(plugin.getDataFolder(), filename);
		if (!file.exists()) {
			inputStreamToFile(getResource(plugin, filename), file);
		} else if (replace) {
			file.delete();
			saveResource(plugin, filename, false);
		}
	}
	
    public static InputStream getResource(Plugin plugin, String filename) {
        try {
            URL url = plugin.getClass().getClassLoader().getResource(filename);
            if (url == null) {
                return null;
            } else {
                URLConnection connection = url.openConnection();
                connection.setUseCaches(false);
                return connection.getInputStream();
            }
        } catch (IOException ignored) {
            return null;
        }
    }

    public static void inputStreamToFile(InputStream inputStream, File file) {
        try {
            String text = new String(readFully(inputStream), Charset.forName("utf-8"));
            FileWriter fileWriter = new FileWriter(createNewFile(file));
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

    public static byte[] readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buf)) > 0) {
            stream.write(buf, 0, len);
        }
        return stream.toByteArray();
    }

    public static File createNewFile(File file) {
        if (file != null && !file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception ignored) {
            }
        }
        return file;
    }
}
