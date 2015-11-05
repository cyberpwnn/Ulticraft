package com.ulticraft.uapi;

import org.bukkit.entity.Player;

public abstract class NewPlayerTrigger implements Runnable
{
	private Player player;
	
	public NewPlayerTrigger()
	{

	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public void run(Player p)
	{
		setPlayer(p);
		run();
	}
}
