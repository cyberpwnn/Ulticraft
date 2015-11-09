package com.ulticraft.component;

import org.bukkit.entity.Player;
import com.ulticraft.Ulticraft;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;
import com.ulticraft.uapi.Title;
import com.ulticraft.uapi.UMap;
import net.md_5.bungee.api.ChatColor;

@Depend(AchievementComponent.class)
public class SecurityComponent extends Component
{
	private UMap<Player, UMap<String, Integer>> checks;
	
	public SecurityComponent(Ulticraft pl)
	{
		super(pl);
		this.checks = new UMap<Player, UMap<String, Integer>>();
	}
	
	public void enable()
	{
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
	
	public void splashStop()
	{
		new Title(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "DONE!", "  ", 0, 30, 10).send();
	}
	
	public void splashStart()
	{
		new Title(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Updating", ChatColor.DARK_GRAY + "Doing stuff...", 5, 50000, 4000).send();
	}

	public UMap<Player, UMap<String, Integer>> getChecks()
	{
		return checks;
	}

	public void setChecks(UMap<Player, UMap<String, Integer>> checks)
	{
		this.checks = checks;
	}
}
