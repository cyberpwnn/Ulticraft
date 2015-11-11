package com.ulticraft.command;

import com.ulticraft.uapi.UList;

public class NodeParam
{
	private String name;
	private UList<NodeParamFlag> flags;
	private NodeType type;
	
	public NodeParam(String name, NodeType type)
	{
		this.name = name;
		this.type = type;
		this.flags = new UList<NodeParamFlag>();
	}
	
	public void addFlag(NodeParamFlag flag)
	{
		flags.add(flag);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public UList<NodeParamFlag> getFlags()
	{
		return flags;
	}

	public void setFlags(UList<NodeParamFlag> flags)
	{
		this.flags = flags;
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
