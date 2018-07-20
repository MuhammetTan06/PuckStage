package com.puck.utilities.piccolo2d;




import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import org.piccolo2d.event.PBasicInputEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.event.PInputEventFilter;
import org.piccolo2d.extras.pswing.PSwingCanvas;
import org.piccolo2d.nodes.PText;

import com.puck.arrows.ArrowNodesHolder;
import com.puck.arrows.Parrow;
import com.puck.arrows.ParrowExtends;
import com.puck.arrows.ParrowUses;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.menu.Menu;
import com.puck.menu.items.AddNode;
import com.puck.menu.items.CollapseAll;
import com.puck.menu.items.ExpandAll;
import com.puck.menu.items.FocusNode;
import com.puck.menu.items.HideNode;
import com.puck.menu.items.RenameNode;
import com.puck.menu.items.ingoing.CreateEdgesBy;
import com.puck.menu.items.ingoing.CreateEgdesHierarchyBy;
import com.puck.menu.items.outgoing.CreateEdgesOf;
import com.puck.menu.items.outgoing.CreateEgdesHierarchyOf;
import com.puck.menu.items.removing.RemoveEdgesOf;
import com.puck.menu.items.removing.RemovesHierarchyEdgesOf;
import com.puck.nodes.piccolo2d.Edge;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;
import com.puck.undoRedo.Changeable;
import com.puck.undoRedo.StateChanger;
import com.puck.utilities.NodeType;

public class PCustomInputEventHandler extends PBasicInputEventHandler {
	private PiccoloCustomNode pnode;
	private JMenuItem createEdgesOf;
	private JMenuItem createEdgesBy;
	private JMenuItem removeEdgesOf;
	private JMenuItem createEgdesHierarchyBy;
	private JMenuItem createEgdesHierarchyOf;
	private JMenuItem removesHierarchyEdgesOf;
	private HideNode hideNode ;
	private FocusNode focusNode;
	private ExpandAll expandAll;
	private CollapseAll collapseAll;
	private JMenuItem addClass;
	private JMenuItem addPackage;
	private JMenuItem renameNode;
	private Changeable state;
	private NewDisplayDG frame;
	private int HashSet;
	private int Edge;
	
	
	public PCustomInputEventHandler(JFrame frame, PiccoloCustomNode pnode) {
		setEventFilter(new PInputEventFilter(InputEvent.BUTTON1_MASK & InputEvent.BUTTON2_MASK));
		this.frame = (NewDisplayDG)frame;
		this.pnode = pnode;
		this.createEdgesOf = new CreateEdgesOf(pnode, this.frame);
		this.removeEdgesOf = new RemoveEdgesOf(pnode, this.frame);
		this.createEdgesBy = new CreateEdgesBy(pnode, this.frame);
		this.createEgdesHierarchyBy = new CreateEgdesHierarchyBy(pnode, this.frame);
		this.createEgdesHierarchyOf = new CreateEgdesHierarchyOf(pnode, this.frame);
		
		this.removesHierarchyEdgesOf = new RemovesHierarchyEdgesOf(pnode, this.frame);
		this.hideNode = new HideNode(pnode, this.frame);
		this.focusNode = new FocusNode(pnode, this.frame);
		this.expandAll = new ExpandAll(pnode, this.frame);
		this.collapseAll = new CollapseAll(pnode, this.frame);
		this.state = StateChanger.getInstance();
		this.addClass = new AddNode(this.frame, pnode, NodeType.CLASS, state);
		this.addPackage =  new AddNode(this.frame, pnode, NodeType.PACKAGE, state);
		this.renameNode = new RenameNode(pnode, this.frame, state);
		
	}

	public PCustomInputEventHandler(PiccoloCustomNode pnode) {
		setEventFilter(new PInputEventFilter(InputEvent.BUTTON1_MASK & InputEvent.BUTTON2_MASK));
		this.pnode = pnode;
	}

	@Override
	public void mousePressed(PInputEvent aEvent) {
		
		try {
			if (aEvent.isLeftMouseButton()) {
				pnode.toggleChildren();
				this.frame.getANH().refreshAllArrows();
				this.frame.refreshDisplay();
				this.frame.getRoot().setLayout();
				this.frame.getRoot().updateContentBoundingBoxes(false, this.frame.getCanvas());
			}
			if (aEvent.isRightMouseButton()) {
					generateMenu(this.frame.getMenu(),aEvent);		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void generateMenu(Menu menu,PInputEvent aEvent) {
		menu.removeAll();
		menu.add(createEdgesOf);
		menu.add(createEgdesHierarchyOf);
		menu.addSeparator();
		menu.add(createEdgesBy);
		menu.add(createEgdesHierarchyBy);
		menu.addSeparator();
		menu.add(removeEdgesOf);
		menu.add(removesHierarchyEdgesOf);
		menu.addSeparator();
		menu.add(hideNode);
		menu.add(focusNode);
		menu.addSeparator();
		menu.add(expandAll);
		menu.add(collapseAll);
		menu.addSeparator();
		if(pnode.getContent().getType().equals("package")) {
		menu.add(addClass);
		menu.add(addPackage);
		menu.addSeparator();
		}
		menu.add(renameNode);
	
		menu.setPoint(aEvent.getPosition());
		menu.setCanvas(this.frame.getCanvas());
	}
//	public boolean menuContainsPoint(Menu menu, Point point) {
//		PBounds bounds = menu.getP().getGlobalFullBounds();
//		Point2D poi = new Point2D.Double(point.getX()+5, point.getY()+5);
//		int x = (int) bounds.x;
//		int y = (int) bounds.y;
//		Rectangle2D rect =new Rectangle2D.Double();
//		rect.setFrame(x, y, bounds.getWidth(), bounds.getWidth());
//		System.out.println("menu "+rect);
//		System.out.println("point event "+poi);
//		System.out.println(rect.contains(poi));
//		return rect.contains(poi);
//	}
	
	 
}