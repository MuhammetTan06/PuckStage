package com.puck.display.piccolo2d;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.color.ICC_ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.piccolo2d.PNode;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.pswing.PSwingCanvas;
import org.piccolo2d.nodes.PPath;
import org.piccolo2d.nodes.PText;

import com.puck.arrows.ArrowNodesHolder;
import com.puck.arrows.Parrow;
import com.puck.arrows.ParrowExtends;
import com.puck.arrows.ParrowUses;
import com.puck.menu.Menu;
import com.puck.menu.items.RenameNode;
import com.puck.menu.items.ingoing.CreateEdgesBy;
import com.puck.menu.items.ingoing.CreateEgdesHierarchyBy;
import com.puck.menu.items.outgoing.CreateEdgesOf;
import com.puck.menu.items.outgoing.CreateEgdesHierarchyOf;
import com.puck.menu.items.removing.RemovesHierarchyEdgesOf;
import com.puck.nodes.piccolo2d.Edge;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.NodeContent;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;
import com.puck.refactoring.ExecuteRefactoringPlan;
import com.puck.refactoring.RefactoringCommands;
import com.puck.undoRedo.State;
import com.puck.undoRedo.StateChanger;
import com.puck.undoRedo.StateChanger2;
import com.puck.utilities.piccolo2d.PCustomInputEventHandler;
import com.puck.utilities.piccolo2d.XmlToStructure;

public class NewDisplayDG extends JFrame {
	private HashMap<String, PiccoloCustomNode> allPNodes = new HashMap<>();
	private Map<String, Node> listNodes;
	private PiccoloCustomNode root;
	private Menu menu;
	private ArrowNodesHolder ANH;
	private MousePopupListener mp ;
	private PopupPrintListener mpp;
	private StateChanger state;
	private StateChanger2 state2;
	private ExecuteRefactoringPlan refactoringPlanExecutor;
	private PSwingCanvas canvas;
	private Collection<Edge> forbiddenEdges = new ArrayList<Edge>();
	
	private List<PPath> forbiddenVirtualArrowCounters = new ArrayList<PPath>();
	private Map<String, PText> forbiddenDependencyCounters = new HashMap<String, PText>();
	
	
	
