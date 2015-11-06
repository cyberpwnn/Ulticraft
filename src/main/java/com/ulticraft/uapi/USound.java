package com.ulticraft.uapi;

public class USound
{
	private String sound;
	private Float volume;
	private Float pitch;
	
	public USound(String sound, Float volume, Float pitch)
	{
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
	}
	
	public USound(String sound)
	{
		this.sound = sound;
		this.volume = 1f;
		this.pitch = 1f;
	}
	
	public String getSound()
	{
		return sound;
	}
	
	public void setSound(String sound)
	{
		this.sound = sound;
	}
	
	public Float getVolume()
	{
		return volume;
	}
	
	public void setVolume(Float volume)
	{
		this.volume = volume;
	}
	
	public Float getPitch()
	{
		return pitch;
	}
	
	public void setPitch(Float pitch)
	{
		this.pitch = pitch;
	}
}
