package com.ulticraft.component;

import com.ulticraft.Info;
import com.ulticraft.Ulticraft;
import com.ulticraft.command.GemCommandExecutor;
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
		pl.getCommand(Info.CMD_GEM).setExecutor(new GemCommandExecutor(pl));
		
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
}
