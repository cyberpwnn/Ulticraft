package com.ulticraft.graphics;

import org.bukkit.util.Vector;
import com.ulticraft.uapi.UList;

public class VectorFilterSet implements VectorFilter
{
	private UList<VectorFilter> filters;
	
	public VectorFilterSet()
	{
		this.filters = new UList<VectorFilter>();
	}
	
	public UList<VectorFilter> getFilters()
	{
		return filters;
	}
	
	public void setFilters(UList<VectorFilter> filters)
	{
		this.filters = filters;
	}
	
	public VectorFilterSet add(VectorFilter filter)
	{
		filters.add(filter);
		return this;
	}
	
	@Override
	public Vector apply(Vector v)
	{
		for(VectorFilter i : filters)
		{
			v = i.apply(v);
		}
		
		return v;
	}
}
