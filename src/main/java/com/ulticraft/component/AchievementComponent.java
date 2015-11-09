package com.ulticraft.component;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import com.ulticraft.Info;
import com.ulticraft.Ulticraft;
import com.ulticraft.composite.Notification;
import com.ulticraft.composite.PlayerData;
import com.ulticraft.event.FlameMagicEvent;
import com.ulticraft.event.FrostMagicEvent;
import com.ulticraft.event.RushMagicEvent;
import com.ulticraft.event.ShockMagicEvent;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@SuppressWarnings("deprecation")
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
		check(p, stat, at, 1);
	}
	
	public void check(Player p, String stat, int at, int d)
	{
		if(at + 1 > Info.ACHIEVEMENT_MAX)
		{
			return;
		}
		
		if(getLevel(at) != getLevel(at + d))
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
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayer(EntityDeathEvent e)
	{
		if(!e.getEntity().getType().equals(EntityType.PLAYER) && e.getEntity().getKiller().getType().equals(EntityType.PLAYER))
		{
			PlayerData pdk = pl.gpd(e.getEntity().getKiller());
			check(e.getEntity().getKiller(), Info.STAT_COMBATKILLS, pdk.getCombatKills());
			pdk.setCombatKills(pdk.getCombatKills() + 1);
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayer(PlayerDeathEvent e)
	{
		PlayerData pd = pl.gpd(e.getEntity());
		check(e.getEntity(), Info.STAT_COMBATDEATHS, pd.getCombatDeaths());
		pd.setCombatDeaths(pd.getCombatDeaths() + 1);
		
		if(e.getEntity().getKiller().getType().equals(EntityType.PLAYER))
		{
			PlayerData pdk = pl.gpd(e.getEntity().getKiller());
			check(e.getEntity().getKiller(), Info.STAT_COMBATPLAYERKILLS, pdk.getCombatPlayerKills());
			pdk.setCombatPlayerKills(pdk.getCombatPlayerKills() + 1);
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayer(PlayerChatEvent e)
	{
		PlayerData pd = pl.gpd(e.getPlayer());
		check(e.getPlayer(), Info.STAT_CHATWORDS, pd.getChatWords(), StringUtils.split(" ").length);
		pd.setChatWords(pd.getChatWords() + 1);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayer(FlameMagicEvent e)
	{
		PlayerData pd = pl.gpd(e.getCaster());
		check(e.getCaster(), Info.STAT_MAGICFLAMES, pd.getMagicFlames());
		pd.setMagicFlames(pd.getMagicFlames() + 1);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayer(FrostMagicEvent e)
	{
		PlayerData pd = pl.gpd(e.getCaster());
		check(e.getCaster(), Info.STAT_MAGICFROSTS, pd.getMagicFrosts());
		pd.setMagicFrosts(pd.getMagicFrosts() + 1);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayer(ShockMagicEvent e)
	{
		PlayerData pd = pl.gpd(e.getCaster());
		check(e.getCaster(), Info.STAT_MAGICSHOCKS, pd.getMagicShocks());
		pd.setMagicShocks(pd.getMagicShocks() + 1);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayer(RushMagicEvent e)
	{
		PlayerData pd = pl.gpd(e.getCaster());
		check(e.getCaster(), Info.STAT_MAGICSHOCKS, pd.getMagicRushes());
		pd.setMagicRushes(pd.getMagicRushes() + 1);
	}
}
