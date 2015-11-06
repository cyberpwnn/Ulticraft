package com.ulticraft.component;

import org.bukkit.event.Listener;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(DataComponent.class)
public class NotificationComponent extends Component implements Listener
{
	public NotificationComponent(Ulticraft pl)
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
