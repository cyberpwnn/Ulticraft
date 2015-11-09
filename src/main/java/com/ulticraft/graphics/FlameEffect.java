package com.ulticraft.graphics;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Area;
import com.ulticraft.uapi.ParticleEffect;

public class FlameEffect extends TrailEffect
{
	public FlameEffect(Ulticraft pl, Location start, Vector direction)
	{
		super(pl, start, direction);
		setEffect(ParticleEffect.FLAME);
		setFilterSet(new VectorFilterSet().add(new RandomVectorFilter(0.1f, 0.1f, 0.1f)));
	}
	
	public void tick(ParticleIteration pi)
	{
		super.tick(pi);
		
		if(!pi.getLocation().getBlock().getType().equals(Material.AIR))
		{
			stop();
			pi.getLocation().getWorld().createExplosion(pi.getLocation(), 0f);
			return;
		}
		
		Area a = new Area(pi.getLocation(), (double) 2);
		
		for(Entity i : a.getNearbyEntities())
		{
			if(!i.getType().equals(EntityType.PLAYER))
			{
				if(i instanceof LivingEntity)
				{
					stop();
					pi.getLocation().getWorld().createExplosion(pi.getLocation(), 0f);
					i.setFireTicks(80);
					((LivingEntity)i).damage(5f);
				}
			}
		}
	}
}
