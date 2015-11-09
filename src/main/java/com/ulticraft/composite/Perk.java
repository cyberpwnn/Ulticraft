package com.ulticraft.composite;

import org.bukkit.ChatColor;
import com.ulticraft.Info;
import com.ulticraft.uapi.UList;

public class Perk
{
	private final String name;
	private final String[] description;
	private final UList<String> permissions;
	private final UList<String> requirements;
	private final Integer cost;
	
	public Perk(String name, String[] description, String[] permissions, Integer cost)
	{
		this.name = name;
		this.description = description;
		this.permissions = new UList<String>(permissions).qadd(Info.NAME.toLowerCase() + "." + getCodeName());
		this.requirements = new UList<String>();
		this.cost = cost;
	}
	
	public Perk(String name, String[] description, Integer cost)
	{
		this.name = name;
		this.description = description;
		this.permissions = new UList<String>().qadd(Info.NAME.toLowerCase() + "." + getCodeName());
		this.requirements = new UList<String>();
		this.cost = cost;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getCodeName()
	{
		return ChatColor.stripColor(name).toLowerCase().replace(' ', '-');
	}
	
	public String[] getDescription()
	{
		return description;
	}
	
	public UList<String> getPermissions()
	{
		return permissions;
	}
	
	public UList<String> getRequirements()
	{
		return requirements;
	}
	
	public void addRequirement(Perk k)
	{
		requirements.add(k.getCodeName());
	}
	
	public Integer getCost()
	{
		return cost;
	}
}
