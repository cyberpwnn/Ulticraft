package com.ulticraft.command;

import com.ulticraft.uapi.UList;

public class Node
{
	private UList<NodeParam> params;
	private UList<NodeFlag> flags;
	private UList<String> path;
	private RunnableNode runnableNode;
	
	public Node(UList<String> path, UList<NodeParam> params)
	{
		this.path = path;
		this.params = params;
	}
	
	public Node(UList<String> path)
	{
		this.path = path;
		this.params = new UList<NodeParam>();
	}
	
	public UList<NodeParam> getParams()
	{
		return params;
	}
	
	public void setParams(UList<NodeParam> params)
	{
		this.params = params;
	}
	
	public UList<NodeFlag> getFlags()
	{
		return flags;
	}
	
	public void setFlags(UList<NodeFlag> flags)
	{
		this.flags = flags;
	}
	
	public UList<String> getPath()
	{
		return path;
	}
	
	public void setPath(UList<String> path)
	{
		this.path = path;
	}

	public RunnableNode getRunnableNode()
	{
		return runnableNode;
	}

	public void setRunnableNode(RunnableNode runnableNode)
	{
		this.runnableNode = runnableNode;
	}
}
