package graph.readers;

import org.extendj.ast.EnumDecl;
import org.extendj.ast.TypeDecl;

import graph.Graph;
import graph.Node;

public class EnumReader extends TypeDeclReader{
	private EnumDecl enumDeclaration;

	public EnumReader(EnumDecl enumD, Graph graph) {
		super(enumD, graph);
		this.enumDeclaration = enumD;
	}
 
	@Override
	public Graph read() {
		addNode(this.enumDeclaration.fullName(), Node.Type.Enum, enumDeclaration);
		
		return getGraph();
	}

	@Override
	String getFullName() {
		return this.enumDeclaration.fullName();
	}
	
	

}
