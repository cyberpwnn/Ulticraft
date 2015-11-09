package com.ulticraft.component;

import org.bukkit.entity.Player;
import com.ulticraft.Info;
import com.ulticraft.Ulticraft;
import com.ulticraft.composite.Notification;
import com.ulticraft.composite.Perk;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;
import com.ulticraft.uapi.NotificationPriority;
import com.ulticraft.uapi.UList;

@Depend(GemComponent.class)
public class PerkComponent extends Component
{
	private UList<Perk> perks;
	
	public PerkComponent(Ulticraft pl)
	{
		super(pl);
		perks = new UList<Perk>(Info.PERKS);
	}
	
	public void enable()
	{
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
	
	public Perk resolve(String k)
	{
		for(Perk i : perks)
		{
			if(i.getCodeName().equals(k.toLowerCase()))
			{
				return i;
			}
		}
		
		for(Perk i : perks)
		{
			if(i.getName().equals(k))
			{
				return i;
			}
		}
		
		for(Perk i : perks)
		{
			if(i.getName().toLowerCase().equals(k.toLowerCase()))
			{
				return i;
			}
		}
		
		return null;
	}
	
	public UList<Perk> get()
	{
		return perks;
	}
	
	public UList<Perk> getRequirements(Perk k)
	{
		UList<Perk> perks = new UList<Perk>();
		
		for(String i : k.getRequirements())
		{
			perks.add(resolve(i));
		}
		
		return perks;
	}
	
	public UList<Perk> get(Player p)
	{
		UList<Perk> perks = new UList<Perk>();
		
		for(String i : new UList<String>(pl.gpd(p).getPerks()))
		{
			Perk k = resolve(i);
			
			if(k != null)
			{
				perks.add(k);
			}
		}
		
		return perks;
	}
	
	public boolean has(Player p, Perk k)
	{
		return get(p).contains(k);
	}
	
	public boolean has(Player p, UList<Perk> k)
	{
		for(Perk i : k)
		{
			if(!has(p, i))
			{
				return false;
			}
		}
			
		return true;
	}
	
	public void unlock(Player p, Perk k)
	{
		if(has(p, k) || !pl.getGemComponent().has(p, k.getCost()) || !has(p, getRequirements(k)))
		{
			return;
		}
		
		pl.getPermissionComponent().give(p, k.getPermissions());
		pl.gpd(p).setPerks(new UList<String>(pl.gpd(p).getPerks()).qadd(k.getCodeName()).toString(","));
		pl.getNotificationComponent().dispatch(p, new Notification().setSubTitle(String.format(Info.MSG_PERK_UNLOCK, k.getName())).setSound(Info.SOUND_PERK_UNLOCK).setPriority(NotificationPriority.HIGH));
		pl.getNotificationComponent().broadcast(p, new Notification().setSubSubTitle(String.format(Info.BROAD_PERK_UNLOCK, p.getName(), k.getName())).setSound(Info.SOUND_PERK_UNLOCK_DISTANT).setPriority(NotificationPriority.LOW));
		pl.getGemComponent().take(p, k.getCost());
	}
}
