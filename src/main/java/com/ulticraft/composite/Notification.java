package com.ulticraft.composite;

import com.ulticraft.uapi.NotificationPriority;
import com.ulticraft.uapi.Title;
import com.ulticraft.uapi.USound;

public class Notification
{
	private Title title;
	private USound sound;
	private NotificationPriority priority;
	
	public Notification(Title title, USound sound, NotificationPriority priority)
	{
		this.title = title;
		this.sound = sound;
		this.priority = priority;
	}
	
	public Notification(Title title, NotificationPriority priority)
	{
		this.title = title;
		this.sound = null;
		this.priority = priority;
	}
	
	public Notification(Title title, USound sound)
	{
		this.title = title;
		this.sound = sound;
		this.priority = NotificationPriority.MEDIUM;
	}
	
	public Notification(Title title)
	{
		this.title = title;
		this.sound = null;
		this.priority = NotificationPriority.MEDIUM;
	}

	public Title getTitle()
	{
		return title;
	}

	public void setTitle(Title title)
	{
		this.title = title;
	}

	public USound getSound()
	{
		return sound;
	}

	public void setSound(USound sound)
	{
		this.sound = sound;
	}

	public NotificationPriority getPriority()
	{
		return priority;
	}

	public void setPriority(NotificationPriority priority)
	{
		this.priority = priority;
	}
}
