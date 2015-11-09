package com.ulticraft.component;

import com.ulticraft.Ulticraft;
import com.ulticraft.command.Commander;
import com.ulticraft.command.Node;
import com.ulticraft.command.NodeFlag;
import com.ulticraft.command.NodeType;
import com.ulticraft.command.RunnableNode;
import com.ulticraft.uapi.Component;
import com.ulticraft.uapi.Depend;

@Depend(GemComponent.class)
public class CommandComponent extends Component
{
	private Commander gemCommander;
	
	public CommandComponent(Ulticraft pl)
	{
		super(pl);
		
		gemCommander = new Commander();
		
		Node gemGet = new Node(gemCommander, "gem", NodeType.NONE);
		gemGet.addFlag(NodeFlag.PLAYER_ONLY);
		gemGet.setRunnableNode(new RunnableNode()
		{
			@Override
			public void run()
			{
				getSender().sendMessage("You have " + pl.gpd(getPlayer()).getGems());
			}
		});
		
		gemCommander.getNodes().add(gemGet);
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
