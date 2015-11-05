package com.ulticraft.uapi;

import com.ulticraft.Ulticraft;

public class Component
{
	protected final Ulticraft pl;
	private boolean enabled;
	
	public Component(Ulticraft pl)
	{
		this.pl = pl;
		enabled = false;
	}
	
	public void enable()
	{
		enabled = true;
	}
	
	public void disable()
	{
		enabled = false;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
}
