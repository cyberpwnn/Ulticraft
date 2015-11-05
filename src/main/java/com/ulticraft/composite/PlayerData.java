package com.ulticraft.composite;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.bukkit.entity.Player;
import com.ulticraft.uapi.CommonUtil;
import com.ulticraft.uapi.UList;

@Entity
public class PlayerData
{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	// Identity
	private UUID uuid;
	private String name;
	private UList<String> names;
	private String address;
	private UList<String> addresses;
	private String lastLogin;
	private String firstLogin;
	
	// Economy
	private Integer gems;
	private Integer gemsKnown;
	private Float mana;
	private Float manaRegen;
	private Float manaMax;
	private UList<String> perks;
	private UList<String> spells;
	private	UList<UUID> friends;
	
	// Statistics
	private Integer blockBreak;
	private Integer blockPlace;
	private Integer combatKills;
	private Integer combatPlayerKills;
	private Integer combatDeaths;
	private Integer chatWords;
	private Integer timeOnline;
	private Integer magicFlames;
	private Integer magicShocks;
	private Integer magicFrosts;
	private Integer magicRushes;
	
	public PlayerData(Player player)
	{
		//Identity
		this.uuid = player.getUniqueId();
		this.name = player.getName();
		this.names = new UList<String>();
		this.address = CommonUtil.date();
		this.addresses = new UList<String>();
		this.lastLogin = CommonUtil.address(player.getAddress());
		this.firstLogin = lastLogin;
		
		//Economy
		this.gems = 0;
		this.gemsKnown = 0;
		this.mana = 0f;
		this.manaRegen = 1f;
		this.manaMax = 100f;
		this.perks = new UList<String>();
		this.spells = new UList<String>();
		this.friends = new UList<UUID>();
		
		//Statistics
		this.blockBreak = 0;
		this.blockPlace = 0;
		this.combatKills = 0;
		this.combatPlayerKills = 0;
		this.combatDeaths = 0;
		this.chatWords = 0;
		this.timeOnline = 0;
		this.magicFlames = 0;
		this.magicShocks = 0;
		this.magicFrosts = 0;
		this.magicRushes = 0;
	}
	
	protected PlayerData()
	{
		
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
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

	public UList<String> getNames()
	{
		return names;
	}

	public void setNames(UList<String> names)
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

	public UList<String> getAddresses()
	{
		return addresses;
	}

	public void setAddresses(UList<String> addresses)
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

	public UList<String> getPerks()
	{
		return perks;
	}

	public void setPerks(UList<String> perks)
	{
		this.perks = perks;
	}

	public UList<String> getSpells()
	{
		return spells;
	}

	public void setSpells(UList<String> spells)
	{
		this.spells = spells;
	}

	public UList<UUID> getFriends()
	{
		return friends;
	}

	public void setFriends(UList<UUID> friends)
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

	public Integer getMagicFlames()
	{
		return magicFlames;
	}

	public void setMagicFlames(Integer magicFlames)
	{
		this.magicFlames = magicFlames;
	}

	public Integer getMagicShocks()
	{
		return magicShocks;
	}

	public void setMagicShocks(Integer magicShocks)
	{
		this.magicShocks = magicShocks;
	}

	public Integer getMagicFrosts()
	{
		return magicFrosts;
	}

	public void setMagicFrosts(Integer magicFrosts)
	{
		this.magicFrosts = magicFrosts;
	}

	public Integer getMagicRushes()
	{
		return magicRushes;
	}

	public void setMagicRushes(Integer magicRushes)
	{
		this.magicRushes = magicRushes;
	}
}