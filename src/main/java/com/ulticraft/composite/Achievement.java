package com.ulticraft.composite;

public class Achievement
{
	private String name;
	private Integer modifier;
	private Integer frequency;
	
	public Achievement(String name, Integer modifier, Integer frequency)
	{
		this.name = name;
		this.modifier = modifier;
		this.frequency = frequency;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Integer getModifier()
	{
		return modifier;
	}
	
	public void setModifier(Integer modifier)
	{
		this.modifier = modifier;
	}
	
	public Integer getFrequency()
	{
		return frequency;
	}
	
	public void setFrequency(Integer frequency)
	{
		this.frequency = frequency;
	}
}
