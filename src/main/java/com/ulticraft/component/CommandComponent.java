package com.ulticraft.component;

import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(GemComponent.class)
public class CommandComponent extends Component
{
	public CommandComponent(Ulticraft pl)
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
