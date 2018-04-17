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
public class CommandPermission extends BungeeSubCommandExecutor {
	
	@Override
	public String getName() {
		return "permission";
	}

	@Override
	public String getUsages() {
		return "查看玩家权限";
	}

	@Override
	public BungeeSubCommandParameter[] getArgs() {
		return new BungeeSubCommandParameter[] { 
				new BungeeSubCommandParameter("玩家", true),
				new BungeeSubCommandParameter("权限", true)};
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
					if (result[0].equals("true")) {
						TLogger.send(sender, "&7玩家 &f" + args[0] + " &7拥有 &f" + args[1] + " &7权限");
					} else {
						TLogger.send(sender, "&4玩家 &c" + args[0] + " &4没有 &c" + args[1] + " &4权限");
					}
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
