package com.ulticraft.composite;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import com.ulticraft.uapi.ParticleEffect;

public class Particle
{
	private ParticleEffect effect;
	private Vector relativeLocation;
	
	public Particle(ParticleEffect effect, Vector relativeLocation)
	{
		this.effect = effect;
		this.relativeLocation = relativeLocation;
	}
	
	public void draw(Location l)
	{
		effect.display(0, 0, 0, 0, 1, l.add(relativeLocation), 64);
	}

	public ParticleEffect getEffect()
	{
		return effect;
	}

	public void setEffect(ParticleEffect effect)
	{
		this.effect = effect;
	}

	public Vector getRelativeLocation()
	{
		return relativeLocation;
	}

	public void setRelativeLocation(Vector relativeLocation)
	{
		this.relativeLocation = relativeLocation;
	}
}
