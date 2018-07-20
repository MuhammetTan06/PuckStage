package com.puck.menu.items;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
import com.puck.menu.items.removing.RemovesHierarchyEdgesOf;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;

public class HideNode extends JMenuItem {
	
		private NewDisplayDG frame;
		private PiccoloCustomNode pnode;
		
		public HideNode(PiccoloCustomNode pnode, JFrame frame) {
			super("Hide node ",new ImageIcon("images/hide.png"));
			this.pnode = pnode;
			this.frame = (NewDisplayDG)frame;
			addActionListener();
		}
		public void hideNode(PiccoloCustomNode pnode, PSwingCanvas canvas) {
			ArrayList<PiccoloCustomNode> fromAscendency = pnode.getAscendency();
//			RemoveEdgesOf removeEdges = new RemoveEdgesOf(pnode, canvas, allPNodes, menu, ANH, listNodes);
//			removeEdges.drawOutgoingdges(pnode,canvas);
			RemovesHierarchyEdgesOf removeEdges = new RemovesHierarchyEdgesOf(pnode, this.frame);
			removeEdges.drawOutgoingdges(pnode,canvas);
			PiccoloCustomNode parent = pnode.getParentNode();
			parent.getHiddenchildren().add(pnode);
			parent.removeChild(pnode);
			if (fromAscendency.size() > 0) {
				fromAscendency.get(fromAscendency.size()-1).setLayout();
			}
			
		}
	
		public void addActionListener() {
			this.addActionListener(new AbstractAction() {

				public void actionPerformed(ActionEvent arg0) {
					System.out.println("je click");
					hideNode(pnode, frame.getCanvas());
				}
			});
		}
}
