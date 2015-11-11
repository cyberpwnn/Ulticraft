package com.ulticraft.composite;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import com.ulticraft.Info;
import com.ulticraft.uapi.UList;

public class Perk
{
	private final String name;
	private final String[] description;
	private final UList<String> permissions;
	private final UList<String> requirements;
	private final Integer cost;
	private final Material material;
	
	public Perk(String name, String[] description, String[] permissions, Integer cost, Material material)
	{
		this.name = name;
		this.description = description;
		this.permissions = new UList<String>(permissions).qadd(Info.NAME.toLowerCase() + "." + getCodeName());
		this.requirements = new UList<String>();
		this.cost = cost;
		this.material = material;
	}
	
	public Perk(String name, String[] description, Integer cost, Material material)
	{
		this.name = name;
		this.description = description;
		this.permissions = new UList<String>().qadd(Info.NAME.toLowerCase() + "." + getCodeName());
		this.requirements = new UList<String>();
		this.cost = cost;
		this.material = material;
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
	
	public Material getMaterial()
	{
		return material;
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
