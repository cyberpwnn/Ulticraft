package com.ulticraft.component;

import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(DataComponent.class)
public class GemsComponent extends Component
{
	public GemsComponent(Ulticraft pl)
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
