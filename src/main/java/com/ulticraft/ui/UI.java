package com.ulticraft.ui;

import org.bukkit.entity.Player;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Gui;
import com.ulticraft.uapi.Gui.Pane;
import com.ulticraft.uapi.Gui.Pane.Element;
import com.ulticraft.uapi.UMap;

public class UI
{
	protected UMap<Integer, UIElement> elements;
	protected Player viewer;
	protected String title;
	protected Ulticraft pl;
	protected Gui current;
	
	public UI(Ulticraft pl, String title, Player viewer)
	{
		this.pl = pl;
		this.title = title;
		this.elements = new UMap<Integer, UIElement>();
		this.viewer = viewer;
	}
	
	public void show()
	{
		if(current != null)
		{
			current.close();
			current = null;
		}
		
		Gui gui = new Gui(viewer, pl);
		Pane pane = gui.new Pane(title);
		pane.setDefault();
		
		for(int i : elements.keySet())
		{
			UIElement em = elements.get(i);
			Element e = pane.new Element(title, em.getMaterial(), i);
			
			for(String j : em.getDescription())
			{
				e.addInfo(j);
			}
			
			e.setQuickRunnable(em.getRunnable());
		}
		
		gui.show();
		current = gui;
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
