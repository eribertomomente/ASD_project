import data_structure.*;
import graphs.Graph;
import input_computation.*;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("eccoci");
		
		String input = IOImplementation.getInput();
		MyList<MyList<String>> words = IOImplementation.listWords(input);
		IOImplementation.sortWords(words);
		
		MapCharToInt mcti = IOImplementation.getAlphabet(words);
		MapStringToInt msti = GraphBuilder.getVertices(words);
		
		Graph g = GraphBuilder.buildGraph(words);
		
		System.out.println(g.toString(msti));
		
	}

}
