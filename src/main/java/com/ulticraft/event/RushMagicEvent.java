package com.ulticraft.event;

import org.bukkit.entity.Player;
import com.ulticraft.composite.SpellType;

public class RushMagicEvent extends NeutralMagicEvent
{
	public RushMagicEvent(Player caster, SpellType type)
	{
		super(caster, type);
	}
}
