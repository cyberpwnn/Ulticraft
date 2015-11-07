package com.ulticraft;

import java.util.Collection;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.ulticraft.component.AchievementComponent;
import com.ulticraft.component.DataComponent;
import com.ulticraft.component.GemComponent;
import com.ulticraft.component.ManaComponent;
import com.ulticraft.component.NotificationComponent;
import com.ulticraft.component.PerkComponent;
import com.ulticraft.component.SecurityComponent;
import com.ulticraft.component.SoundComponent;
import com.ulticraft.component.SpellComponent;
import com.ulticraft.composite.PlayerData;
import com.ulticraft.uapi.ComponentManager;
import com.ulticraft.uapi.Dispatcher;

public class Ulticraft extends JavaPlugin
{
	private ComponentManager componentManager;
	private DataComponent dataComponent;
	private GemComponent gemComponent;
	private PerkComponent perkComponent;
	private AchievementComponent achievementComponent;
	private SpellComponent spellComponent;
	private NotificationComponent notificationComponent;
	private SoundComponent soundComponent;
	private SecurityComponent securityComponent;
	private ManaComponent manaComponent;
	private Dispatcher dispatcher;
	
	public void onEnable()
	{
		dispatcher = new Dispatcher(this);
		componentManager = new ComponentManager(this);
		
		dataComponent = new DataComponent(this);
		gemComponent = new GemComponent(this);
		perkComponent = new PerkComponent(this);
		achievementComponent = new AchievementComponent(this);
		spellComponent = new SpellComponent(this);
		notificationComponent = new NotificationComponent(this);
		soundComponent = new SoundComponent(this);
		manaComponent = new ManaComponent(this);
		securityComponent = new SecurityComponent(this);
		
		componentManager.register(dataComponent);
		componentManager.register(gemComponent);
		componentManager.register(perkComponent);
		componentManager.register(achievementComponent);
		componentManager.register(spellComponent);
		componentManager.register(notificationComponent);
		componentManager.register(soundComponent);
		componentManager.register(securityComponent);
		componentManager.register(manaComponent);
		
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
	
	public PlayerData gpd(Player player)
	{
		return dataComponent.get(player);
	}
	
	public ComponentManager getComponentManager()
	{
		return componentManager;
	}
	
	public ManaComponent getManaComponent()
	{
		return manaComponent;
	}
	
	public AchievementComponent getAchievementComponent()
	{
		return achievementComponent;
	}
	
	public SpellComponent getSpellComponent()
	{
		return spellComponent;
	}
	
	public SecurityComponent getSecurityComponent()
	{
		return securityComponent;
	}
	
	public NotificationComponent getNotificationComponent()
	{
		return notificationComponent;
	}
	
	public SoundComponent getSoundComponent()
	{
		return soundComponent;
	}
	
	public DataComponent getDataComponent()
	{
		return dataComponent;
	}
	
	public GemComponent getGemComponent()
	{
		return gemComponent;
	}
	
	public PerkComponent getPerkComponent()
	{
		return perkComponent;
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
