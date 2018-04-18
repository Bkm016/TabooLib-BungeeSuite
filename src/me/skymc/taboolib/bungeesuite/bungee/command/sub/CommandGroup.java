package me.skymc.taboolib.bungeesuite.bungee.command.sub;

import me.skymc.taboolib.bungeesuite.TabooLibBungee;
import me.skymc.taboolib.bungeesuite.bungee.command.BungeeSubCommandExecutor;
import me.skymc.taboolib.bungeesuite.bungee.command.BungeeSubCommandParameter;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class CommandGroup extends BungeeSubCommandExecutor {
	
	@Override
	public String getName() {
		return "group";
	}

	@Override
	public String getUsages() {
		return "查看玩家权限组";
	}

	@Override
	public BungeeSubCommandParameter[] getArgs() {
		return new BungeeSubCommandParameter[] { 
				new BungeeSubCommandParameter("玩家", true)};
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		ProxiedPlayer player = BungeeCord.getInstance().getPlayer(args[0]);
		if (player == null) {
			TLogger.send(sender, "&4玩家 &c" + args[0] + " &4不在线");
			return;
		}
		
		TabooLibBungee.getInstance().getPermissionHandler().hasPermission(player, args[1], new TChannelResult() {
				
				@Override
				public void run(String[] result) {
					TLogger.send(sender, "&7玩家 &f" + args[0] + " &7在 &f" + result[0] + " &7权限组");
				}
			},
			new Runnable() {
				
				@Override
				public void run() {
					TLogger.send(sender, "&4权限获取失败, 可能有以下原因");
					TLogger.send(sender, "&41. &c玩家所在的服务器没有权限插件");
					TLogger.send(sender, "&42. &c玩家所在的服务器没有本插件");
				}
			});
	}
}
