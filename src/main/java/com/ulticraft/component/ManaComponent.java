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
					regenMana(i);
				}
			}
		});
	}
	
	public void disable()
	{
		super.disable();
	}
	
	public float getMana(Player p)
	{
		return pl.gpd(p).getMana();
	}
	
	public float getManaMax(Player p)
	{
		return pl.gpd(p).getManaMax();
	}
	
	public float getManaRegen(Player p)
	{
		return pl.gpd(p).getManaRegen();
	}
	
	public void setMana(Player p, float mana)
	{
		if(mana > getManaMax(p))
		{
			pl.gpd(p).setMana(getManaMax(p));
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
	
	public void regenMana(Player p)
	{
		setMana(p, getMana(p) + getManaRegen(p));
	}
	
	public String getManaBar(Player p)
	{
		String bar = "" + ChatColor.LIGHT_PURPLE;
		Float mana = getMana(p);
		
		if(mana != getManaMax(p))
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
