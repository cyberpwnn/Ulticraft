package com.ulticraft.uapi;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import com.ulticraft.Ulticraft;

public class DataManager
{
	private File file;
	private Ulticraft pl;
	
	public DataManager(Ulticraft pl, String localPath)
	{
		file = new File(pl.getDataFolder(), localPath);
		this.pl = pl;
	}
	
	public DataManager(Ulticraft pl, File file)
	{
		this.file = file;
		this.pl = pl;
	}
	
	public void writeYAML(Object o)
	{
		writeYAML(o, false);
	}
	
	public void writeYAML(Object o, boolean v)
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
		
		if(v)
		{
			r.showLog(pl.getDispatcher());
		}
	}
	
	public Object readYAML(Class<?> clazz)
	{
		return readYAML(clazz, false);
	}
	
	public Object readYAML(Class<?> clazz, boolean v)
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
			
			if(v)
			{
				r.showLog(pl.getDispatcher());
			}
			
			return k;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(v)
		{
			r.showLog(pl.getDispatcher());
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
