package com.ulticraft.ui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import com.ulticraft.Info;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Gui;
import com.ulticraft.uapi.Gui.Pane;

public class UIMain extends UI
{	
	public UIMain(Ulticraft pl, Player viewer)
	{
		super(pl, Info.UI_TITLE_ULTICRAFT, viewer);
	}
	
	public void show()
	{
		Gui gui = new Gui(viewer, pl);
		Pane pane = gui.new Pane(title);
		
		pane.new Element(Info.UI_TITLE_PERKS_MY, Material.BARRIER, -1, 2).setQuickRunnable(new Runnable()
		{
			public void run()
			{
				gui.close();
				new UIPerks(pl, viewer, false).show();
			}
		});
		
		pane.new Element(Info.UI_TITLE_PERKS_GET, Material.BARRIER, 1, 2).setQuickRunnable(new Runnable()
		{
			public void run()
			{
				gui.close();
				new UIPerks(pl, viewer, true).show();
			}
		});
		
		pane.setDefault();
		pane.show();
	}
}
