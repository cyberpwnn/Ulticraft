package com.ulticraft.ui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import com.ulticraft.Info;
import com.ulticraft.Ulticraft;
import com.ulticraft.composite.Perk;
import com.ulticraft.uapi.Gui;
import com.ulticraft.uapi.Gui.Pane;
import com.ulticraft.uapi.Gui.Pane.Element;

public class UIPerks extends UI
{
	private boolean buy;
	
	public UIPerks(Ulticraft pl, Player viewer, boolean buy)
	{
		super(pl, buy ? Info.UI_TITLE_PERKS_GET : Info.UI_TITLE_PERKS_MY, viewer);
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
		
		pane.new Element(Info.UI_ACTION_BACK, Material.CHAINMAIL_CHESTPLATE, 4, 4).setQuickRunnable(new Runnable()
		{
			public void run()
			{
				gui.close();
				new UIMain(pl, viewer).show();
			}
		});
		
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
						pl.getPerkComponent().unlock(viewer, i);
						viewer.closeInventory();
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
