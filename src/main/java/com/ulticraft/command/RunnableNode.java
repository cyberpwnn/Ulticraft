package com.ulticraft.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ulticraft.uapi.UList;

public class RunnableNode implements Runnable
{
	private CommandSender sender;
	private UList<NodeParam> data;
	
	public CommandSender getSender()
	{
		return sender;
	}
	
	public void setSender(CommandSender sender)
	{
		this.sender = sender;
	}
	
	public UList<NodeParam> getData()
	{
		return data;
	}

	public void setData(UList<NodeParam> data)
	{
		this.data = data;
	}

	public boolean isPlayer()
	{
		return sender instanceof Player;
	}
	
	public Player getPlayer()
	{
		return (Player) sender;
	}
	
	public void run(CommandSender sender, UList<NodeParam> data)
	{
		setSender(sender);
		setData(data);
		
		run();
	}
	
	@Override
	public void run()
	{
		
	}
}
