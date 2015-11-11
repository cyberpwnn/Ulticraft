package com.ulticraft.component;

import com.ulticraft.Ulticraft;
import com.ulticraft.command.Commander;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(GemComponent.class)
public class CommandComponent extends Component
{
	private Commander gemCommander;
	
	public CommandComponent(Ulticraft pl)
	{
		super(pl);
	}
	
	public void enable()
	{
		pl.getCommand("gem").setExecutor(gemCommander);
		
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
}
