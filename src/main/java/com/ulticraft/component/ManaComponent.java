package com.ulticraft.component;

import org.bukkit.entity.Player;
import com.ulticraft.Info;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;
import net.md_5.bungee.api.ChatColor;

@Depend(DataComponent.class)
public class ManaComponent extends Component
{
	public ManaComponent(Ulticraft pl)
	{
		super(pl);
	}
	
	public void enable()
	{
		super.enable();
		
		pl.scheduleSyncRepeatingTask(0, 0, new Runnable()
		{
			@Override
			public void run()
			{
				for(Player i : pl.onlinePlayers())
				{
					regen(i);
				}
			}
		});
	}
	
	public void disable()
	{
		super.disable();
	}
	
	public float get(Player p)
	{
		return pl.gpd(p).getMana();
	}
	
	public float getMax(Player p)
	{
		return pl.gpd(p).getManaMax();
	}
	
	public float getRegen(Player p)
	{
		return pl.gpd(p).getManaRegen();
	}
	
	public void set(Player p, float mana)
	{
		if(mana > getMax(p))
		{
			pl.gpd(p).setMana(getMax(p));
		}
		
		else if(mana < 0)
		{
			pl.gpd(p).setMana(0f);
		}
		
		else
		{
			pl.gpd(p).setMana(mana);
		}
	}
	
	public boolean has(Player p, float mana)
	{
		return get(p) >= mana;
	}
	
	public void give(Player p, float mana)
	{
		set(p, get(p) + mana);
	}
	
	public void take(Player p, float mana)
	{
		if(mana > 0f || !has(p, mana))
		{
			return;
		}
		
		set(p, get(p) - mana);
	}
	
	public void setMax(Player p, float max)
	{
		pl.gpd(p).setManaMax(max);
	}
	
	public void setRegen(Player p, float regen)
	{
		pl.gpd(p).setManaRegen(regen);
	}
	
	public void increaseMax(Player p, float max)
	{
		setMax(p, getMax(p) + max);
	}
	
	public void decreaseMax(Player p, float max)
	{
		setMax(p, getMax(p) - max);
	}
	
	public void increaseRegen(Player p, float regen)
	{
		setRegen(p, getRegen(p) + regen);
	}
	
	public void decreaseRegen(Player p, float regen)
	{
		setRegen(p, getRegen(p) - regen);
	}
	
	public void regen(Player p)
	{
		give(p, getRegen(p));
	}
	
	public String getBar(Player p)
	{
		String bar = "" + ChatColor.LIGHT_PURPLE;
		Float mana = get(p);
		
		if(mana != getMax(p))
		{
			if(mana < Info.MANA_BAR_SPLIT)
			{
				bar = bar + Info.DINGBAT_HEXAGON;
				return bar + "  ";
			}
			
			int split = ((int) (mana / Info.MANA_BAR_SPLIT) * 2) + 1;
			
			for(int i = 0; i < split; i++)
			{
				bar = bar + Info.DINGBAT_HEXAGON;
			}
		}
		
		return bar + "  ";
	}
}
