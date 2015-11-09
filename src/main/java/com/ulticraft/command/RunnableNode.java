package com.ulticraft.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RunnableNode implements Runnable
{
	private CommandSender sender;
	private Object data;
	
	public CommandSender getSender()
	{
		return sender;
	}
	
	public void setSender(CommandSender sender)
	{
		this.sender = sender;
	}
	
	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
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
	
	public void run(CommandSender sender, Object data)
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
