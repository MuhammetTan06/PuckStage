package app;

//import static org.junit.Assert.assertFalse;

//import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.xml.transform.Source;

//import org.junit.Assert;
//import org.junit.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.*;
import org.xmlunit.diff.Comparison.Detail;

import graph.CodeGenerator;
import graph.Graph;

public class CodeGeneratorTest {
	private static final String sourceFilesPath = "C:\\Users\\Momoche\\eclipse-workspace\\test\\"; 
	private static final String sourceFilesPathCG = "C:\\Users\\Momoche\\eclipse-workspace\\test2\\"; 
	private static final String xmlFilePath = String.join("", sourceFilesPath, "originalDG.xml");
	private static final String xmlFilePathCG = String.join("", sourceFilesPathCG, "genDG.xml");
	

//	@Test
	public void test() throws IOException {
		Puck2Runner runner1 = new Puck2Runner(sourceFilesPath);
		runner1.run();
		Graph graph1 = runner1.getGraph();
		try {
			runner1.outputToFile(xmlFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CodeGenerator cg = new CodeGenerator(graph1.getProgram(), sourceFilesPath);
		cg.generateCode(sourceFilesPathCG);
		
		Puck2Runner runner2 = new Puck2Runner(sourceFilesPathCG);
		runner2.run();
		Graph graph2 = runner2.getGraph();
		try {
			runner2.outputToFile(xmlFilePathCG);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Source control = Input.fromFile(xmlFilePath).build();
		Source test = Input.fromFile(xmlFilePathCG).build();
	
		
		Diff d = DiffBuilder.compare(control)
				.withTest(test)
				.ignoreWhitespace()
				.checkForSimilar()
				.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndAllAttributes))
				.build();
		
//		assertFalse("XML similar " + d.toString(), d.hasDifferences());
		
	}
	

	

}
