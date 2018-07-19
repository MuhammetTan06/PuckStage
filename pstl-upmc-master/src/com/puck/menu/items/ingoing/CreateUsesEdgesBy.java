package com.puck.menu.items.ingoing;



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
import com.puck.arrows.ParrowUses;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.menu.Menu;
import com.puck.nodes.piccolo2d.Edge;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;

public class CreateUsesEdgesBy extends JMenuItem {
	
	private PiccoloCustomNode pnode;
	private NewDisplayDG frame;

	public CreateUsesEdgesBy(PiccoloCustomNode pnode, JFrame frame) {
		super();
		this.setText("show uses ingoing");
	
		this.pnode = pnode;
		this.frame = (NewDisplayDG)frame;
	    addActionListener();
	}

	public void DrawEdges(PiccoloCustomNode target, PSwingCanvas canvas) {
		
		Node node = this.frame.getListNodes().get(target.getidNode());

		HashMap<String, Edge> relation = getRelationInGoing();
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
			PNode to = target;
			PNode from = (this.frame.getAllPNodes().get(e.getFrom()));
			if (from.getParent() instanceof PiccoloCustomNode && !((PiccoloCustomNode) from.getParent()).isHidden()) {
				this.frame.getANH().addArrow(new ParrowUses(from, to, 10, from, to, e.getViolation(), Parrow.REAL_TYPE));
			} else {
				for (PiccoloCustomNode pnode : ((PiccoloCustomNode) from).getAscendency()) {
					if (!pnode.isHidden()) {
						this.frame.getANH().addArrow(new ParrowUses(from, to, 10, pnode, to, e.getViolation(), Parrow.REAL_TYPE));
						break;
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

	public HashMap<String, Edge> getRelationInGoing() {
		Node currentNode = this.frame.getListNodes().get(pnode.getidNode());
		HashMap<String, Edge> relationInGoing = new HashMap<>();
		if (currentNode != null) {
			for (Entry<String, Node> nodeEntry : this.frame.getListNodes().entrySet()) {
				Node n = nodeEntry.getValue();
				HashMap<String, Edge> relation = n.getRelation();
				for (Entry<String, Edge> edgeEntry : relation.entrySet()) {
					Edge e = edgeEntry.getValue();
					if (e.getType().equals("uses") && (e.getTo().equals(currentNode.getId()))) {
						relationInGoing.put(e.getId(), e);
					}
				}
			}

		}
		return relationInGoing;
	}
}
