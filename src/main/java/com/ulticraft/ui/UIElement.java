package com.ulticraft.ui;

import org.bukkit.Material;
import com.ulticraft.uapi.UList;

public class UIElement
{
	private String name;
	private Material material;
	private UList<String> description;
	
	public UIElement(String name, Material material)
	{
		this.name = name;
		this.material = material;
		this.description = new UList<String>();
	}
	
	public void addDescription(String desc)
	{
		description.add(desc);
	}
	
	public void resetDescription()
	{
		description.clear();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Material getMaterial()
	{
		return material;
	}
	
	public void setMaterial(Material material)
	{
		this.material = material;
	}
	
	public UList<String> getDescription()
	{
		return description;
	}
	
	public void setDescription(UList<String> description)
	{
		this.description = description;
	}
}
