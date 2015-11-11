package com.ulticraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ulticraft.Info;
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
			if(argx.size() == 1)
			{
				if(n.getParams().size() == 0 && n.getPath().get(0).equals(argx.get(0)))
				{
					possibleNode = n;
					break;
				}
			}
			
			for(int i = 0; i < argx.size(); i++)
			{
				if(n.getParams().hasIndex(i) && argx.hasIndex(i + 1) && argx.get(i + 1).equalsIgnoreCase(n.getParams().get(i).getName()))
				{
					possibleNode = n;
				}
			}
		}
		
		if(possibleNode != null)
		{
			if(sender instanceof Player)
			{
				if(possibleNode.getFlags().contains(NodeFlag.CONSOLE_ONLY))
				{
					sender.sendMessage(Info.MSG_CONSOLE_ONLY);
					return true;
				}
			}
			
			else
			{
				if(possibleNode.getFlags().contains(NodeFlag.PLAYER_ONLY))
				{
					sender.sendMessage(Info.MSG_PLAYER_ONLY);
					return true;
				}
			}
			
			UList<String> params = argx.copy();
			params.trimBeginning(possibleNode.getPath().size());
			
			if(params.size() == possibleNode.getParams().size())
			{
				UList<NodeParamData> npd = new UList<NodeParamData>();
				
				int k = 0;
				
				for(NodeParam i : possibleNode.getParams())
				{
					npd.add(i.toParamData(params.get(k)));
					k++;
				}
				
				possibleNode.getRunnableNode().run(sender, npd);
			}
			
			else
			{
				sender.sendMessage(String.format(Info.MSG_INVALID_PARAMS, params.toString(", "), possibleNode.getParams().toString(", ")));
				return true;
			}
		}
		
		else
		{
			sender.sendMessage(String.format(Info.MSG_UNKNOWN_SUB_COMMAND, "/" + argx.toString(" ")));
		}
		
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
		return resolveNode(sender, new UList<String>(args).qaddFirst(command.getName().toLowerCase()));
	}
}
