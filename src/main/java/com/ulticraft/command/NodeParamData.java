package com.ulticraft.command;

public class NodeParamData
{
	private String name;
	private NodeType type;
	private Object data;
	
	public NodeParamData(String name, NodeType type, Object data)
	{
		this.name = name;
		this.type = type;
		this.data = data;
	}
	
	public Object getData()
	{
		return data;
	}
	
	public void setData(Object data)
	{
		this.data = data;
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
	
	public String toString()
	{
		return type.toString() + ":" + name;
	}
}
