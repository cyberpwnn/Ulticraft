package com.ulticraft.graphics;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import com.ulticraft.uapi.ParticleEffect;
import com.ulticraft.uapi.UList;

public class Line
{
	private Vector relativeA;
	private Vector relativeB;
	private Integer segments;
	private ParticleEffect effect;
	
	public Line(Vector relativeA, Vector relativeB, Integer segments, ParticleEffect effect)
	{
		this.relativeA = relativeA;
		this.relativeB = relativeB;
		this.segments = segments;
		this.effect = effect;
	}
	
	public Vector getRelativeA()
	{
		return relativeA;
	}
	
	public void setRelativeA(Vector relativeA)
	{
		this.relativeA = relativeA;
	}
	
	public Vector getRelativeB()
	{
		return relativeB;
	}
	
	public void setRelativeB(Vector relativeB)
	{
		this.relativeB = relativeB;
	}
	
	public Integer getSegments()
	{
		return segments;
	}
	
	public void setSegments(Integer segments)
	{
		this.segments = segments;
	}
	
	public ParticleEffect getEffect()
	{
		return effect;
	}

	public void setEffect(ParticleEffect effect)
	{
		this.effect = effect;
	}

	public UList<Particle> getParticles(Location center)
	{
		UList<Particle> particles = new UList<Particle>();
		
		Vector link = center.clone().add(relativeB).toVector().subtract(center.clone().add(relativeA).toVector());
		Float length = (float) link.length();
		Integer step = 0;
		Float ratio = length / segments;
		Vector v = link.multiply(ratio);
		Location loc = center.clone().add(relativeA).subtract(v);
		
		for(int i = 0; i < segments; i++)
		{
			if(step >= segments)
			{
				step = 0;
			}
			
			step++;
			loc.add(v);
			
			particles.add(new Particle(effect, center.clone().subtract(loc.clone()).toVector()));
		}
		
		return particles;
	}
}
