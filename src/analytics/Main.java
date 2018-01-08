package analytics;

import data_structure.*;
import input_computation.*;

public class Main {
	
	public static void mainTimeAnalysis(String s) {
	
		
//		String input = IOImplementation.getInput();
//		MyList<MyList<String>> words = IOImplementation.listWords(input);
//		MyList<MyList<String>> words = IOImplementation.listWords("se nonna non era serena non si rasserenerava nonno");
//		MyList<MyList<String>> words = IOImplementation.listWords("anna ana annna annnna adn and");
		MyList<MyList<String>> words = IOImplementation.listWords(s);
		IOImplementation.sortWords(words);
		
//		MapCharToInt mcti = IOImplementation.getAlphabet(words);
		MapStringToInt msti = GraphBuilder.getVertices(words);
		
		MapCharToInt alphabet = IOImplementation.getAlphabet(words);
		MapStringToInt vertices = GraphBuilder.getVertices(words);
		Graph g = GraphBuilder.buildGraph(words, alphabet, vertices);
		
		int maxPath = g.DFS_MaxPath();
		
//		System.out.println("maxPath: "+ maxPath + "\n");
		
//		System.out.println(g.toString(msti));
		
		
	}

	
	public static void main(String[] args) {
		
//		String input = RandomGenerator.randomStringGen(1000);
		
		String input = IOImplementation.getInput();
		
		/* prima parte */
		MyList<MyList<String>> words = IOImplementation.listWords(input);
		IOImplementation.sortWords(words);
		IOImplementation.deleteDuplicatedWords(words);
		
		// seconda parte
		MapCharToInt alphabet = IOImplementation.getAlphabet(words);
		MapStringToInt vertices = GraphBuilder.getVertices(words);
		Graph g = GraphBuilder.buildGraph(words, alphabet, vertices);
		
		//terza
		int maxPath = g.DFS_MaxPath();
		
		System.out.println(maxPath + "\n");
		
		// quarta
		MapStringToInt msti = GraphBuilder.getVertices(words);
		System.out.println(g.toString(msti));
		
		
	}

}
