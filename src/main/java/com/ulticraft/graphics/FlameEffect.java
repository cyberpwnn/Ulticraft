package com.ulticraft.graphics;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Area;
import com.ulticraft.uapi.ParticleEffect;

public class FlameEffect extends TrailEffect
{
	private Player caster;
	
	public FlameEffect(Ulticraft pl, Location start, Vector direction, Player caster)
	{
		super(pl, start, direction);
		this.caster = caster;
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
			if(i instanceof LivingEntity)
			{
				if(i instanceof Player)
				{
					Player t = (Player) i;
					if(t != caster)
					{
						stop();
						pi.getLocation().getWorld().createExplosion(pi.getLocation(), 0f);
						i.setFireTicks(80);
						t.damage(4f);
						return;
					}
				}
				
				stop();
				pi.getLocation().getWorld().createExplosion(pi.getLocation(), 0f);
				i.setFireTicks(80);
				((LivingEntity)i).damage(5f);
				return;
			}
			
			else
			{
				
			}
		}
	}
}
