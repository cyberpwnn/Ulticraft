package com.ulticraft.composite;

import com.ulticraft.uapi.Table;

public class PlayerData implements Table
{
	// Identity
	private String uuid;
	private String name;
	private String names;
	private String address;
	private String addresses;
	private String lastLogin;
	private String firstLogin;
	
	// Economy
	private Integer gems;
	private Integer gemsKnown;
	private Float mana;
	private Float manaRegen;
	private Float manaMax;
	private String perks;
	private String spells;
	private String friends;
	
	// Statistics
	private Integer blockBreak;
	private Integer blockPlace;
	private Integer combatKills;
	private Integer combatPlayerKills;
	private Integer combatDeaths;
	private Integer chatWords;
	private Integer timeOnline;
	
	public String getUuid()
	{
		return uuid;
	}
	
	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getNames()
	{
		return names;
	}
	
	public void setNames(String names)
	{
		this.names = names;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getAddresses()
	{
		return addresses;
	}
	
	public void setAddresses(String addresses)
	{
		this.addresses = addresses;
	}
	
	public String getLastLogin()
	{
		return lastLogin;
	}
	
	public void setLastLogin(String lastLogin)
	{
		this.lastLogin = lastLogin;
	}
	
	public String getFirstLogin()
	{
		return firstLogin;
	}
	
	public void setFirstLogin(String firstLogin)
	{
		this.firstLogin = firstLogin;
	}
	
	public Integer getGems()
	{
		return gems;
	}
	
	public void setGems(Integer gems)
	{
		this.gems = gems;
	}
	
	public Integer getGemsKnown()
	{
		return gemsKnown;
	}
	
	public void setGemsKnown(Integer gemsKnown)
	{
		this.gemsKnown = gemsKnown;
	}
	
	public Float getMana()
	{
		return mana;
	}
	
	public void setMana(Float mana)
	{
		this.mana = mana;
	}
	
	public Float getManaRegen()
	{
		return manaRegen;
	}
	
	public void setManaRegen(Float manaRegen)
	{
		this.manaRegen = manaRegen;
	}
	
	public Float getManaMax()
	{
		return manaMax;
	}
	
	public void setManaMax(Float manaMax)
	{
		this.manaMax = manaMax;
	}
	
	public String getPerks()
	{
		return perks;
	}
	
	public void setPerks(String perks)
	{
		this.perks = perks;
	}
	
	public String getSpells()
	{
		return spells;
	}
	
	public void setSpells(String spells)
	{
		this.spells = spells;
	}
	
	public String getFriends()
	{
		return friends;
	}
	
	public void setFriends(String friends)
	{
		this.friends = friends;
	}
	
	public Integer getBlockBreak()
	{
		return blockBreak;
	}
	
	public void setBlockBreak(Integer blockBreak)
	{
		this.blockBreak = blockBreak;
	}
	
	public Integer getBlockPlace()
	{
		return blockPlace;
	}
	
	public void setBlockPlace(Integer blockPlace)
	{
		this.blockPlace = blockPlace;
	}
	
	public Integer getCombatKills()
	{
		return combatKills;
	}
	
	public void setCombatKills(Integer combatKills)
	{
		this.combatKills = combatKills;
	}
	
	public Integer getCombatPlayerKills()
	{
		return combatPlayerKills;
	}
	
	public void setCombatPlayerKills(Integer combatPlayerKills)
	{
		this.combatPlayerKills = combatPlayerKills;
	}
	
	public Integer getCombatDeaths()
	{
		return combatDeaths;
	}
	
	public void setCombatDeaths(Integer combatDeaths)
	{
		this.combatDeaths = combatDeaths;
	}
	
	public Integer getChatWords()
	{
		return chatWords;
	}
	
	public void setChatWords(Integer chatWords)
	{
		this.chatWords = chatWords;
	}
	
	public Integer getTimeOnline()
	{
		return timeOnline;
	}
	
	public void setTimeOnline(Integer timeOnline)
	{
		this.timeOnline = timeOnline;
	}
}