package com.ulticraft;

import com.ulticraft.composite.Perk;
import com.ulticraft.uapi.USound;
import net.md_5.bungee.api.ChatColor;

public class Info
{
	public static final String NAME = "Ulticraft";
	public static final String VERSION = "3.1.21";
	public static final String[] AUTHORS = new String[]{"Daniel Mills (cyberpwn)", "Alex Walters (Sir_Ulticraft)"};
	
	public static final String STAT_BLOCKBREAK = "Blocks Broken";
	public static final String STAT_BLOCKPLACE = "Blocks Placed";
	public static final String STAT_COMBATKILLS = "Combat Kills";
	public static final String STAT_COMBATPLAYERKILLS = "Combat Player Kills";
	public static final String STAT_COMBATDEATHS = "Combat Deaths";
	public static final String STAT_CHATWORDS = "Chatted Words";
	public static final String STAT_MAGICFROSTS = "Magic Frosts";
	public static final String STAT_MAGICSHOCKS = "Magic Shocks";
	public static final String STAT_MAGICFLAMES = "Magic Flames";
	public static final String STAT_MAGICRUSHES = "Magic Rushes";
	
	public static final char DINGBAT_PENTAGON = '\u2B1F';
	public static final char DINGBAT_HEXAGON = '\u2B22';
	
	public static final String MSG_GEMS_SPENT = ChatColor.DARK_GRAY + "Spent " + ChatColor.LIGHT_PURPLE + "%s Gems";
	public static final String MSG_GEMS_EARNED = ChatColor.DARK_GRAY + "Earned " + ChatColor.LIGHT_PURPLE + "%s Gems";
	public static final String MSG_PERK_UNLOCK = ChatColor.DARK_GRAY + "Unlocked " + ChatColor.LIGHT_PURPLE + "%s";
	
	public static final String BROAD_PERK_UNLOCK = ChatColor.LIGHT_PURPLE + "%s " + ChatColor.DARK_GRAY + "Unlocked " + ChatColor.LIGHT_PURPLE + "%s";
	
	public static final USound SOUND_PERK_UNLOCK = new USound("ulticraft.unlock.perk");
	public static final USound SOUND_PERK_UNLOCK_DISTANT = new USound("ulticraft.unlock.perk.distant");
	
	public static final int MANA_BAR_SPLIT = 20;
	
	public static final Perk PERK_FLY = new Perk("Fly", new String[]{"Fly anywhere with /fly!"}, new String[]{"essentials.fly", "essentials.fly.safelogin"}, 400);

	public static final Perk[] PERKS = new Perk[]{PERK_FLY};
}
