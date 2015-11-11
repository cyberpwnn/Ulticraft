package com.ulticraft;

import java.util.Collection;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.ulticraft.component.AchievementComponent;
import com.ulticraft.component.AuctionComponent;
import com.ulticraft.component.CommandComponent;
import com.ulticraft.component.DataComponent;
import com.ulticraft.component.GemComponent;
import com.ulticraft.component.GraphicsComponent;
import com.ulticraft.component.ManaComponent;
import com.ulticraft.component.NotificationComponent;
import com.ulticraft.component.PerkComponent;
import com.ulticraft.component.PermissionComponent;
import com.ulticraft.component.SecurityComponent;
import com.ulticraft.component.SoundComponent;
import com.ulticraft.component.SpellComponent;
import com.ulticraft.component.UIComponent;
import com.ulticraft.composite.Notification;
import com.ulticraft.composite.PlayerData;
import com.ulticraft.uapi.ComponentManager;
import com.ulticraft.uapi.Dispatcher;
import com.ulticraft.uapi.NotificationPriority;
import net.md_5.bungee.api.ChatColor;

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
	private PermissionComponent permissionComponent;
	private AuctionComponent auctionComponent;
	private GraphicsComponent graphicsComponent;
	private UIComponent uiComponent;
	private CommandComponent commandComponent;
	private Dispatcher dispatcher;
	
	public void onEnable()
	{
		dispatcher = new Dispatcher(this);
		componentManager = new ComponentManager(this);
		dataComponent = new DataComponent(this);
		permissionComponent = new PermissionComponent(this);
		graphicsComponent = new GraphicsComponent(this);
		gemComponent = new GemComponent(this);
		perkComponent = new PerkComponent(this);
		achievementComponent = new AchievementComponent(this);
		spellComponent = new SpellComponent(this);
		notificationComponent = new NotificationComponent(this);
		soundComponent = new SoundComponent(this);
		manaComponent = new ManaComponent(this);
		securityComponent = new SecurityComponent(this);
		auctionComponent = new AuctionComponent(this);
		uiComponent = new UIComponent(this);
		commandComponent = new CommandComponent(this);
		
		componentManager.register(dataComponent);
		componentManager.register(permissionComponent);
		componentManager.register(gemComponent);
		componentManager.register(perkComponent);
		componentManager.register(graphicsComponent);
		componentManager.register(achievementComponent);
		componentManager.register(spellComponent);
		componentManager.register(notificationComponent);
		componentManager.register(soundComponent);
		componentManager.register(securityComponent);
		componentManager.register(auctionComponent);
		componentManager.register(manaComponent);
		componentManager.register(uiComponent);
		componentManager.register(commandComponent);
		
		//Enable component manager
		componentManager.enable();
				
		splash();
	}
	
	public void onDisable()
	{
		for(Player i : onlinePlayers())
		{
			i.closeInventory();
		}
		
		securityComponent.splashStart();
		componentManager.disable();
	}
	
	public void splash()
	{
		securityComponent.splashStop();
		notificationComponent.broadcast(new Notification().setSubTitle(Info.MSG_UPGRADED).setPriority(NotificationPriority.HIGH));
		notificationComponent.broadcast(new Notification().setSubSubTitle(Info.MSG_DEVELOPED_BY).setPriority(NotificationPriority.LOW));
		
		for(String i : Info.AUTHORS)
		{
			notificationComponent.broadcast(new Notification().setSubSubTitle(ChatColor.LIGHT_PURPLE + i).setPriority(NotificationPriority.LOW));
		}
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
	
	public PermissionComponent getPermissionComponent()
	{
		return permissionComponent;
	}

	public GraphicsComponent getGraphicsComponent()
	{
		return graphicsComponent;
	}

	public UIComponent getUiComponent()
	{
		return uiComponent;
	}

	public CommandComponent getCommandComponent()
	{
		return commandComponent;
	}

	public ManaComponent getManaComponent()
	{
		return manaComponent;
	}
	
	public void setComponentManager(ComponentManager componentManager)
	{
		this.componentManager = componentManager;
	}

	public void setDataComponent(DataComponent dataComponent)
	{
		this.dataComponent = dataComponent;
	}

	public void setGemComponent(GemComponent gemComponent)
	{
		this.gemComponent = gemComponent;
	}

	public void setPerkComponent(PerkComponent perkComponent)
	{
		this.perkComponent = perkComponent;
	}

	public void setAchievementComponent(AchievementComponent achievementComponent)
	{
		this.achievementComponent = achievementComponent;
	}

	public void setSpellComponent(SpellComponent spellComponent)
	{
		this.spellComponent = spellComponent;
	}

	public void setNotificationComponent(NotificationComponent notificationComponent)
	{
		this.notificationComponent = notificationComponent;
	}

	public void setSoundComponent(SoundComponent soundComponent)
	{
		this.soundComponent = soundComponent;
	}

	public void setSecurityComponent(SecurityComponent securityComponent)
	{
		this.securityComponent = securityComponent;
	}

	public void setManaComponent(ManaComponent manaComponent)
	{
		this.manaComponent = manaComponent;
	}

	public void setPermissionComponent(PermissionComponent permissionComponent)
	{
		this.permissionComponent = permissionComponent;
	}

	public void setAuctionComponent(AuctionComponent auctionComponent)
	{
		this.auctionComponent = auctionComponent;
	}

	public void setGraphicsComponent(GraphicsComponent graphicsComponent)
	{
		this.graphicsComponent = graphicsComponent;
	}

	public void setDispatcher(Dispatcher dispatcher)
	{
		this.dispatcher = dispatcher;
	}

	public AuctionComponent getAuctionComponent()
	{
		return auctionComponent;
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
	
	public boolean canFindPlayer(String search)
	{
		return findPlayer(search) == null ? false : true;
	}
	
	public Player findPlayer(String search)
	{
		for(Player i : onlinePlayers())
		{
			if(i.getName().equalsIgnoreCase(search))
			{
				return i;
			}
		}
		
		for(Player i : onlinePlayers())
		{
			if(i.getName().toLowerCase().contains(search.toLowerCase()))
			{
				return i;
			}
		}
		
		return null;
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