	private static final long serialVersionUID = 1L;
	   	public HashMap<String, PiccoloCustomNode> getAllPNodes() {
		return allPNodes;
	}
	public Map<String, Node> getListNodes() {
		return listNodes;
	}
	public PiccoloCustomNode getRoot() {
		return root;
	}
	public Menu getMenu() {
		return menu;
	}
	public ArrowNodesHolder getANH() {
		return ANH;
	}
	public MousePopupListener getMp() {
		return mp;
	}
	public PopupPrintListener getMpp() {
		return mpp;
	}
	public StateChanger getStateChanger() {
		return state;
	}
	public StateChanger2 getStateChanger2() {
		return state2;
	}
	public ExecuteRefactoringPlan getRefactoringPlanExecutor() {
		return refactoringPlanExecutor;
	}
	public PSwingCanvas getCanvas() {
		
		return canvas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		public void setAllPNodes(HashMap<String, PiccoloCustomNode> allPNodes) {
		this.allPNodes = allPNodes;
	}
	public void setListNodes(Map<String, Node> listNodes) {
		this.listNodes = listNodes;
	}
	public void setRoot(PiccoloCustomNode root) {
		this.root = root;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public void setANH(ArrowNodesHolder aNH) {
		ANH = aNH;
	}
	public void setMp(MousePopupListener mp) {
		this.mp = mp;
	}
	public void setMpp(PopupPrintListener mpp) {
		this.mpp = mpp;
	}
	public void setStateChanger(StateChanger state) {
		this.state = state;
	}
	public Collection<Edge> getForbiddenEdges() {
		return forbiddenEdges;
	}
	
	public void setRefactoringPlanExecutor(ExecuteRefactoringPlan refactoringPlanExecutor) {
		this.refactoringPlanExecutor = refactoringPlanExecutor;
	}
	public void setCanvas(PSwingCanvas canvas) {
		this.canvas = canvas;
	}
	
	public NewDisplayDG() {
		
	}
	public void setStateChanger2(StateChanger2 s) {
		this.state2 = s;
	}
	   

	public NewDisplayDG(PSwingCanvas canvas,String args) throws InterruptedException{
		
		RefactoringCommands.getInstance().init();
		state2 = StateChanger2.getInstance();
		refactoringPlanExecutor = ExecuteRefactoringPlan.getInstance();
	    mp = new MousePopupListener();
	    mpp = new PopupPrintListener();
		menu = new Menu();
		listNodes = new XmlToStructure(args).parseNode();
		this.ANH =  new ArrowNodesHolder();
	
		this.canvas = canvas;
		NodeToPnodeParser nodesToPnodes = new NodeToPnodeParser(allPNodes, listNodes);
		
		root = nodesToPnodes.getPackageNodes();
		root.collapseAll();
		
		
		
		
		//root.expandAll();
		
		addEvent(root, root,canvas,menu,listNodes);
		canvas.getLayer().addChild(root);
		canvas.getLayer().addChild(ANH);
		canvas.addMouseListener(mp);
		canvas.setAutoscrolls(false);
		menu.addPopupMenuListener(mpp);
		state2.init(this, RefactoringCommands.getInstance().getXmlString());
		refactoringPlanExecutor.init(this);
		
		PMouseWheelZoomEventHandler wheelZoomEvent = new PMouseWheelZoomEventHandler();
		wheelZoomEvent.setScaleFactor(-0.1d);
		getCanvas().addInputEventListener(wheelZoomEvent);
	}
	
	private void readForbiddenEdges() {
		
		this.forbiddenEdges.clear();
		File f = new File(System.getProperty("user.dir"));
		File[] list = f.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				
					return name.endsWith(".fe");
				
			}
		}); 
		ArrayList<String> forbiddenIDs = new ArrayList<String>();
		BufferedReader bf;
		if(list.length!=0) {
			try {
				bf = new BufferedReader(new FileReader(list[0]));
				String line;
				try {
					while((line=bf.readLine())!=null) {
						forbiddenIDs.add(line);
					}
				bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
			for(Node n : this.listNodes.values()) {
				HashMap<String, Edge> relation = new HashMap<>(n.getRelation());
				for (Entry<String, Edge> edgeEntry : relation.entrySet()) {
						
					Edge e = edgeEntry.getValue();
						
					
					if(forbiddenIDs.contains(e.getId())) {
						forbiddenEdges.add(e);
					}
						
							
				}
			}
			
		}
		else {
			System.err.println("no .fe file found!!");
		}
		
		
	}
	
	public void clearAllEdgesDisplay() {
		
		for(Parrow p : this.ANH.getVisibleArrows()) {
			p.setVisible(false);
		}
		
		for(PiccoloCustomNode p : this.allPNodes.values()) {
			NodeContent newContent = new NodeContent(new PText(p.getName()), p.getContent().getType());
			newContent.getText().setTextPaint(Color.BLACK);
			newContent.getText().setFont(new Font(p.getContent().getText().getText(), Font.PLAIN, 12));
			
			newContent.setOffset(p.getContent().getOffset().getX(),
					p.getContent().getOffset().getY());
			newContent.addInputEventListener(new PCustomInputEventHandler(this, p));
			p.setContent(newContent);
		}
		
	}
	
	public void showAllDependencies() {
		
		readForbiddenEdges();
		
		new CreateEgdesHierarchyBy(root, this).drawOutgoingdges(root, canvas);
		new CreateEgdesHierarchyOf(root, this).drawOutgoingdges(root, canvas);
		
		
		this.ANH.getFocusedArrows().clear();
		for(Parrow p : this.ANH.getAllArrows())
			this.ANH.getFocusedArrows().add(p);
		
		//drawForbiddenDependencyCounters();
		//clearAllEdgesDisplay();
		
		
		
		//traitement pour use et extend
		
	
		
		refreshDisplay();
		
		
		
	
		
		
		this.root.setLayout();
		this.root.updateContentBoundingBoxes(false, this.canvas);
				
			
		
		
		
		for(PiccoloCustomNode p : this.allPNodes.values()) {
			NodeContent newContent = new NodeContent(new PText(p.getName()), p.getContent().getType());
			
			newContent.getText().setTextPaint(Color.BLACK);
			newContent.getText().setFont(new Font(p.getContent().getText().getText(), Font.PLAIN, 12));
			
			newContent.setOffset(p.getContent().getOffset().getX(),
					p.getContent().getOffset().getY());
			newContent.addInputEventListener(new PCustomInputEventHandler(this, p));
			for(Edge e : forbiddenEdges) {
				if(e.getType().equals("contains")) {
					if(p.getidNode().equals(e.getTo())) {
						
						newContent.getText().setTextPaint(Color.RED);
						newContent.getText().setFont(new Font(p.getContent().getText().getText(), Font.BOLD, 12));
						break;
					}
					
				}
			}
			p.setContent(newContent);
			root.setLayout();
			System.out.println(this.ANH.getAllArrows().size());
		}

		//this.ANH.updateAllPosition();
		
		this.root.updateContentBoundingBoxes(false, canvas);
		
	}
	
	public void refreshDisplay() {
		this.root.setLayout();
		ArrayList<PiccoloCustomNode> allNodes = new ArrayList<PiccoloCustomNode>();
		allNodes.add(this.root);
		allNodes.addAll(this.root.getHierarchy());
		for(PiccoloCustomNode c : allNodes)
			this.ANH.hide_show_arrows(c);
		this.ANH.refreshAllArrows();
		
		if(getForbiddenEdges().size()!=0)
			drawForbiddenVirtualArrowCounters();
	}
	
	
	public void drawForbiddenVirtualArrowCounters() {
		for(PPath p : this.forbiddenVirtualArrowCounters) {
			
			this.root.setLayout();
			this.canvas.getLayer().removeChild((p));
		}
		
		countForbiddenVirtualArrows();
		for(PPath p : this.forbiddenVirtualArrowCounters) {
			
			this.root.setLayout();
			this.canvas.getLayer().addChild((p));
		}
	}
	
	private void countForbiddenDep(PiccoloCustomNode p) {
		if(!p.isCollapsed()) {
			for(PiccoloCustomNode child : p.getAllChildren()) {
				countForbiddenDep(child);
			}
		} else {
			int numberOfForbDependencies = 0;
			Set<Edge> edges = new HashSet<Edge>(this.forbiddenEdges);
			for(PiccoloCustomNode child : p.getHierarchy()) {
				List<Edge> childEdges = new ArrayList<Edge>();
				for(Edge e : edges) {
					if(e.getFrom().equals(child.getidNode()) || e.getTo().equals(child.getidNode())) {

						numberOfForbDependencies++;
						childEdges.add(e);
						
					}
				}
				if(childEdges!=null)
					for(Edge ed : childEdges)
						edges.remove(ed);
			}
			if(numberOfForbDependencies>0) {
				PText indice = new PText();
				indice.setText(String.valueOf(numberOfForbDependencies));
				indice.setBounds(p.getContent().getX(), p.getContent().getY()-p.getContent().getHeight()/1.4, 30, 30);
				
				
				
			}
			
		}
	}
	
	private void countForbiddenVirtualArrows() {

		forbiddenVirtualArrowCounters.clear();
		ArrayList<ArrayList<Parrow>> tab = new ArrayList<ArrayList<Parrow>>();
		for(Parrow p : this.ANH.getVisibleArrows()) {
			
			if(p.getArrowType() == Parrow.VIRTUAL_TYPE) {
				boolean kk = false;
				PiccoloCustomNode from = (PiccoloCustomNode)p.getVirtualFrom();
				PiccoloCustomNode to = (PiccoloCustomNode)p.getVirtualto();
				
				if(tab.size()!=0) {
					for(ArrayList<Parrow> arrows : tab) {
						if(((PiccoloCustomNode)arrows.get(0).getVirtualFrom()).getidNode().equals(from.getidNode()) && ((PiccoloCustomNode)arrows.get(0).getVirtualto()).getidNode().equals(to.getidNode())) {
							arrows.add(p);
							kk = true;
							break;
						}
					}
					if(!kk) {
						ArrayList<Parrow> arr = new ArrayList<Parrow>();
						arr.add(p);
						tab.add(arr);
						
					}
					
				}
				else {
					ArrayList<Parrow> arr = new ArrayList<Parrow>();
					arr.add(p);
					tab.add(arr);
					
				}
				
				
			}
		}
		
		
		for(ArrayList<Parrow> arrows : tab) {
			PiccoloCustomNode from = (PiccoloCustomNode)arrows.get(0).getVirtualFrom();
			PiccoloCustomNode to = (PiccoloCustomNode)arrows.get(0).getVirtualto();
			Point2D coords = new Point2D.Double();
			coords.setLocation((to.getContent().getText().getGlobalBounds().getCenter2D().getX() + from.getContent().getText().getGlobalBounds().getCenter2D().getX())/2,
					(from.getContent().getText().getGlobalBounds().getCenter2D().getY() + to.getContent().getText().getGlobalBounds().getCenter2D().getY())/2 - 18);
			int forbiddenArrowsCount = 0;
			for(Parrow p : arrows) {
				if(p.getViolation()) {
					
					forbiddenArrowsCount++;
				}
			}
			
			
			if(forbiddenArrowsCount>0) {
				PPath t = PPath.createEllipse(0, 0, 10, 10);
				t.setBounds(coords.getX()-2, coords.getY(), 13, 13);
				t.setPaint(Color.RED);
			
				PText text = new PText(""+forbiddenArrowsCount);
				double offsetX = forbiddenArrowsCount>9 ? 0.4 : 2.3;
				text.setBounds(coords.getX()+offsetX, coords.getY()+1.5, 5, 5);
				text.setFont(new Font("", Font.BOLD, 7));
				
				
				text.setTextPaint(Color.WHITE);
				t.addChild(text);
				
				forbiddenVirtualArrowCounters.add(t);
			}
			
			
		}
		System.out.println();
		for(ArrayList<Parrow> arrows : tab) {
			System.out.println(arrows.size());
		}
		
		
	}
	
	
	public void showOnlyFocusedDependency(Edge ed) {
		
		clearAllEdgesDisplay();
		this.ANH.getFocusedArrows().clear();
		displayForbiddenDep(ed);
		this.root.collapseAll();
//		reorganizeHierarchyWhenFocusingEdge(this.allPNodes.get(ed.getFrom()), this.allPNodes.get(ed.getTo()));
		 PNode pFrom = this.allPNodes.get(ed.getFrom());
	      PNode pTo = this.allPNodes.get(ed.getTo());
	        
	      PiccoloCustomNode pcnFrom = (PiccoloCustomNode)pFrom;
	      PiccoloCustomNode pcnTo = (PiccoloCustomNode)pTo;
	        
	      for (PiccoloCustomNode pcn : pcnFrom.getAscendency()) {
	          if (pcn.isCollapsed() && pcn!=pcnFrom)
	              pcn.toggleChildren();
	      }
	        
	      for (PiccoloCustomNode pcn : pcnTo.getAscendency()) {
	          if (pcn.isCollapsed() && pcn!=pcnTo)
	              pcn.toggleChildren();
	      }
		this.root.setLayout();
		this.ANH.refreshAllArrows();
		this.root.updateContentBoundingBoxes(false, this.canvas);
		refreshDisplay();
			
	}
	
	
	public void showOnlyAllForbiddenDependencies () {
		
	    clearAllEdgesDisplay();
	    
		this.ANH.getFocusedArrows().clear();
		for(Edge e : this.forbiddenEdges) {
			displayForbiddenDep(e);
		}
		this.root.collapseAll();
		for (Edge e : this.forbiddenEdges) {
		      PNode pFrom = this.allPNodes.get(e.getFrom());
		      PNode pTo = this.allPNodes.get(e.getTo());
		        
		      PiccoloCustomNode pcnFrom = (PiccoloCustomNode)pFrom;
		      PiccoloCustomNode pcnTo = (PiccoloCustomNode)pTo;
		        
		      for (PiccoloCustomNode pcn : pcnFrom.getAscendency()) {
		          if (pcn.isCollapsed() && pcn!=pcnFrom)
		              pcn.toggleChildren();
		      }
		        
		      for (PiccoloCustomNode pcn : pcnTo.getAscendency()) {
		          if (pcn.isCollapsed() && pcn!=pcnTo)
		              pcn.toggleChildren();
		      }
		}
		
		refreshDisplay();

	}

	private void displayForbiddenDep(Edge ed) {
		
		PiccoloCustomNode fromNode = this.allPNodes.get(ed.getFrom());
		PiccoloCustomNode toNode = this.allPNodes.get(ed.getTo());
		
		
		
	
		if(ed.getType().equals("contains")) {
			
			NodeContent newContent = new NodeContent(new PText(toNode.getName()), toNode.getContent().getType());
			newContent.getText().setTextPaint(Color.RED);
			newContent.getText().setFont(new Font(toNode.getContent().getText().getText(), Font.BOLD, 12));
			newContent.setOffset(toNode.getContent().getOffset().getX(),
					toNode.getContent().getOffset().getY());
			newContent.addInputEventListener(new PCustomInputEventHandler(this, toNode));
			toNode.setContent(newContent);
			
		} else {
			
			
			for(Parrow p : this.ANH.getAllArrows()) {
				if(p.getFrom().equals(fromNode) && p.getTo().equals(toNode)) {
					p.setVisible(true);
					this.ANH.getFocusedArrows().add(p);
					return;
				}
			}
			if(ed.getType().equals("uses")) {
				Parrow newP = new ParrowUses(fromNode, toNode, 10, fromNode, toNode, ed.getViolation(), Parrow.REAL_TYPE);
				this.ANH.addArrow(newP);
				this.ANH.getFocusedArrows().add(newP);
			}
			else {
				Parrow newP = new ParrowExtends(fromNode, toNode, fromNode, toNode, ed.getViolation(), Parrow.REAL_TYPE);
				this.ANH.addArrow(newP);
				this.ANH.getFocusedArrows().add(newP);
			}
		}
	}

//	private void reorganizeHierarchyWhenFocusingEdge(PiccoloCustomNode from, PiccoloCustomNode to) {
//		
//		
//		PiccoloCustomNode p = (to.getDistanceFromHigherParent()>from.getDistanceFromHigherParent()) ? to : from;
//		
//		while(p.isHidden()) {
//			p.getLastVisibleParent().expandAll();
//		}
//		p.collapseAll();
//
//		PiccoloCustomNode par;
//
//		do {
//			par = p.getParentNode();
//			for(PiccoloCustomNode ch : par.getAllChildren()) {
//				if(!(p.getidNode().equals(ch.getidNode()))) {
//					ch.collapseAll();
//				}
//			}
//			p = p.getParentNode();
//		} while(!(par.getidNode().equals(p.getHigherParent().getidNode())));
//		
//	}

	
	public HashMap<String, PiccoloCustomNode> copy() {
		HashMap<String, PiccoloCustomNode> allPNodes_atPre = new HashMap<>();
		for(Map.Entry<String, PiccoloCustomNode> node : allPNodes.entrySet()) {
			PiccoloCustomNode copy = PiccoloCustomNode.newInstance(node.getValue());
			Collection<PiccoloCustomNode> hide_atPre = new ArrayList<>(node.getValue().getHiddenchildren());
			copy.setHiddenchildren(hide_atPre);
			Collection<PiccoloCustomNode> children_atPre = new ArrayList<>(node.getValue().getChildren());
			copy.setChilldren(children_atPre);
			copy.setParentNode(node.getValue().getParentNode());
			copy.setParent(node.getValue().getParent());
			allPNodes_atPre.put(copy.getidNode(), copy);
		}
		return allPNodes_atPre;
	}
		
	
	private void addEvent(PiccoloCustomNode node, PiccoloCustomNode tree,PSwingCanvas canvas,Menu menu,Map<String, Node> listNodes) {
		node.getContent().getText().addInputEventListener(new PCustomInputEventHandler(this, node));
		if (node.getAllChildren().size() != 0)
			for (PiccoloCustomNode PCN : node.getAllChildren()) {
				addEvent(PCN, tree,canvas,menu,listNodes);
			}
	}
	
	public void renameNodes(String [] ids) {
		System.out.println(allPNodes.hashCode());
		String newName = allPNodes.get(ids[0].trim()).getName();	
		for (int i = 1; i < ids.length; i++) {
			for(PiccoloCustomNode node : root.getHierarchy()) {
				if (node.getidNode().equals(ids[i].trim())) {
					RenameNode rename = new RenameNode(node, this, state);
					rename.renameWithoutStateSaving(newName);
				}
			}
		}
	}
	
	// Listener for Pnode click 
	class MousePopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	    	//System.out.println("j'ai detecte click press");
	      checkPopup(e);
	    }

	    public void mouseClicked(MouseEvent e) {
	    	//System.out.println("j'ai detecte click click");
	      checkPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	      checkPopup(e);
	    }

	    private void checkPopup(MouseEvent e) {
	      if (e.isPopupTrigger()) {
		    //	System.out.println("popuptrigger");

	    	 if (menu.isHidden()) {
	    	//		System.out.println("menu hidden");
	    		 menu.show();
			 }else {
				 menu.hide();
			 }
	    		 
		
	      }
	    }
	  }
	public class PopupPrintListener implements PopupMenuListener {
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		//	System.out.println("Popup menu will be visible!");
		}

		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		//	System.out.println("Popup menu will be invisible!");
		}

		public void popupMenuCanceled(PopupMenuEvent e) {
		//	System.out.println("Popup menu is hidden!");
		}
	}
	

}