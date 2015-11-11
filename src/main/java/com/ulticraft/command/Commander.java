package com.ulticraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.ulticraft.uapi.UList;

public class Commander implements CommandExecutor
{
	private UList<Node> nodes;
	
	public Commander()
	{
		this.nodes = new UList<Node>();
	}
	
	public boolean resolveNode(CommandSender sender, UList<String> argx)
	{
		Node possibleNode = null;
		
		for(Node n : nodes)
		{
			for(int i = 0; i < argx.size(); i++)
			{
				if(n.getParams().hasIndex(i) && argx.get(i).equalsIgnoreCase(n.getParams().get(i).getName()))
				{
					possibleNode = n;
				}
			}
		}
		
		if(possibleNode != null)
		{
			
		}
		
		return false;
	}

	public UList<Node> getNodes()
	{
		return nodes;
	}

	public void setNodes(UList<Node> nodes)
	{
		this.nodes = nodes;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		return resolveNode(sender, new UList<String>(args).qaddFirst(command.getName().toLowerCase()));
	}
}
