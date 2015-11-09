package com.ulticraft.graphics;

import org.bukkit.Location;
import com.ulticraft.uapi.UList;

public class ParticleIterator
{
	private final UList<Particle> particles;
	private final Location center;
	private int sector;
	private boolean next;
	private boolean draw;
	
	public ParticleIterator(ParticleSystem ps)
	{
		this.particles = ps.getParticles().copy();
		this.center = ps.getCenter().clone();
		this.sector = 0;
		this.next = false;
		this.draw = false;
	}

	public boolean hasNext()
	{
		next = false;
		draw = false;
		return particles.hasIndex(sector + 1);
	}
	
	public ParticleIteration next()
	{
		if(!hasNext() || next)
		{
			return null;
		}
		
		sector++;
		next = true;
		
		return new ParticleIteration(center.clone().add(particles.get(sector).getRelativeLocation()), particles.get(sector).getEffect());
	}
	
	public void draw()
	{
		if(draw)
		{
			return;
		}
		
		draw = true;
		particles.get(sector).getEffect().display(0, 0, 0, 0, 1, center.clone().add(particles.get(sector).getRelativeLocation()), 64);
	}
}
