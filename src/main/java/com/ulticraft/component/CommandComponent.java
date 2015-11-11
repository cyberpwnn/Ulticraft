package com.ulticraft.component;

import com.ulticraft.Info;
import com.ulticraft.Ulticraft;
import com.ulticraft.command.Commander;
import com.ulticraft.command.Node;
import com.ulticraft.command.NodeFlag;
import com.ulticraft.command.NodeParam;
import com.ulticraft.command.NodeType;
import com.ulticraft.command.RunnableNode;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(GemComponent.class)
public class CommandComponent extends Component
{
	private Commander gemCommander;
	
	private Node gemNode;
	private Node gemGetNode;
	
	public CommandComponent(Ulticraft pl)
	{
		super(pl);
		
		gemNode = new Node().addPath("gem").addFlag(NodeFlag.PLAYER_ONLY);
		gemNode.setRunnableNode(new RunnableNode()
		{
			@Override
			public void run()
			{
				getPlayer().sendMessage(String.format(Info.MSG_GEMS_HAVE, String.valueOf(pl.gpd(getPlayer()).getGems())));
			}
		});
		
		gemGetNode = new Node().addPath("gem").addPath("get").addParam(new NodeParam("player", NodeType.PLAYER));
		gemGetNode.setRunnableNode(new RunnableNode()
		{
			@Override
			public void run()
			{
				getSender().sendMessage(String.format(Info.MSG_PLAYER_GEM_HAS, getDataPlayer().getName(), String.valueOf(pl.gpd(getDataPlayer()).getGems())));
			}
		});
		
		gemCommander = new Commander();
		
		gemCommander.addNode(gemNode);
		gemCommander.addNode(gemGetNode);
	}
	
	public void enable()
	{
		pl.getCommand("gem").setExecutor(gemCommander);
		
		super.enable();
	}
	
	public void disable()
	{
		super.disable();
	}
}
