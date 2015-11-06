package com.ulticraft;

import java.util.Collection;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.ulticraft.component.DataComponent;
import com.ulticraft.component.GemsComponent;
import com.ulticraft.component.PerksComponent;
import com.ulticraft.uapi.ComponentManager;
import com.ulticraft.uapi.Dispatcher;

public class Ulticraft extends JavaPlugin
{
	private ComponentManager componentManager;
	private DataComponent dataComponent;
	private GemsComponent gemsComponent;
	private PerksComponent perksComponent;
	private Dispatcher dispatcher;
	
	public void onEnable()
	{
		dispatcher = new Dispatcher(this);
		componentManager = new ComponentManager(this);
		
		dataComponent = new DataComponent(this);
		gemsComponent = new GemsComponent(this);
		perksComponent = new PerksComponent(this);
		
		componentManager.register(dataComponent);
		componentManager.register(gemsComponent);
		componentManager.register(perksComponent);
		
		componentManager.enable();
	}
	
	public int scheduleSyncRepeatingTask(int delay, int interval, Runnable runnable)
	{
		return getServer().getScheduler().scheduleSyncRepeatingTask(this, runnable, delay, interval);
	}
	
	public int scheduleSyncTask(int delay, Runnable runnable)
	{
		return getServer().getScheduler().scheduleSyncDelayedTask(this, runnable, delay);
	}
	
	public void cancelTask(int tid)
	{
		getServer().getScheduler().cancelTask(tid);
	}
	
	public Collection<? extends Player> onlinePlayers()
	{
		return getServer().getOnlinePlayers();
	}
	
	public void onDisable()
	{
		componentManager.disable();
	}
	
	public void register(Listener listener)
	{
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	public void unRegister(Listener listener)
	{
		HandlerList.unregisterAll(listener);
	}
	
	public ComponentManager getComponentManager()
	{
		return componentManager;
	}
	
	public DataComponent getDataComponent()
	{
		return dataComponent;
	}
	
	public GemsComponent getGemsComponent()
	{
		return gemsComponent;
	}
	
	public PerksComponent getPerksComponent()
	{
		return perksComponent;
	}
	
	public Dispatcher getDispatcher()
	{
		return dispatcher;
	}
	
	public void i(String... o)
	{
		dispatcher.info(o);
	}
	
	public void s(String... o)
	{
		dispatcher.success(o);
	}
	
	public void f(String... o)
	{
		dispatcher.failure(o);
	}
	
	public void w(String... o)
	{
		dispatcher.warning(o);
	}
	
	public void v(String... o)
	{
		dispatcher.verbose(o);
	}
	
	public void o(String... o)
	{
		dispatcher.overbose(o);
	}
	
	public void si(String... o)
	{
		dispatcher.sinfo(o);
	}
	
	public void ss(String... o)
	{
		dispatcher.ssuccess(o);
	}
	
	public void sf(String... o)
	{
		dispatcher.sfailure(o);
	}
	
	public void sw(String... o)
	{
		dispatcher.swarning(o);
	}
	
	public void sv(String... o)
	{
		dispatcher.sverbose(o);
	}
	
	public void so(String... o)
	{
		dispatcher.soverbose(o);
	}
}
