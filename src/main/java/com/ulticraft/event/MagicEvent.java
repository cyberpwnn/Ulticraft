package com.ulticraft.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MagicEvent extends Event implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();
	private Player caster;
	private boolean cancelled;
	
	public MagicEvent(Player caster)
	{
		this.caster = caster;
		this.cancelled = false;
	}
	
	public Player getCaster()
	{
		return caster;
	}
	
	public void setCaster(Player caster)
	{
		this.caster = caster;
	}
	
	public HandlerList getHandlers()
	{
		return handlers;
	}
	
	public static HandlerList getHandlerList()
	{
		return handlers;
	}

	public boolean isCancelled()
	{
		return cancelled;
	}

	public void setCancelled(boolean cancel)
	{
		cancelled = cancel;
	}
}
