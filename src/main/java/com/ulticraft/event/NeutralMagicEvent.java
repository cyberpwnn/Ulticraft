package com.ulticraft.event;

import org.bukkit.entity.Player;
import com.ulticraft.composite.SpellType;

public class NeutralMagicEvent extends MagicEvent
{
	public NeutralMagicEvent(Player caster, SpellType type)
	{
		super(caster, type);
	}
}
