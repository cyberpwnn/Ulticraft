package com.ulticraft.component;

import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(PerkComponent.class)
public class UIComponent extends Component
{
	public UIComponent(Ulticraft pl)
	{
		super(pl);
	}
	
	public void enable()
	{
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
}
