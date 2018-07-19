package com.puck.reader.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.piccolo2d.PNode;
import org.piccolo2d.extras.pswing.PSwingCanvas;

import com.puck.arrows.ArrowNodesHolder;
import com.puck.display.piccolo2d.NewDisplayDG;
import com.puck.display.piccolo2d.NodeToPnodeParser;
import com.puck.menu.Menu;
import com.puck.menu.items.ingoing.CreateEgdesHierarchyBy;
import com.puck.menu.items.outgoing.CreateEgdesHierarchyOf;
import com.puck.nodes.piccolo2d.Edge;
import com.puck.nodes.piccolo2d.Node;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;
import com.puck.utilities.piccolo2d.XmlToStructure;

/*
 * cette classe permet de comparer le xml intial avec le xml regenere a partir de l'affichage 
 * 
 * */
public class XmlToDisplay {
	private StringBuilder xmlString;
	private HashMap<String, Edge> addedEdges = new HashMap<String, Edge>();
	private HashMap<String, PiccoloCustomNode> addedPnodes = new HashMap<String, PiccoloCustomNode>();
	private BufferedWriter br;
	private CreateEgdesHierarchyBy createEdgesHierarchyBy;
	private CreateEgdesHierarchyOf createEdgesHierarchyOf;
	private NewDisplayDG frame;
	

	public XmlToDisplay(JFrame frame) {
		xmlString = new StringBuilder();
		xmlString.append("<?xml version=\"1.0\"?>\n");
		xmlString.append("<DG>\n");
		this.frame = (NewDisplayDG)frame;
		this.frame.setListNodes(new XmlToStructure("DependecyGraph.xml").parseNode());
		this.frame.setRoot(new NodeToPnodeParser(this.frame.getAllPNodes(), this.frame.getListNodes()).getPackageNodes());
		this.frame.setCanvas(new PSwingCanvas());
		this.frame.setMenu(new Menu());
		this.frame.setANH(new ArrowNodesHolder());
		this.frame.getRoot().setParent(new PNode());
		this.frame.getRoot().expandAll();
		
		createEdgesHierarchyBy = new CreateEgdesHierarchyBy(this.frame.getRoot(), this.frame);
		createEdgesHierarchyOf = new CreateEgdesHierarchyOf(this.frame.getRoot(), this.frame);
		addPnodes(this.frame.getRoot());
		createEdgesHierarchyBy.drawOutgoingdges(this.frame.getRoot(), this.frame.getCanvas());
		createEdgesHierarchyOf.drawOutgoingdges(this.frame.getRoot(), this.frame.getCanvas());
		
		for (Entry<String, PiccoloCustomNode> entry : addedPnodes.entrySet()) {
			String key = entry.getKey();
			PiccoloCustomNode p = entry.getValue();
			addEdgesToXml(p);
		}
		addUsesIsaToXml();
		System.err.println(addedEdges.size());
		xmlString.append("</DG>");
		try {
			writeTo();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void addPnodes(PiccoloCustomNode pnode) {
		for (PiccoloCustomNode child : pnode.getHierarchy()) {
			addPnodeToXml(child);
			addPnodes(child);
		}

	}

	public void addPnodeToXml(PiccoloCustomNode pnode) {
		if (!addedPnodes.containsValue(pnode)) {
			System.out.println(pnode.getName());
			System.out.println(pnode.isHidden());
			addedPnodes.put(pnode.getidNode(), pnode);
			xmlString.append(nodeToString(pnode));
		}
	}

	private String nodeToString(PiccoloCustomNode node) {
		String formatString = "\t<node type=\"%s\" id=\"%s\" name=\"%s\"/>\n";
		String type = node.getContent().getType().toLowerCase();
		String id = node.getidNode();
		String name = node.getName();
		return String.format(formatString, type, id, name);
	}

	private String edgeToString(Edge node) {
		String formatString = "\t<edge type=\"%s\" src=\"%s\" dest=\"%s\" id=\"%s\"/>\n";
		String type = node.getType();
		String id = node.getId();
		String from = node.getFrom();
		String to = node.getTo();
		return String.format(formatString,type,from,to,id);
	}

	public void writeTo() throws IOException {
		BufferedWriter br = new BufferedWriter(new FileWriter(new File("xmlFromDisplay.xml")));
		br.write(xmlString.toString());
		br.close();
	}
	public void addUsesIsaToXml() {
		for (Entry<String, Edge> entry : addedEdges.entrySet()) {
			Edge e = entry.getValue();
			xmlString.append(edgeToString(e));
		}
	}
	public void addEdgesToXml(PiccoloCustomNode pnode) {
		Node node = this.frame.getListNodes().get(pnode.getidNode());
		for (Entry<String, PiccoloCustomNode> entry : addedPnodes.entrySet()) {
			String key = entry.getKey();
			PiccoloCustomNode p = entry.getValue();
			for (PiccoloCustomNode child : p.getChildren()) {
				if (node != null) {
					Edge e = getCointainsEdge(node, this.frame.getListNodes().get(child.getidNode()));
					if (e != null && !addedEdges.containsKey(e)) {		
						xmlString.append(edgeToString(e));
					}
				}
			}
		}
	}

	public Edge getCointainsEdge(Node node, Node child) {
		HashMap<String, Edge> edges = node.getRelation();
		for (Entry<String, Edge> entry : edges.entrySet()) {
			Edge e = entry.getValue();
			if (e.getType().equals("contains") && e.getTo().equals(child.getId())) {
				return e;
			}
			if(!e.getType().equals("contains")){
				addedEdges.put(e.getId(), e);
			}
		}
		return null;
	}

	/*
		public static void main(String[] args) {
			new XmlToDisplay();
		}
	*/
}
