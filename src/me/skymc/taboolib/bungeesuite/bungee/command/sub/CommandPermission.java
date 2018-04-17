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
		return "�鿴���Ȩ��";
	}

	@Override
	public BungeeSubCommandParameter[] getArgs() {
		return new BungeeSubCommandParameter[] { 
				new BungeeSubCommandParameter("���", true),
				new BungeeSubCommandParameter("Ȩ��", true)};
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		ProxiedPlayer player = BungeeCord.getInstance().getPlayer(args[0]);
		if (player == null) {
			TLogger.send(sender, "&4��� &c" + args[0] + " &4������");
			return;
		}
		
		TabooLibBungee.getInstance().getPermissionHandler().hasPermission(player, args[1], new TChannelResult() {
				
				@Override
				public void run(String[] result) {
					if (result[0].equals("true")) {
						TLogger.send(sender, "&7��� &f" + args[0] + " &7ӵ�� &f" + args[1] + " &7Ȩ��");
					} else {
						TLogger.send(sender, "&4��� &c" + args[0] + " &4û�� &c" + args[1] + " &4Ȩ��");
					}
				}
			},
			new Runnable() {
				
				@Override
				public void run() {
					TLogger.send(sender, "&4Ȩ�޻�ȡʧ��, ����������ԭ��");
					TLogger.send(sender, "&41. &c������ڵķ�����û��Ȩ�޲��");
					TLogger.send(sender, "&42. &c������ڵķ�����û�б����");
				}
			});
	}
}
