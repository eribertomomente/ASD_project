import data_structure.*;
import graphs.Graph;
import input_computation.*;

public class Main {
	
	public static void main(String[] args) {
		
//		String input = IOImplementation.getInput();
//		MyList<MyList<String>> words = IOImplementation.listWords(input);
//		MyList<MyList<String>> words = IOImplementation.listWords("se nonna non era serena non si rasserenerava nonno");
		MyList<MyList<String>> words = IOImplementation.listWords("anna ana annna annnna adn and");
		IOImplementation.sortWords(words);
		
		MapCharToInt mcti = IOImplementation.getAlphabet(words);
		MapStringToInt msti = GraphBuilder.getVertices(words);
		
		Graph g = GraphBuilder.buildGraph(words);
		
		int maxPath = g.DFS_MaxPath();
		
		System.out.println("maxPath: "+ maxPath + "\n");
		
		System.out.println(g.toString(msti));
		
		
	}

}
