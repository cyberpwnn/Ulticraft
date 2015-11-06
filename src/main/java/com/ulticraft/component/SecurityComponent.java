package com.ulticraft.component;

import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(AchievementComponent.class)
public class SecurityComponent extends Component
{
	public SecurityComponent(Ulticraft pl)
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
