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
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;

public class RemovesHierarchyEdgesOf extends JMenuItem {
	private NewDisplayDG frame;
	private PiccoloCustomNode pnode;
	private RemoveEdgesOf removeEdgesOf;
	
	public RemovesHierarchyEdgesOf(PiccoloCustomNode pnode,  JFrame frame) {
		super("Hide hierarchy edges ",new ImageIcon("images/hide.png"));
	
		this.pnode = pnode;
		this.frame = (NewDisplayDG)frame;
		removeEdgesOf = new RemoveEdgesOf(pnode, this.frame);
		addActionListener();
	}
	public void drawOutgoingdges(PiccoloCustomNode pnode , PSwingCanvas canvas) {
		removeEdgesOf = new RemoveEdgesOf(pnode, this.frame);
		removeEdgesOf.drawOutgoingdges(pnode, canvas);
		for(PiccoloCustomNode child : pnode.getHierarchy()) {
			removeEdgesOf = new RemoveEdgesOf(child, this.frame);
			removeEdgesOf.drawOutgoingdges(child, canvas);
		}
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
