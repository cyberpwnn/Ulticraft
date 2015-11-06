package com.ulticraft.component;

import java.io.File;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.ulticraft.Ulticraft;
import com.ulticraft.composite.PlayerData;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.DataManager;
import com.ulticraft.uapi.UMap;

public class DataComponent extends Component implements Listener
{
	private File base;
	private UMap<Player, PlayerData> cache;
	
	public DataComponent(Ulticraft pl)
	{
		super(pl);
		this.base = new File(pl.getDataFolder(), "data");
		this.cache = new UMap<Player, PlayerData>();
	}
	
	public void enable()
	{
		super.enable();
		pl.register(this);
	}
	
	public void disable()
	{
		pl.unRegister(this);
		super.disable();
	}
	
	public void create(Player player)
	{
		PlayerData pd = new PlayerData(player);
		cache.put(player, pd);
	}
	
	public void load(Player player)
	{
		if(cache.containsKey(player))
		{
			return;
		}
		
		DataManager dm = new DataManager(pl, toFileName(player));
		PlayerData pd = (PlayerData) dm.readYAML(PlayerData.class);
		
		if(pd != null)
		{
			cache.put(player, pd);
		}
	}
	
	public boolean save(Player player)
	{
		if(flush(player))
		{
			cache.remove(player);
			return true;
		}
		
		return false;
	}
	
	public boolean flush(Player player)
	{
		if(!cache.containsKey(player))
		{
			return false;
		}
		
		DataManager dm = new DataManager(pl, toFileName(player));
		dm.writeYAML(get(player));
		
		return true;
	}
	
	public PlayerData get(Player player)
	{
		if(!cache.containsKey(player))
		{
			load(player);
		}
		
		return cache.get(player);
	}
	
	public void join(Player player)
	{
		if(!exists(player))
		{
			create(player);
			//new player
		}
		
		else
		{
			load(player);
		}
	}
	
	public void quit(Player player)
	{
		if(save(player))
		{
			//saved
		}
		
		else
		{
			//failed
		}
	}
	
	public File toFileName(Player player)
	{
		return new File(base, player.getUniqueId().toString() + ".yml");
	}
	
	public boolean exists(Player player)
	{
		return toFileName(player).exists();
	}
	
	private void verify(File dir)
	{
		if(!dir.getParentFile().exists())
		{
			verify(dir.getParentFile());
		}
		
		dir.mkdir();
	}
	
	public void verifyFile(File file)
	{
		if(!file.getParentFile().exists())
		{
			verify(file.getParentFile());
		}
		
		try
		{
			file.createNewFile();
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayer(PlayerJoinEvent e)
	{
		join(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayer(PlayerQuitEvent e)
	{
		quit(e.getPlayer());
	}
}
