package com.ulticraft.component;

import org.bukkit.event.Listener;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;

public class DataComponent extends Component implements Listener
{
	public DataComponent(Ulticraft pl)
	{
		super(pl);
	}
	
	public void enable()
	{
		super.enable();
		pl.register(this);
	}
	
	public void disable()
	{
		pl.unRegister(this);
		super.disable();
	}
}
