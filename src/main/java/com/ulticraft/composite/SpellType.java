package com.ulticraft.composite;

import com.ulticraft.Info;

public enum SpellType
{
	FLAME(Info.STAT_MAGICFLAMES, SkillType.DESTRUCTION), 
	FROST(Info.STAT_MAGICFROSTS, SkillType.DESTRUCTION), 
	SHOCK(Info.STAT_MAGICSHOCKS, SkillType.DESTRUCTION), 
	RUSH(Info.STAT_MAGICRUSHES, SkillType.NEUTRAL);
	
	private String name;
	private SkillType type;
	
	private SpellType(String name, SkillType type)
	{
		this.name = name;
		this.type = type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public SkillType getType()
	{
		return type;
	}
}
