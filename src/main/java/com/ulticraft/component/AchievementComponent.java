package com.ulticraft.component;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import com.ulticraft.Info;
import com.ulticraft.Ulticraft;
import com.ulticraft.composite.Notification;
import com.ulticraft.composite.PlayerData;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend({GemComponent.class, NotificationComponent.class})
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
	
	public int getLevel(int at)
	{
		int lvl = 0;
		
		for(int i : Info.ACHIEVEMENT_PATH)
		{
			if(at < i)
			{
				return lvl;
			}
			
			lvl++;
		}
		
		return lvl;
	}
	
	public int getReward(int lvl)
	{
		return (int) (2 + (Math.pow(lvl + 2, 2) - Math.pow(lvl + 2, 1.6)));
	}
	
	public void check(Player p, String stat, int at)
	{
		if(at + 1 > Info.ACHIEVEMENT_MAX)
		{
			return;
		}
		
		if(getLevel(at) != getLevel(at + 1))
		{
			pl.getNotificationComponent().dispatch(p, new Notification().setSubTitle(String.format(Info.MSG_ACHIEVEMENT, Info.ACHIEVEMENT_PATH_TITLES[getLevel(at)], stat)).setSound(Info.SOUND_ACHIEVE));
			pl.getGemComponent().give(p, getReward(getLevel(at)));
			pl.getNotificationComponent().broadcast(p, new Notification().setSubTitle(String.format(Info.BROAD_ACHIEVEMENT, p.getName(), Info.ACHIEVEMENT_PATH_TITLES[getLevel(at)], stat)).setSound(Info.SOUND_ACHIEVE_DISTANT));
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayer(BlockBreakEvent e)
	{
		PlayerData pd = pl.gpd(e.getPlayer());
		check(e.getPlayer(), Info.STAT_BLOCKBREAK, pd.getBlockBreak());
		pd.setBlockBreak(pd.getBlockBreak() + 1);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayer(BlockPlaceEvent e)
	{
		PlayerData pd = pl.gpd(e.getPlayer());
		check(e.getPlayer(), Info.STAT_BLOCKPLACE, pd.getBlockPlace());
		pd.setBlockPlace(pd.getBlockPlace() + 1);
	}
}
