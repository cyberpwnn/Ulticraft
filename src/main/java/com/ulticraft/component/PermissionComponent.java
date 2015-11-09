package com.ulticraft.component;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;
import com.ulticraft.uapi.UList;
import net.milkbowl.vault.permission.Permission;

@SuppressWarnings("deprecation")
@Depend(DataComponent.class)
public class PermissionComponent extends Component implements Listener
{
	private Permission permission;
	private boolean testEvent;
	private boolean testResult;
	
	public PermissionComponent(Ulticraft pl)
	{
		super(pl);
		testEvent = false;
		testResult = false;
	}
	
	public void enable()
	{
		RegisteredServiceProvider<Permission> permissionProvider = pl.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		
		if(permissionProvider != null)
		{
			permission = permissionProvider.getProvider();
		}
		
		pl.register(this);
		
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
	
	public void give(Player p, String node, String w)
	{
		permission.playerAdd(w, p, node);
	}
	
	public void give(Player p, String node)
	{
		permission.playerAdd(null, p, node);
	}
	
	public void give(Player p, String[] nodes, String w)
	{
		for(String i : nodes)
		{
			give(p, i, w);
		}
	}
	
	public void give(Player p, String[] nodes)
	{
		for(String i : nodes)
		{
			give(p, i);
		}
	}
	
	public void give(Player p, UList<String> nodes)
	{
		for(String i : nodes)
		{
			give(p, i);
		}
	}
	
	public void give(Player p, UList<String> nodes, String w)
	{
		for(String i : nodes)
		{
			give(p, i, w);
		}
	}
	
	public boolean canBreak(Player p, Block block)
	{
		testEvent = true;
		pl.getServer().getPluginManager().callEvent(new BlockBreakEvent(block, p));
		
		return testResult;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onTest(BlockBreakEvent e)
	{
		if(testEvent)
		{
			testResult = !e.isCancelled();
			testEvent = false;
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onTest(PlayerChatEvent e)
	{
		e.setMessage("Value is " + canBreak(e.getPlayer(), e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN)));
	}
}
