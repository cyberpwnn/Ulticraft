package com.ulticraft.graphics;

import org.bukkit.Location;
import com.ulticraft.uapi.ParticleEffect;

public class ParticleIteration
{
	private Location location;
	private ParticleEffect effect;
	
	public ParticleIteration(Location location, ParticleEffect effect)
	{
		this.location = location;
		this.effect = effect;
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	public ParticleEffect getEffect()
	{
		return effect;
	}
	
	public void setEffect(ParticleEffect effect)
	{
		this.effect = effect;
	}
}
