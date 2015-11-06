package com.ulticraft.uapi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import com.ulticraft.Ulticraft;

public class DataManager
{
	private File file;
	
	public DataManager(Ulticraft pl, String localPath)
	{
		file = new File(pl.getDataFolder(), localPath);
	}
	
	public DataManager(Ulticraft pl, File file)
	{
		this.file = file;
	}
	
	public void writeBytes(Object o)
	{
		verifyFile(file);
		Reflector r = new Reflector();
		MetaData md = r.getMetaData(o.getClass());
		DataOutputStream dos = null;
		
		try
		{
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			
			for(MetaReference i : md.getReferences())
			{
				r.writeRespective(dos, i, r.get(o, i));
			}
			
			dos.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			
			try
			{
				dos.close();
			}
			
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
	public Object readBytes(Class<?> clazz)
	{
		Reflector r = new Reflector();
		MetaData md = r.getMetaData(clazz);
		
		if(!file.exists())
		{
			verifyFile(file);
			writeBytes(r.instantiate(clazz));
		}
		
		try
		{
			Object k = r.instantiate(clazz);
			DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			
			for(MetaReference i : md.getReferences())
			{
				Object o = r.readRespective(dis, i);
				if(o != null)
				{
					r.set(k, i, r.valueOf(i, o));
				}
			}
			
			return k;
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void writeYAML(Object o)
	{
		verifyFile(file);
		FileConfiguration fc = new YamlConfiguration();
		Reflector r = new Reflector();
		MetaData md = r.getMetaData(o.getClass());
		
		for(MetaReference i : md.getReferences())
		{
			fc.set(i.getTarget().getPathYAML(), r.get(o, i));
		}
		
		try
		{
			fc.save(file);
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Object readYAML(Class<?> clazz)
	{
		FileConfiguration fc = new YamlConfiguration();
		Reflector r = new Reflector();
		MetaData md = r.getMetaData(clazz);
		
		if(!file.exists())
		{
			verifyFile(file);
			writeYAML(r.instantiate(clazz));
		}
		
		try
		{
			fc.load(file);
			
			Object k = r.instantiate(clazz);
			
			for(MetaReference i : md.getReferences())
			{
				Object o = fc.get(i.getTarget().getPathYAML());
				if(o != null)
				{
					r.set(k, i, r.valueOf(i, o));
				}
			}
			
			return k;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void verify(File dir)
	{
		if(!dir.getParentFile().exists())
		{
			verify(dir.getParentFile());
		}
		
		dir.mkdir();
	}
	
	private void verifyFile(File file)
	{
		if(!file.getParentFile().exists())
		{
			verify(file.getParentFile());
		}
		
		try
		{
			file.createNewFile();
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
