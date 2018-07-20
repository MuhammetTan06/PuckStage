package com.puck.menu.items;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.piccolo2d.extras.pswing.PSwingCanvas;

import com.puck.arrows.ArrowNodesHolder;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.menu.Menu;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;
import com.puck.refactoring.RefactoringCommands;
import com.puck.undoRedo.Changeable;
import com.puck.undoRedo.State;
import com.puck.undoRedo.StateChanger2;

public class RenameNode extends JMenuItem {
	private NewDisplayDG frame;
	private PiccoloCustomNode pnode;
	private Changeable state;
	
	private Collection<PiccoloCustomNode> children;
    // TODO(rename methode interface ) rename -> undo rename -> save -> execute / undo redo ne renomme pas les metrhode override
	public RenameNode(PiccoloCustomNode pnode, JFrame frame, Changeable state) {
		super("Rename ",new ImageIcon("images/add.png"));
		this.frame = (NewDisplayDG)frame;
		this.pnode = pnode;
		this.state = state;
	
		children = pnode.getChildren();
		addActionListener();
	}

	public void rename(String newName) {
		PiccoloCustomNode root_atpre = PiccoloCustomNode.newInstance(this.frame.getRoot());
		State previousState = new State(copy(), this.frame.getANH(), this.frame.getCanvas(),root_atpre,new StringBuilder(RefactoringCommands.getInstance().getXmlString()));
		Stack<State> editedState = StateChanger2.getInstance().getAddedPnodes();
		editedState.push(previousState);
		StateChanger2.getInstance().setAddedPnodes(editedState);
		
		pnode.getContent().rename(newName);
		pnode.setName(newName);
		
		this.frame.getAllPNodes().get(pnode.getidNode()).getContent().rename(newName);
		this.frame.getAllPNodes().get(pnode.getidNode()).setName(newName);
		
		
		RefactoringCommands.getInstance().nodeToString(pnode);
		//System.out.println(RefactoringCommands.getInstance().getXmlString().hashCode());

		State currentState = new State(copy(), this.frame.getANH(), this.frame.getCanvas(),PiccoloCustomNode.newInstance(this.frame.getRoot()),new StringBuilder(RefactoringCommands.getInstance().getXmlString()));
		editedState = StateChanger2.getInstance().getAddedPnodes();
		editedState.push(currentState);
		StateChanger2.getInstance().setAddedPnodes(editedState);
	
		this.frame.getRoot().setLayout();
	}
	public void renameWithoutStateSaving(String newName) {
		pnode.getContent().rename(newName);
		pnode.setName(newName);
	}

	public void addActionListener() {
		this.addActionListener(new AbstractAction() {

			public void actionPerformed(ActionEvent arg0) {
				String newName = JOptionPane.showInputDialog("Rename",pnode.getContent().getText().getText());
				if (newName == null || newName.equals(""))
					return;
				rename(newName);
			}
		});
	}
	
	public HashMap<String, PiccoloCustomNode>  copy() {
		HashMap<String, PiccoloCustomNode> allPNodes_atPre = new HashMap<>();
		for(Map.Entry<String, PiccoloCustomNode> node : this.frame.getAllPNodes().entrySet()) {
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
}
