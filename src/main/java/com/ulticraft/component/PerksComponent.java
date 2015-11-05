package com.ulticraft.component;

import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(GemsComponent.class)
public class PerksComponent extends Component
{
	public PerksComponent(Ulticraft pl)
	{
		super(pl);
	}
	
	public void enable()
	{
		super.enable();
	}
	
	public void disable()
	{
		super.enable();
	}
}
