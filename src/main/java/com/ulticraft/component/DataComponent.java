package com.ulticraft.component;

import com.ulticraft.Ulticraft;
import com.ulticraft.composite.PlayerData;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.DataManager;

public class DataComponent extends Component
{
	public DataComponent(Ulticraft pl)
	{
		super(pl);
	}
	
	public void enable()
	{
		super.enable();
		
		PlayerData pd = new PlayerData();
		DataManager dm = new DataManager(pl, "test");
		dm.writeYAML(pd, true);
	}
	
	public void disable()
	{
		super.enable();
	}
}
