package com.ulticraft.component;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.FastMath;
import com.ulticraft.uapi.USound;

public class SoundComponent extends Component
{
	public SoundComponent(Ulticraft pl)
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
	
	public void play(Player p, USound sound)
	{
		Float volume = 1f;
		Float pitch = 1f;
		
		if(sound.getVolume() != null)
		{
			volume = sound.getVolume();
		}
		
		if(sound.getPitch() != null)
		{
			pitch = sound.getPitch();
		}
				
		if(sound.getiSound() != null)
		{
			p.playSound(p.getLocation(), sound.getiSound(), volume, pitch);
		}
		
		if(sound.getSound() != null)
		{
			String cmd = "playsound " + sound + " " + p.getName() + " " + volume.toString() + " " + pitch.toString();
			pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), cmd);
		}
	}
	
	public void play(Location l, USound sound)
	{
		for(Player i : pl.onlinePlayers())
		{
			if(FastMath.isInRadius(l, i.getLocation(), 32))
			{
				Float volume = 1f;
				Float pitch = 1f;
				
				if(sound.getVolume() != null)
				{
					volume = sound.getVolume();
				}
				
				if(sound.getPitch() != null)
				{
					pitch = sound.getPitch();
				}
						
				if(sound.getiSound() != null)
				{
					i.playSound(i.getLocation(), sound.getiSound(), volume, pitch);
				}
				
				if(sound.getSound() != null)
				{
					String cmd = "playsound " + sound + " " + i.getName() + " " + volume.toString() + " " + pitch.toString();
					pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), cmd);
				}
			}
		}
	}
}
