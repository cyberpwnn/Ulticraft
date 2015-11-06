package com.ulticraft.component;

import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.ulticraft.Ulticraft;
import com.ulticraft.composite.Notification;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;
import com.ulticraft.uapi.NotificationPriority;
import com.ulticraft.uapi.UList;
import com.ulticraft.uapi.UMap;

@Depend(SoundComponent.class)
public class NotificationComponent extends Component implements Listener
{
	private UMap<Player, UList<Notification>> queue;
	
	public NotificationComponent(Ulticraft pl)
	{
		super(pl);
		queue = new UMap<Player, UList<Notification>>();
	}
	
	public void enable()
	{
		pl.register(this);
		
		pl.scheduleSyncRepeatingTask(0, 30, new Runnable()
		{
			@Override
			public void run()
			{
				for(Player i : pl.onlinePlayers())
				{
					if(!queue.containsKey(i))
					{
						queue.put(i, new UList<Notification>());
					}
					
					NotificationPriority highest = NotificationPriority.LOWEST;
					Iterator<Notification> id = queue.get(i).iterator();
					
					for(Notification j : queue.get(i))
					{
						if(j.getPriority().getPriority() > highest.getPriority())
						{
							highest = j.getPriority();
						}
					}
					
					pl.o("Highest Priority is " + highest.toString());
					
					while(id.hasNext())
					{
						Notification j = id.next();
						if(j.getPriority().equals(highest))
						{
							j.getTitle().send(i);
							pl.getSoundComponent().play(i, j.getSound().getSound(), j.getSound().getVolume(), j.getSound().getPitch());
							id.remove();
							break;
						}
					}
				}
			}
		});
		
		super.enable();
	}
	
	public void disable()
	{
		pl.unRegister(this);
		
		super.disable();
	}
	
	public void dispatchNotification(Player p, Notification n)
	{
		UList<Notification> ns = queue.get(p) == null ? new UList<Notification>() : queue.get(p);
		ns.add(n);
		
		queue.put(p, ns);
	}
	
	@EventHandler
	public void onPlayer(PlayerQuitEvent e)
	{
		queue.remove(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayer(PlayerJoinEvent e)
	{
		queue.put(e.getPlayer(), new UList<Notification>());
	}
}
