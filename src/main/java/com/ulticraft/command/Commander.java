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
	
	public Node findBestMatch(String cmd, UList<String> argx)
	{
		return null;
	}
	
	public boolean resolveNode(CommandSender sender, UList<String> argx, String cmd)
	{
		
		
		return false;
	}
	
	public Commander addNode(Node n)
	{
		nodes.add(n);
		return this;
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
		return resolveNode(sender, new UList<String>(args), command.getName());
	}
}
