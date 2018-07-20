package com.puck.menu.items;

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

public class CollapseAll extends JMenuItem {
	private NewDisplayDG frame;
	private PiccoloCustomNode pnode;
	
	public CollapseAll(PiccoloCustomNode pnode, JFrame frame) {
		super("Collapse All",new ImageIcon("images/hide.png"));
		this.frame = (NewDisplayDG)frame;
		this.pnode = pnode;
		addActionListener();
	}
	public void expandAll(PiccoloCustomNode pnode, PSwingCanvas canvas) {
		pnode.collapseAll();
		pnode.getHigherParent().setLayout();
		this.frame.getANH().refreshAllArrows();
		
	}
	public void addActionListener() {
		this.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				expandAll(pnode, frame.getCanvas());
			}
		});
	}
}
