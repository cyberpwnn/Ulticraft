package com.ulticraft.component;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(GemComponent.class)
public class AchievementComponent extends Component implements Listener
{
	public AchievementComponent(Ulticraft pl)
	{
		super(pl);
		pl.register(this);
	}
	
	public void enable()
	{
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayer(BlockBreakEvent e)
	{
		pl.getManaComponent().setMana(e.getPlayer(), 0f);
	}
}
