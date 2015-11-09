package com.ulticraft.command;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.ulticraft.Info;
import com.ulticraft.uapi.UList;

public class Node
{
	private UList<UUID> children;
	private UList<NodeFlag> flags;
	private String permission;
	private RunnableNode runnableNode;
	private Commander commander;
	private UUID parent;
	private UUID uuid;
	private String name;
	private NodeType type;
	
	public Node(Commander commander, String name, NodeType type)
	{
		this.children = new UList<UUID>();
		this.commander = commander;
		this.parent = null;
		this.flags = new UList<NodeFlag>();
		this.permission = null;
		this.runnableNode = null;
		this.uuid = UUID.randomUUID();
		this.name = name;
		this.type = type;
	}
	
	public void execute(CommandSender sender, String value)
	{
		if(permission != null && !sender.hasPermission(permission))
		{
			sender.sendMessage(Info.MSG_NO_PERMISSION);
			return;
		}
		
		if(flags.contains(NodeFlag.PLAYER_ONLY) && !(sender instanceof Player))
		{
			sender.sendMessage(Info.MSG_PLAYER_ONLY);
			return;
		}
		
		if(flags.contains(NodeFlag.CONSOLE_ONLY) && (sender instanceof Player))
		{
			sender.sendMessage(Info.MSG_CONSOLE_ONLY);
			return;
		}
		
		if(type.equals(NodeType.NODE) && value == null)
		{
			return;
		}
		
		if(type.equals(NodeType.NONE) && value == null)
		{
			if(runnableNode != null)
			{
				runnableNode.run(sender, null);
				return;
			}
		}
		
		if(value == null)
		{
			sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, "NULL", "NOT_NULL"));
			return;
		}
		
		if(type.equals(NodeType.STRING))
		{
			if(flags.contains(NodeFlag.ONE_WORD_STRING) && StringUtils.split(value, " ").length != 1)
			{
				sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeFlag.ONE_WORD_STRING.toString()));
				return;
			}
			
			else if(runnableNode != null)
			{
				runnableNode.run(sender, value);
				return;
			}
		}
		
		if(type.equals(NodeType.BOOLEAN))
		{
			Boolean m = null;
			
			if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("y"))
			{
				m = true;
			}
			
			if(value.equalsIgnoreCase("false") || value.equalsIgnoreCase("f") || value.equalsIgnoreCase("no") || value.equalsIgnoreCase("n"))
			{
				m = false;
			}
			
			if(m != null)
			{
				if(runnableNode != null)
				{
					runnableNode.run(sender, m);
					return;
				}
			}
			
			else
			{
				sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeType.BOOLEAN.toString()));
				return;
			}
		}
		
		if(type.equals(NodeType.DOUBLE))
		{
			try
			{
				double m = Double.parseDouble(value);
				
				if(flags.contains(NodeFlag.NON_ZERO_NUMBER) && m == 0)
				{
					sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeFlag.NON_ZERO_NUMBER.toString()));
					return;
				}
				
				if(flags.contains(NodeFlag.POSITIVE_NUMBER_ONLY) && m < 0)
				{
					sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeFlag.POSITIVE_NUMBER_ONLY.toString()));
					return;
				}
				
				if(flags.contains(NodeFlag.NEGITIVE_NUMBER_ONLY) && m > 0)
				{
					sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeFlag.NEGITIVE_NUMBER_ONLY.toString()));
					return;
				}
				
				if(runnableNode != null)
				{
					runnableNode.run(sender, m);
					return;
				}
			}
			
			catch(NumberFormatException e)
			{
				sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeType.DOUBLE.toString()));
				return;
			}
		}
		
		if(type.equals(NodeType.INTEGER))
		{
			try
			{
				int m = Integer.parseInt(value);
				
				if(flags.contains(NodeFlag.NON_ZERO_NUMBER) && m == 0)
				{
					sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeFlag.NON_ZERO_NUMBER.toString()));
					return;
				}
				
				if(flags.contains(NodeFlag.POSITIVE_NUMBER_ONLY) && m < 0)
				{
					sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeFlag.POSITIVE_NUMBER_ONLY.toString()));
					return;
				}
				
				if(flags.contains(NodeFlag.NEGITIVE_NUMBER_ONLY) && m > 0)
				{
					sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeFlag.NEGITIVE_NUMBER_ONLY.toString()));
					return;
				}
				
				if(runnableNode != null)
				{
					runnableNode.run(sender, m);
					return;
				}
			}
			
			catch(NumberFormatException e)
			{
				sender.sendMessage(String.format(Info.MSG_INVALID_INPUT, value, NodeType.INTEGER.toString()));
				return;
			}
		}
	}
	
	public RunnableNode getRunnableNode()
	{
		return runnableNode;
	}

	public void setRunnableNode(RunnableNode runnableNode)
	{
		this.runnableNode = runnableNode;
	}

	public void addNode(Node node)
	{
		node.setParent(uuid);
		children.add(node.getUuid());
		setType(NodeType.NODE);
		commander.getNodes().add(node);
	}
	
	public void addFlag(NodeFlag flag)
	{
		flags.add(flag);
	}

	public String getPermission()
	{
		return permission;
	}

	public void setPermission(String permission)
	{
		this.permission = permission;
	}

	public UList<NodeFlag> getFlags()
	{
		return flags;
	}

	public void setFlags(UList<NodeFlag> flags)
	{
		this.flags = flags;
	}

	public UList<UUID> getChildren()
	{
		return children;
	}

	public void setChildren(UList<UUID> children)
	{
		this.children = children;
	}

	public UUID getParent()
	{
		return parent;
	}

	public void setParent(UUID parent)
	{
		this.parent = parent;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public NodeType getType()
	{
		return type;
	}

	public void setType(NodeType type)
	{
		this.type = type;
	}
}
