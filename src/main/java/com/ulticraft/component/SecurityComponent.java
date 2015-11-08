package com.ulticraft.component;

import org.bukkit.entity.Player;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;
import com.ulticraft.uapi.UMap;

@Depend(AchievementComponent.class)
public class SecurityComponent extends Component
{
	private UMap<Player, UMap<String, Integer>> checks;
	
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