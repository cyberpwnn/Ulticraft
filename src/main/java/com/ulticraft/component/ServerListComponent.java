package com.ulticraft.component;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(AchievementComponent.class)
public class ServerListComponent extends Component implements Listener
{
	private File base;
	private CachedServerIcon empty;
	private CachedServerIcon full;
	private CachedServerIcon partialempty;
	private CachedServerIcon partialmid;
	private CachedServerIcon partialfull;
	
	public ServerListComponent(Ulticraft pl)
	{
		super(pl);
		pl.register(this);
		this.base = new File(pl.getDataFolder(), "images");
	}
	
	public void enable()
	{
		if(base.exists())
		{
			try
			{
				empty = Bukkit.loadServerIcon(new File(base, "empty.png"));
			}
			
			catch(Exception e)
			{
				pl.f("FAILED TO LOAD SERVER ICON: empty.png");
			}
			
			try
			{
				full = Bukkit.loadServerIcon(new File(base, "full.png"));
			}
			
			catch(Exception e)
			{
				pl.f("FAILED TO LOAD SERVER ICON: full.png");
			}
			
			try
			{
				partialempty = Bukkit.loadServerIcon(new File(base, "partial-empty.png"));
			}
			
			catch(Exception e)
			{
				pl.f("FAILED TO LOAD SERVER ICON: partial-empty.png");
			}
			
			try
			{
				partialfull = Bukkit.loadServerIcon(new File(base, "partial-full.png"));
			}
			
			catch(Exception e)
			{
				pl.f("FAILED TO LOAD SERVER ICON: partial-full.png");
			}
			
			try
			{
				partialmid = Bukkit.loadServerIcon(new File(base, "partial-mid.png"));
			}
			
			catch(Exception e)
			{
				pl.f("FAILED TO LOAD SERVER ICON: partial-mid.png");
			}
		}
		
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
	
	@EventHandler
	public void onPing(ServerListPingEvent e)
	{
		if(pl.onlinePlayers().isEmpty())
		{
			e.setServerIcon(empty);
		}
		
		else if(pl.onlinePlayers().size() > 10)
		{
			e.setServerIcon(full);
		}
		
		else if(pl.onlinePlayers().size() > 8)
		{
			e.setServerIcon(partialfull);
		}
		
		else if(pl.onlinePlayers().size() > 4)
		{
			e.setServerIcon(partialmid);
		}
		
		else if(pl.onlinePlayers().size() > 0)
		{
			e.setServerIcon(partialempty);
		}
	}
}
