package com.ulticraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ulticraft.Info;
import com.ulticraft.Ulticraft;

public class GemCommandExecutor implements CommandExecutor
{
	private Ulticraft pl;
	
	public GemCommandExecutor(Ulticraft pl)
	{
		this.pl = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		boolean isPlayer = sender instanceof Player;
		Player player = isPlayer ? (Player) sender : null;
		
		if(args.length == 0)
		{
			if(isPlayer)
			{
				player.sendMessage(String.format(Info.MSG_GEMS_HAVE, String.valueOf(pl.gpd(player).getGems())));
			}
			
			else
			{
				pl.msg(sender, Info.CMD_HELP_ADMIN_GEM);
			}
		}
		
		return true;
	}
}
