package me.skymc.taboolib.bungeesuite.util;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import me.skymc.taboolib.bungeesuite.TabooLibBungee;

/**
 * α - MetaData
 * 
 * @author sky
 * @since 2018-03-11 11:43:41
 */
public class TagUtils {
	
	private static TagUtils inst;
	private static ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> playerData = new ConcurrentHashMap<>();
	
	private TagUtils() {
		TabooLibBungee.getInstance().getProxy().getScheduler().schedule(TabooLibBungee.getInstance(), () -> playerData.keySet().stream().filter(player -> TabooLibBungee.getInstance().getProxy().getPlayer(player) == null).forEach(player -> playerData.remove(player).clear()), 180, 180, TimeUnit.SECONDS);
	}
	
	public static TagUtils getInst() {
		if (inst == null) {
			synchronized (TagUtils.class) {
				if (inst == null) {
					inst = new TagUtils();
				}
			}
		}
		return inst;
	}
	
	public void set(String key, Object value, String player) {
		if (contains(player)) {
			playerData.get(player).put(key, value);
		} else {
			ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
			map.put(key, value);
			playerData.put(player, map);
		}
	}
	
	public void set(String key, Object value, String... entities) {
		Arrays.stream(entities).forEach(ProxiedPlayer -> set(key, value, ProxiedPlayer));
	}
	
	public void set(String key, Object value, List<String> entities) {
		entities.forEach(ProxiedPlayer -> set(key, value, ProxiedPlayer));
	}
	
	public Object remove(String key, String player) {
		if (contains(player)) {
			playerData.get(player).remove(key);
			if (playerData.get(player).size() == 0) {
				return playerData.remove(player);
			}
		}
		return null;
	}
	
	public void remove(String key, String... entities) {
		for (String ProxiedPlayer : entities) remove(key, ProxiedPlayer);
	}
	
	public void remove(String key, List<String> entities) {
		for (String ProxiedPlayer : entities) remove(key, ProxiedPlayer);
	}
	
	public boolean contains(String player) {
		return playerData.containsKey(player);
	}
	
	public boolean hasKey(String key, String player) {
        return contains(player) && playerData.get(player).containsKey(key);
    }
	
	public Object get(String key, String ProxiedPlayer) {
		if (contains(ProxiedPlayer)) {
			return playerData.get(ProxiedPlayer).get(key);
		}
		return null;
	}
	
	public String getString(String key, String ProxiedPlayer) {
		Object object = get(key, ProxiedPlayer);
		if (object instanceof String) {
			return (String) object;
		}
		return null;
	}
	
	public int getInteger(String key, String ProxiedPlayer) {
		Object object = get(key, ProxiedPlayer);
		if (object != null) {
			return (int) object;
		}
		return 0;
	}
	
	public long getLong(String key, String ProxiedPlayer) {
		Object object = get(key, ProxiedPlayer);
		if (object != null) {
			return (long) object;
		}
		return 0L;
	}
	
	public boolean getBoolean(String key, String ProxiedPlayer) {
        Object object = get(key, ProxiedPlayer);
        return object != null && (boolean) object;
    }
	
	public double getDouble(String key, String ProxiedPlayer) {
		Object object = get(key, ProxiedPlayer);
		if (object != null) {
			return (double) object;
		}
		return 0D;
	}
	
	public double getFloat(String key, String ProxiedPlayer) {
		Object object = get(key, ProxiedPlayer);
		if (object != null) {
			return (float) object;
		}
		return 0F;
	}
	
	public short getShort(String key, String ProxiedPlayer) {
		Object object = get(key, ProxiedPlayer);
		if (object != null) {
			return (short) object;
		}
		return (short) 0;
	}
	
	public byte getByte(String key, String ProxiedPlayer) {
		Object object = get(key, ProxiedPlayer);
		if (object != null) {
			return (byte) object;
		}
		return (byte) 0;
	}
}
