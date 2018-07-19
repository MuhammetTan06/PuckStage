package com.puck.menu.items.outgoing;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.piccolo2d.PNode;
import org.piccolo2d.extras.pswing.PSwingCanvas;

import com.puck.arrows.ArrowNodesHolder;
import com.puck.arrows.Parrow;
import com.puck.arrows.ParrowExtends;
import com.puck.arrows.ParrowUses;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.display.piccolo2d.NodeToPnodeParser;
import com.puck.menu.Menu;
import com.puck.nodes.piccolo2d.Edge;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;
import com.puck.utilities.piccolo2d.XmlToStructure;

public class CreateUsesEdgesOf extends JMenuItem {

	private PiccoloCustomNode pnode;
	private NewDisplayDG frame;

	public CreateUsesEdgesOf(PiccoloCustomNode pnode, JFrame frame) {
		super();
		this.setText("show uses outgoing");
		this.pnode = pnode;
		this.frame = (NewDisplayDG)frame;
		addActionListener();
		
	}

	public void DrawEdges(PiccoloCustomNode target, PSwingCanvas canvas) {
		Node node = this.frame.getListNodes().get(target.getidNode());
		
		if ( node!= null && node.getRelation() !=  null) {
			HashMap<String, Edge> relation = node.getRelation();
			for (Entry<String, Edge> edgeEntry : relation.entrySet()) {
				Edge e = edgeEntry.getValue();
				for(Edge ed : this.frame.getForbiddenEdges()) {
					if(ed.getId().equals(e.getId())) {
						e.setViolation(true);
						break;
					}
					else 
						e.setViolation(false);
				}
				if (e.getType().equals("uses")) {
					PNode from = target;
					PNode to = (this.frame.getAllPNodes().get(e.getTo()));
					
					if (to.getParent() instanceof PiccoloCustomNode
							&& !((PiccoloCustomNode) to.getParent()).isHidden()) {
						for(Parrow p : this.frame.getANH().getAllArrows()) {
							if(!(p.getFrom().equals(this.frame.getListNodes().get(e.getFrom())) && p.getTo().equals(this.frame.getListNodes().get(e.getTo())))) {
								ParrowUses arrow = new ParrowUses(from, to, 10, from, to, e.getViolation(), Parrow.REAL_TYPE);
								this.frame.getANH().addArrow(arrow);
							}
						}
						this.frame.getANH().refreshAllArrows();
					} else {
						for (PiccoloCustomNode pnode : ((PiccoloCustomNode) to).getAscendency()) {
							if (!pnode.isHidden()) {
								for(Parrow p : this.frame.getANH().getAllArrows()) {
									if(!(p.getFrom().equals(this.frame.getListNodes().get(e.getFrom())) && p.getTo().equals(this.frame.getListNodes().get(e.getTo())))) {
										ParrowUses arrow = new ParrowUses(from, to, 10, from, to, e.getViolation(), Parrow.REAL_TYPE);
										this.frame.getANH().addArrow(arrow);
									}
								}
								this.frame.getANH().refreshAllArrows();
								break;
								
							}
						}
					}
				}
			}
		}
		this.frame.getMenu().hideMenu();
	}

	public void addActionListener() {
		this.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				DrawEdges(pnode, frame.getCanvas());
			}
		});
	}
}
