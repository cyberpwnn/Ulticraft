package com.ulticraft.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import com.ulticraft.composite.SpellType;
import com.ulticraft.uapi.UList;

public class FlameMagicEvent extends DestructiveMagicEvent
{
	public FlameMagicEvent(Player caster, SpellType type, UList<Entity> targetEntities, UList<Block> targetBlocks)
	{
		super(caster, type, targetEntities, targetBlocks);
	}
}
