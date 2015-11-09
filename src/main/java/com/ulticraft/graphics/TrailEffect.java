package com.ulticraft.graphics;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.ParticleEffect;

public class TrailEffect
{
	private Location start;
	private Vector direction;
	private int distance;
	private int segmentsPerBlock;
	private int segmentsPerTick;
	private ParticleEffect effect;
	private ParticleSystem particleSystem;
	private VectorFilterSet filterSet;
	private Ulticraft pl;
	
	private ParticleIterator iterator;
	private boolean running;
	private int task;
	
	public TrailEffect(Ulticraft pl, Location start, Vector direction)
	{
		this.pl = pl;
		this.start = start;
		this.direction = direction;
		this.distance = 64;
		this.segmentsPerBlock = 3;
		this.segmentsPerTick = 9;
		this.effect = ParticleEffect.CRIT;
		this.particleSystem = new ParticleSystem(start);
		this.filterSet = new VectorFilterSet();
		this.running = false;
		
	}
	
	public void start()
	{
		Vector a = new Vector(0,0,0);
		Vector b = direction.clone().normalize().multiply(distance);
		
		Line line = new Line(a, b, distance*segmentsPerBlock, effect);
		
		particleSystem.addLine(line);
		particleSystem.applyFilter(filterSet);
		iterator = new ParticleIterator(particleSystem);
		
		running = true;
		task = pl.scheduleSyncRepeatingTask(0, 0, new Runnable()
		{
			@Override
			public void run()
			{
				if(!running)
				{
					pl.cancelTask(task);
					return;
				}
				
				for(int i = 0; i < segmentsPerBlock; i++)
				{
					if(iterator.hasNext())
					{
						if(!running)
						{
							pl.cancelTask(task);
							return;
						}
						
						tick(iterator.next());
					}
					
					else
					{
						pl.cancelTask(task);
						return;
					}
				}
			}
		});
	}
	
	public void tick(ParticleIteration pi)
	{
		draw();
	}
	
	public void draw()
	{
		if(iterator != null)
		{
			iterator.draw();
		}
	}
	
	public void stop()
	{
		running = false;
	}
	
	public VectorFilterSet getFilterSet()
	{
		return filterSet;
	}

	public void setFilterSet(VectorFilterSet filterSet)
	{
		this.filterSet = filterSet;
	}

	public Location getStart()
	{
		return start;
	}
	
	public void setStart(Location start)
	{
		this.start = start;
	}
	
	public Vector getDirection()
	{
		return direction;
	}
	
	public void setDirection(Vector direction)
	{
		this.direction = direction;
	}
	
	public ParticleEffect getEffect()
	{
		return effect;
	}

	public void setEffect(ParticleEffect effect)
	{
		this.effect = effect;
	}

	public ParticleSystem getParticleSystem()
	{
		return particleSystem;
	}

	public void setParticleSystem(ParticleSystem particleSystem)
	{
		this.particleSystem = particleSystem;
	}

	public int getDistance()
	{
		return distance;
	}
	
	public void setDistance(int distance)
	{
		this.distance = distance;
	}
	
	public int getSegmentsPerBlock()
	{
		return segmentsPerBlock;
	}
	
	public void setSegmentsPerBlock(int segmentsPerBlock)
	{
		this.segmentsPerBlock = segmentsPerBlock;
	}
	
	public int getSegmentsPerTick()
	{
		return segmentsPerTick;
	}
	
	public void setSegmentsPerTick(int segmentsPerTick)
	{
		this.segmentsPerTick = segmentsPerTick;
	}
}
