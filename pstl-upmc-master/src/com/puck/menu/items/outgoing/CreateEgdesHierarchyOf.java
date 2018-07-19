package com.puck.menu.items.outgoing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.piccolo2d.extras.pswing.PSwingCanvas;

import com.puck.arrows.ArrowNodesHolder;
import com.puck.arrows.Parrow;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.menu.Menu;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;

public class CreateEgdesHierarchyOf extends JMenuItem{

	private CreateEdgesOf createEdgesOf;
	private PiccoloCustomNode pnode;
	private NewDisplayDG frame;
	
	public CreateEgdesHierarchyOf(PiccoloCustomNode pnode, JFrame frame) {
		super("Show hierarchy outgoing edges ",new ImageIcon("images/right-arrow.png"));
		this.frame = (NewDisplayDG)frame;
		this.pnode = pnode;
		createEdgesOf = new CreateEdgesOf(pnode, this.frame);
		addActionListener();
	}
	public void drawOutgoingdges(PiccoloCustomNode pnode , PSwingCanvas canvas) {
		createEdgesOf = new CreateEdgesOf(pnode, this.frame);
		createEdgesOf.drawOutgoingdges(pnode, canvas);
		for(PiccoloCustomNode child : pnode.getHierarchy()) {
			createEdgesOf = new CreateEdgesOf(child, this.frame);
			createEdgesOf.drawOutgoingdges(child, canvas);
		}
		for (Parrow arrow : this.frame.getANH().getVisibleArrows()) {
			
			this.frame.getANH().refreshArrow(arrow);
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
