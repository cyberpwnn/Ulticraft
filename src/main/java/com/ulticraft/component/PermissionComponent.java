package com.ulticraft.component;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;
import com.ulticraft.uapi.UList;
import net.milkbowl.vault.permission.Permission;

@Depend(DataComponent.class)
public class PermissionComponent extends Component implements Listener
{
	private Permission permission;
	
	public PermissionComponent(Ulticraft pl)
	{
		super(pl);
	}
	
	public void enable()
	{
		RegisteredServiceProvider<Permission> permissionProvider = pl.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		
		if(permissionProvider != null)
		{
			permission = permissionProvider.getProvider();
		}
				
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
}
