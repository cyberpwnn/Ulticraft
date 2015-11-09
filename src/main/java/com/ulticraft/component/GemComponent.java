package com.ulticraft.component;

import org.bukkit.entity.Player;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(DataComponent.class)
public class GemComponent extends Component
{
	public GemComponent(Ulticraft pl)
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
	
	public int get(Player p)
	{
		return pl.gpd(p).getGems();
	}
	
	public void set(Player p, int g)
	{
		pl.gpd(p).setGems(g);
	}
	
	public boolean has(Player p, int g)
	{
		return get(p) - g >= 0;
	}
	
	public void give(Player p, int g)
	{
		if(g < 1)
		{
			return;
		}
		
		set(p, get(p) + g);
	}
	
	public void take(Player p, int g)
	{
		if(g < 1 || !has(p, g))
		{
			return;
		}
		
		set(p, get(p) - g);
	}
}
