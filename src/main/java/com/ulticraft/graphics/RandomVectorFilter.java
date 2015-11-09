package com.ulticraft.graphics;

import java.util.Random;
import org.bukkit.util.Vector;
import com.ulticraft.uapi.VectorFilter;

public class RandomVectorFilter implements VectorFilter
{
	private float modX;
	private float modY;
	private float modZ;
	private Random r;
	
	public RandomVectorFilter(float modX, float modY, float modZ)
	{
		this.modX = modX;
		this.modY = modY;
		this.modZ = modZ;
		this.r = new Random();
	}
	
	@Override
	public Vector apply(Vector v)
	{
		
		
		return v.add(new Vector(modX * ((r.nextFloat() * 2) - 0.5f), modY * ((r.nextFloat() * 2) - 0.5f), modZ * ((r.nextFloat() * 2) - 0.5f)));
	}
}
