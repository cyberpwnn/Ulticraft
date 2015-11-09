package com.ulticraft.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import com.ulticraft.uapi.UList;

public class RestorativeMagicEvent extends MagicEvent
{
	private UList<Entity> targetEntities;
	private UList<Block> targetBlocks;
	
	public RestorativeMagicEvent(Player caster, UList<Entity> targetEntities, UList<Block> targetBlocks)
	{
		super(caster);
		this.targetEntities = targetEntities;
		this.targetBlocks = targetBlocks;
	}

	public UList<Entity> getTargetEntities()
	{
		return targetEntities;
	}

	public void setTargetEntities(UList<Entity> targetEntities)
	{
		this.targetEntities = targetEntities;
	}

	public UList<Block> getTargetBlocks()
	{
		return targetBlocks;
	}

	public void setTargetBlocks(UList<Block> targetBlocks)
	{
		this.targetBlocks = targetBlocks;
	}
}
