import data_structure.*;
import graphs.Graph;
import input_computation.*;

public class Main {
	
	public static void main(String[] args) {
		
		String input = IOImplementation.getInput();
		MyList<MyList<String>> words = IOImplementation.listWords(input);
		
		Graph myGraph = GraphBuilder.buildGraph(words);
		
		System.out.println(myGraph.toString());
		
		
		
		
		
	}

}
