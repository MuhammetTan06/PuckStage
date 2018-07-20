package com.puck.refactoring;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.piccolo2d.extras.pswing.PSwingCanvas;

import com.puck.arrows.ArrowNodesHolder;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.menu.Menu;
import com.puck.menu.items.RenameNode;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;

public class ExecuteRefactoringPlan {

	private String filePath = null;
	NewDisplayDG frame;

	private ExecuteRefactoringPlan() {
		super();
	};

	private static ExecuteRefactoringPlan INSTANCE = new ExecuteRefactoringPlan();

	public static ExecuteRefactoringPlan getInstance() {
		return INSTANCE;
	}

	public void init(JFrame frame) {
		this.frame = (NewDisplayDG)frame;
	}
	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void execute() {
		
		if (filePath != null) {
			PlanReader reader = new PlanReader(filePath);
			int nodeSize = reader.getNbNodesRename();
			for (int i = 1; i <= nodeSize; i++) {
				PiccoloCustomNode pnode = this.frame.getAllPNodes().get(reader.getNodeId(i));
				RenameNode renaming = new RenameNode(pnode, this.frame, null);
				renaming.rename(reader.getNodeNewName(i));
				//pnode.getContent().rename(reader.getNodeNewName(i));
				//pnode.setName(reader.getNodeNewName(i));
			}
			//root.setLayout();
		}
	}
}
