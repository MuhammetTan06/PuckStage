package com.puck.menu.items.ingoing;

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

public class CreateEdgesBy extends JMenuItem{


	private PiccoloCustomNode pnode;
	private NewDisplayDG frame;
	
	private CreateUsesEdgesBy createUsesEdgesBy;
	private CreateISAEdgesBy createExtendsEdgesBy;

	public CreateEdgesBy(PiccoloCustomNode pnode, JFrame frame) {
		super("Show ingoing edges ",new ImageIcon("images/left-arrow.png"));
		this.frame = (NewDisplayDG)frame;
		this.pnode = pnode;
		createUsesEdgesBy = new CreateUsesEdgesBy(pnode, this.frame);
		createExtendsEdgesBy = new CreateISAEdgesBy(pnode, this.frame);
		addActionListener();
	}
	public void drawOutgoingdges(PiccoloCustomNode pnode , PSwingCanvas canvas) {
		createUsesEdgesBy.DrawEdges(pnode, canvas);
		createExtendsEdgesBy.drawExtendsEdges(pnode, canvas);
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
