package com.ulticraft.ui;

import org.bukkit.entity.Player;
import com.ulticraft.Ulticraft;
import com.ulticraft.composite.Perk;
import com.ulticraft.uapi.Gui;
import com.ulticraft.uapi.Gui.Pane;
import com.ulticraft.uapi.Gui.Pane.Element;

public class UIPerks extends UI
{
	private boolean buy;
	
	public UIPerks(Ulticraft pl, String title, Player viewer, boolean buy)
	{
		super(pl, title, viewer);
		this.buy = buy;
	}
	
	public void show()
	{
		Gui gui = new Gui(viewer, pl);
		Pane pane = gui.new Pane(title);
		pane.setDefault();
		
		int m = 0;
		
		if(buy)
		{
			for(Perk i : pl.getPerkComponent().get())
			{
				if(!pl.getPerkComponent().has(viewer, i))
				{
					add(pane, i, m);
					m++;
				}
			}
		}
		
		else
		{
			for(Perk i : pl.getPerkComponent().get(viewer))
			{
				add(pane, i, m);
				m++;
			}
		}
		
		pane.show();
	}
	
	public void add(Pane pane, Perk i, int slot)
	{
		Element e = pane.new Element(i.getName(), i.getMaterial(), slot);
		
		for(String j : i.getDescription())
		{
			e.addInfo(j);
		}
		
		if(!pl.getPerkComponent().has(viewer, i))
		{
			if(pl.getGemComponent().has(viewer, i.getCost()))
			{
				e.setRequirement("Costs " + i.getCost());
				e.setQuickRunnable(new Runnable()
				{
					@Override
					public void run()
					{
						if(pl.getGemComponent().has(viewer, i.getCost()))
						{
							pl.getPerkComponent().unlock(viewer, i);
							viewer.closeInventory();
						}
					}
				});
			}
			
			else
			{
				e.setFailedRequirement("Costs " + i.getCost());
			}
		}
	}
}
