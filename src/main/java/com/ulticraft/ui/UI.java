package com.ulticraft.ui;

import org.bukkit.entity.Player;
import com.ulticraft.uapi.UMap;

public class UI
{
	private UMap<Integer, UIElement> elements;
	private Player viewer;
	
	public UI(Player viewer)
	{
		this.elements = new UMap<Integer, UIElement>();
		this.viewer = viewer;
	}
	
	public UMap<Integer, UIElement> getElements()
	{
		return elements;
	}
	
	public void setElements(UMap<Integer, UIElement> elements)
	{
		this.elements = elements;
	}
	
	public Player getViewer()
	{
		return viewer;
	}
	
	public void setViewer(Player viewer)
	{
		this.viewer = viewer;
	}
}
