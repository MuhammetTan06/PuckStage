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
import com.puck.arrows.Parrow;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.menu.Menu;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;
import com.puck.utilities.piccolo2d.XmlToStructure;

public class CreateEgdesHierarchyBy extends JMenuItem {

	
	private PiccoloCustomNode pnode;
	private NewDisplayDG frame;

	private CreateEdgesBy createEdgesBy;

	public CreateEgdesHierarchyBy(PiccoloCustomNode pnode, JFrame frame) {
		super("Show hierarchy ingoing edges ",new ImageIcon("images/left-arrow.png"));

		this.pnode = pnode;
		this.frame = (NewDisplayDG)frame;
		
		createEdgesBy = new CreateEdgesBy(pnode, this.frame);
		addActionListener();
	}
	public void drawOutgoingdges(PiccoloCustomNode pnode , PSwingCanvas canvas) {
		createEdgesBy = new CreateEdgesBy(pnode, this.frame);
		createEdgesBy.drawOutgoingdges(pnode, canvas);
		for(PiccoloCustomNode child : pnode.getHierarchy()) {
			createEdgesBy = new CreateEdgesBy(child, this.frame);
			createEdgesBy.drawOutgoingdges(child, canvas);
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

