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
		
		else
		{
			if(sender.hasPermission(Info.PERM_GEM_GOD))
			{
				if(args[0].equalsIgnoreCase("get"))
				{
					if(args.length == 2)
					{
						if(pl.canFindPlayer(args[1]))
						{
							pl.msg(sender, String.format(Info.MSG_PLAYER_GEM_HAS, pl.findPlayer(args[1]).getName(), pl.gpd(pl.findPlayer(args[1])).getGems().toString()));
						}
						
						else
						{
							pl.msg(sender, String.format(Info.MSG_CANT_FIND_PLAYER, args[1]));
						}
					}
					
					else
					{
						pl.msg(sender, Info.CMD_HELP_ADMIN_GEM);
					}
				}
				
				else if(args[0].equalsIgnoreCase("give"))
				{
					if(args.length == 3)
					{
						if(pl.canFindPlayer(args[1]))
						{
							try
							{
								int gems = Integer.parseInt(args[2]);
								
								if(gems < 0)
								{
									pl.msg(sender, String.format(Info.MSG_NOT_POSITIVE, String.valueOf(gems)));
								}
								
								else if(gems < 1)
								{
									pl.msg(sender, String.format(Info.MSG_NOT_ZERO, String.valueOf(gems)));
								}
								
								else
								{
									pl.getGemComponent().give(pl.findPlayer(args[1]), gems);
									pl.msg(sender, String.format(Info.MSG_GEMS_GAVE, String.valueOf(gems), pl.findPlayer(args[1]).getName()));
								}
							}
							
							catch(NumberFormatException e)
							{
								pl.msg(sender, String.format(Info.MSG_NOT_NUMBER, args[2]));
							}
						}
						
						else
						{
							pl.msg(sender, String.format(Info.MSG_CANT_FIND_PLAYER, args[1]));
						}
					}
					
					else
					{
						pl.msg(sender, Info.CMD_HELP_ADMIN_GEM);
					}
				}
				
				else if(args[0].equalsIgnoreCase("take"))
				{
					if(args.length == 3)
					{
						if(pl.canFindPlayer(args[1]))
						{
							try
							{
								int gems = Integer.parseInt(args[2]);
								
								if(gems < 0)
								{
									pl.msg(sender, String.format(Info.MSG_NOT_POSITIVE, String.valueOf(gems)));
								}
								
								else if(gems < 1)
								{
									pl.msg(sender, String.format(Info.MSG_NOT_ZERO, String.valueOf(gems)));
								}
								
								else
								{
									if(pl.getGemComponent().has(pl.findPlayer(args[1]), gems))
									{
										pl.getGemComponent().take(pl.findPlayer(args[1]), gems);
										pl.msg(sender, String.format(Info.MSG_GEMS_TOOK, String.valueOf(gems), pl.findPlayer(args[1]).getName()));
									}
									
									else
									{
										pl.msg(sender, String.format(Info.MSG_GEMS_NOT_ENOUGH, pl.findPlayer(args[1]).getName(), String.valueOf(pl.getGemComponent().get(pl.findPlayer(args[1])))));
									}
								}
							}
							
							catch(NumberFormatException e)
							{
								pl.msg(sender, String.format(Info.MSG_NOT_NUMBER, args[2]));
							}
						}
						
						else
						{
							pl.msg(sender, String.format(Info.MSG_CANT_FIND_PLAYER, args[1]));
						}
					}
					
					else
					{
						pl.msg(sender, Info.CMD_HELP_ADMIN_GEM);
					}
				}
				
				else
				{
					pl.msg(sender, Info.CMD_HELP_ADMIN_GEM);
				}
			}
			
			else
			{
				pl.msg(sender, Info.CMD_HELP_GEM);
			}
		}
		
		return true;
	}
}
