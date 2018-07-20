package com.puck.menu.items.removing;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.piccolo2d.extras.pswing.PSwingCanvas;

import com.puck.arrows.ArrowNodesHolder;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.menu.Menu;
import com.puck.menu.items.outgoing.CreateISAEdgesOf;
import com.puck.menu.items.outgoing.CreateUsesEdgesOf;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;
import com.puck.utilities.piccolo2d.XmlToStructure;

public class RemoveEdgesOf extends JMenuItem{
	private NewDisplayDG frame;
	private PiccoloCustomNode pnode;
	private RemoveISAEdges removeISAEdges;
	private RemoveUsesEdges removeUsesEdges;
	

	public RemoveEdgesOf(PiccoloCustomNode pnode, JFrame frame) {
		super("Hide edges",new ImageIcon("images/hide.png"));
		this.pnode = pnode;
		this.frame = (NewDisplayDG)frame;
		removeISAEdges = new RemoveISAEdges(this.pnode, this.frame);
		removeUsesEdges = new RemoveUsesEdges(pnode, this.frame);

		addActionListener();
	}
	public void drawOutgoingdges(PiccoloCustomNode pnode , PSwingCanvas canvas) {
		removeISAEdges.RemoveEdges(pnode, canvas);
		removeUsesEdges.RemoveEdges(pnode, canvas);

		this.frame.getMenu().hideMenu();
	}
	
	public void addActionListener() {
		this.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				drawOutgoingdges(pnode, frame.getCanvas());
			}
		});
	}
}
