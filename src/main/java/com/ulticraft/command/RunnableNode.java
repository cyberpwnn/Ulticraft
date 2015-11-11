package com.ulticraft.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ulticraft.uapi.UList;

public class RunnableNode implements Runnable
{
	private CommandSender sender;
	private UList<NodeParamData> data;
	
	public CommandSender getSender()
	{
		return sender;
	}
	
	public void setSender(CommandSender sender)
	{
		this.sender = sender;
	}
	
	public UList<NodeParamData> getData()
	{
		return data;
	}

	public void setData(UList<NodeParamData> data)
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
	
	public Player getDataPlayer()
	{
		if(data instanceof Player)
		{
			return (Player) data;
		}
		
		return null;
	}
	
	public void run(CommandSender sender, UList<NodeParamData> data)
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
