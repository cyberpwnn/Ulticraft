package com.ulticraft.command;

import java.util.UUID;
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
	
	public Node resolveRootNode(String n)
	{
		for(Node i : nodes)
		{
			if(i.getParent() == null && n.equalsIgnoreCase(i.getName()))
			{
				return i;
			}
		}
		
		return null;
	}
	
	public void resolveNode(CommandSender sender, Node node, UList<String> argx)
	{
		if(argx.size() == 1)
		{
			node.execute(sender, argx.get(0));
			return;
		}
		
		if(argx.size() == 0)
		{
			node.execute(sender, null);
			return;
		}
		
		else
		{
			for(UUID i : node.getChildren())
			{
				for(Node j : nodes)
				{
					if(j.getUuid().equals(i) && j.getName().equalsIgnoreCase(argx.get(0)))
					{
						argx.remove(0);
						resolveNode(sender, j, argx);
						return;
					}
				}
			}
		}
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
		Node n = resolveRootNode(command.getName());
		
		if(n != null)
		{
			resolveNode(sender, n, new UList<String>(args));
		}
		
		return false;
	}
}
