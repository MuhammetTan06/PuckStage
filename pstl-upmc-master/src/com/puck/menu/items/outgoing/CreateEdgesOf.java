package com.puck.menu.items.outgoing;

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
import com.puck.utilities.piccolo2d.XmlToStructure;

public class CreateEdgesOf extends JMenuItem{
	
	private PiccoloCustomNode pnode;
	private CreateUsesEdgesOf createUsesEdgesOf;
	private CreateISAEdgesOf createExtendsEdgesOf;
	private NewDisplayDG frame;

	public CreateEdgesOf(PiccoloCustomNode pnode, JFrame frame) {
		super("Show outgoing edges",new ImageIcon("images/right-arrow.png"));
		this.pnode = pnode;
		this.frame = (NewDisplayDG)frame;
		createUsesEdgesOf = new CreateUsesEdgesOf(pnode, this.frame);
		createExtendsEdgesOf = new CreateISAEdgesOf(pnode, this.frame);
		addActionListener();
	}
	public void drawOutgoingdges(PiccoloCustomNode pnode , PSwingCanvas canvas) {
		createUsesEdgesOf.DrawEdges(pnode, canvas);
		createExtendsEdgesOf.drawExtendsEdges(pnode, canvas);
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
