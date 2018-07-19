package com.puck.undoRedo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.JFrame;

import org.piccolo2d.extras.pswing.PSwingCanvas;

import com.puck.arrows.ArrowNodesHolder;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.menu.Menu;
import com.puck.nodes.piccolo2d.Edge;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;
import com.puck.refactoring.RefactoringCommands;
import com.puck.utilities.piccolo2d.PCustomInputEventHandler;

public class StateChanger2 implements Changeable{
	
	private NewDisplayDG frame;
	private ArrowNodesHolder ANH;
	private Stack<State> editedState; 
	private int position;
	private State currentState;
	private  StringBuilder refactoringCommands;
	private Collection<Edge> forbiddenEdges;
	
	private StateChanger2() {
		super();
	};
	private static StateChanger2 INSTANCE = new StateChanger2();
	 public static StateChanger2 getInstance()
	    {   return INSTANCE;
	    }
	public void init(JFrame f, StringBuilder refactoringCommands) {
		this.frame = (NewDisplayDG)f;
		this.editedState = new  Stack<State>();
		this.position = 0;
		this.refactoringCommands = refactoringCommands;
		//System.err.println("ref"+refactoringCommands.hashCode());
	}
	
	@Override
	public void undo() {
		if(position > 1 )position-- ;
		else position = 0 ;
		if (editedState.size() > 0 && position >= 0 && !currentState.equals(editedState.get(position))) {
			System.err.println("undo");
			State previousState = editedState.get(position);
			currentState = previousState;
			this.frame.getAllPNodes().clear();
			this.frame.getAllPNodes().putAll(previousState.getAllPNodes());
			
			this.ANH = previousState.getANH();
			RefactoringCommands.getInstance().setXmlString(previousState.getRefactoringCommands());
			this.frame.getRoot().removeAllChildren();
			this.frame.getRoot().getAllChildren().clear();
			this.frame.getRoot().getHiddenchildren().clear();
			Collection<PiccoloCustomNode> children = previousState.getRoot().getAllChildren();
			this.frame.getRoot().setChilldren(new ArrayList<>(children));
			//root.setHiddenchildren(previousState.getRoot().getHiddenchildren());	
			addEvent(this.frame.getRoot(), this.frame.getRoot(), this.frame.getCanvas(), this.frame.getMenu(), this.frame.getListNodes());
		
			this.frame.getRoot().setLayout();
		}

	}

	public PiccoloCustomNode getRoot() {
		return this.frame.getRoot();
	}

	@Override
	public void redo() {
		position++;
		if (position == editedState.size()) position--;
		
		if (editedState.size() > 0 && position < editedState.size() && !currentState.equals(editedState.get(position))) {
			System.out.println("redo");
			State nextState = editedState.get(position);
			currentState = nextState;
			this.frame.getAllPNodes().clear();
			this.frame.getAllPNodes().putAll(nextState.getAllPNodes());
			
			ANH = nextState.getANH();
			RefactoringCommands.getInstance().setXmlString(nextState.getRefactoringCommands());

			this.frame.getRoot().removeAllChildren();
			this.frame.getRoot().getAllChildren().clear();
			this.frame.getRoot().getHiddenchildren().clear();
			
			this.frame.getRoot().setChilldren(new ArrayList<>(nextState.getRoot().getAllChildren()));
			//root.setHiddenchildren(nextState.getRoot().getHiddenchildren());
			addEvent(this.frame.getRoot(), this.frame.getRoot(), this.frame.getCanvas(), this.frame.getMenu(), this.frame.getListNodes());

			this.frame.getRoot().setLayout();
		}

	}
	
	
	public Stack<State> getAddedPnodes() {
		return editedState;
	}



	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setAddedPnodes(Stack<State> editedState) {
		this.editedState = editedState;
		position = this.editedState.size()-1;
		currentState = editedState.get(position);
	}

	public HashMap<String, PiccoloCustomNode> getAllPNodes() {
		return this.frame.getAllPNodes();
	}

	public void setAllPNodes(HashMap<String, PiccoloCustomNode> allPNodes) {
		this.frame.setAllPNodes(allPNodes);
	}

	public ArrowNodesHolder getANH() {
		return ANH;
	}

	public void setANH(ArrowNodesHolder aNH) {
		ANH = aNH;
	}

	public PSwingCanvas getCanvas() {
		return this.frame.getCanvas();
	}

	public void setCanvas(PSwingCanvas canvas) {
		this.frame.setCanvas(canvas);
	}
	
	private void addEvent(PiccoloCustomNode node, PiccoloCustomNode tree,PSwingCanvas canvas,Menu menu,Map<String, Node> listNodes) {
		if (node.getidNode() != "r01") 
		node.getContent().getText().addInputEventListener(new PCustomInputEventHandler(this.frame, node));
		if (node.getAllChildren().size() != 0)
			for (PiccoloCustomNode PCN : node.getAllChildren()) {
				addEvent(PCN, tree,canvas,menu,listNodes);
			}
	}
	

}
