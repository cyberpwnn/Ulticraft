package com.ulticraft.component;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.FastMath;

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
	
	public void play(Player p, String sound, Float volume, Float pitch)
	{
		String cmd = "playsound " + sound + " " + p.getName() + " ";
		
		if(volume == null)
		{
			volume = 1f;
		}
		
		if(pitch == null)
		{
			pitch = 1f;
		}
		
		cmd = cmd + volume.toString() + " " + pitch.toString();
		
		pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), cmd);
	}
	
	public void play(Location l, String sound, Float volume, Float pitch)
	{
		for(Player i : pl.onlinePlayers())
		{
			if(FastMath.isInRadius(l, i.getLocation(), 32))
			{
				String cmd = "playsound " + sound + " " + i.getName() + " ";
				
				if(volume == null)
				{
					volume = 1f;
				}
				
				if(pitch == null)
				{
					pitch = 1f;
				}
				
				cmd = cmd + volume.toString() + " " + pitch.toString();
				
				pl.getServer().dispatchCommand(pl.getServer().getConsoleSender(), cmd);
			}
		}
	}
}
