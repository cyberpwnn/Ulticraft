package com.ulticraft.component;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;
import com.ulticraft.ui.UIPerks;
import net.md_5.bungee.api.ChatColor;

@Depend(PerkComponent.class)
public class UIComponent extends Component implements Listener
{
	public UIComponent(Ulticraft pl)
	{
		super(pl);
	}
	
	public void enable()
	{
		pl.register(this);
		
		pl.scheduleSyncRepeatingTask(0, 0, new Runnable()
		{
			public void run()
			{
				for(Player i : pl.onlinePlayers())
				{
					if(i.isSneaking())
					{
						if(i.getInventory().getHelmet() != null && i.getInventory().getHelmet().getType().equals(Material.CHAINMAIL_HELMET))
						{
							i.getInventory().setHelmet(new ItemStack(Material.AIR));
						}
						
						int is = i.getInventory().getHeldItemSlot();
						
						for(int j = 0; j < 9; j++)
						{
							if(j != is && i.getInventory().getItem(j) != null && i.getInventory().getItem(j).getType().equals(Material.CHAINMAIL_HELMET))
							{
								i.getInventory().setItem(j, new ItemStack(Material.AIR));
							}
						}
						
						if(i.getItemInHand() == null || i.getItemInHand().getType().equals(Material.AIR))
						{
							ItemStack itm = new ItemStack(Material.CHAINMAIL_HELMET);
							ItemMeta itmm = itm.getItemMeta();
							itmm.setDisplayName(ChatColor.DARK_GRAY + "Press " + ChatColor.DARK_PURPLE + "Q");
							itm.setItemMeta(itmm);
							i.setItemInHand(itm);
						}
					}
					
					else
					{
						i.getInventory().remove(Material.CHAINMAIL_HELMET);
						
						if(i.getInventory().getHelmet() != null && i.getInventory().getHelmet().getType().equals(Material.CHAINMAIL_HELMET))
						{
							i.getInventory().setHelmet(new ItemStack(Material.AIR));
						}
					}
				}
			}
		});
		
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
	

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDrop(PlayerDropItemEvent e)
	{
		if(e.getPlayer().isSneaking())
		{
			if(e.getItemDrop().getItemStack().getType().equals(Material.CHAINMAIL_HELMET))
			{
				e.setCancelled(true);
				e.getPlayer().setSneaking(false);
				e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
				e.getPlayer().getInventory().remove(Material.CHAINMAIL_HELMET);
				pl.getDataComponent().refresh(e.getPlayer());
				openGui(e.getPlayer());
			}
		}
	}
	
	public void openGui(final Player p)
	{
		UIPerks ui = new UIPerks(pl, p, true);
		
		ui.show();
	}
}
