package com.ulticraft.composite;

import org.bukkit.entity.Player;
import com.ulticraft.uapi.NotificationPriority;
import com.ulticraft.uapi.Title;
import com.ulticraft.uapi.UList;
import com.ulticraft.uapi.USound;

public class Notification
{
	private String title;
	private String subTitle;
	private String subSubTitle;
	private USound sound;
	private NotificationPriority priority;
	private UList<String> messages;
	
	public Notification()
	{
		priority = NotificationPriority.MEDIUM;
		messages = new UList<String>();
	}
	
	public void send(Player p)
	{
		new Title(title, subTitle, subSubTitle).send(p);
		
		for(String i : messages)
		{
			p.sendMessage(i);
		}
		
		sound.play(p);
	}
	
	public Notification addMessage(String message)
	{
		messages.add(message);
		return this;
	}
	
	public UList<String> getMessages()
	{
		return messages;
	}
	
	public Notification setMessages(UList<String> messages)
	{
		this.messages = messages;
		return this;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Notification setTitle(String title)
	{
		this.title = title;
		return this;
	}
	
	public String getSubTitle()
	{
		return subTitle;
	}
	
	public Notification setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
		return this;
	}
	
	public String getSubSubTitle()
	{
		return subSubTitle;
	}
	
	public Notification setSubSubTitle(String subSubTitle)
	{
		this.subSubTitle = subSubTitle;
		return this;
	}
	
	public USound getSound()
	{
		return sound;
	}
	
	public Notification setSound(USound sound)
	{
		this.sound = sound;
		return this;
	}
	
	public NotificationPriority getPriority()
	{
		return priority;
	}
	
	public Notification setPriority(NotificationPriority priority)
	{
		this.priority = priority;
		return this;
	}
}
