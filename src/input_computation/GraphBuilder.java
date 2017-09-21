package input_computation;

import data_structure.MapCharToInt;
import data_structure.MapStringToInt;
import data_structure.MyList;
import graphs.Graph;

public class GraphBuilder {
	
	/**
	 * This class is useful to build the final graph of the project
	 * @author eriberto
	 *
	 */
	

	
	public static boolean compareWords(int[] first, int[] second) {
		boolean flag = false;
		for ( int i=0; i< first.length; i++) {
			// caso in cui sono entrambi a zero, va bene (basta che non siano tutti)
			if ( first[i] != 0 || second[i] != 0 ) {
				if (first[i] <= second[i]) {
					return false;
				} else {
					// verifico che almeno un char sia maggiore. Altrimenti se ho tutti zeri mi deve restituire falso.
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * WARNING in words cannot be duplicated elements
	 * @param words
	 * @return
	 */
	private static MapStringToInt getVertices(MyList<MyList<String>> words) {
		
		// calculate number of elements in <words>
		int numberWords = 0;
		for (int i = 0; i < words.size(); i++) {
			numberWords += words.elementAt(i).size();
		}
		
		MapStringToInt vertices = new MapStringToInt(numberWords);
		
		for (MyList<String> list : words) {
			for (String word : list) {
				vertices.insertKey(word);
			}
		}
		return vertices;
	}
	
	
	public static Graph buildGraph(MyList<MyList<String>> words) {
		
		MapCharToInt alphabet = IOImplementation.getAlphabet(words);
		MapStringToInt vertices = getVertices(words);
		
		Graph myGraph = new Graph();
		
		// insert as many vertex as the number of words
		myGraph = insertWordsAsVertex(myGraph, words);
		
		// build edges
		myGraph = buildEdges(myGraph, words, alphabet, vertices);
		
		return myGraph;
	}
	
	private static Graph insertWordsAsVertex(Graph myGraph, MyList<MyList<String>> words) {
		
		// calculate number of elements in <words>
		int numberWords = 0;
		for (int i = 0; i < words.size(); i++) {
			numberWords += words.elementAt(i).size();
		}
		
		// add a vertex for each word
		for (int i = 0; i < numberWords; i++) {
			myGraph.addVertex();
		}
		
		return myGraph;
	}
	
	private static Graph buildEdges(Graph myGraph, MyList<MyList<String>> words, MapCharToInt alphabet, MapStringToInt map ) {
		
		for(int i = 0; i < words.size(); i++) {
			for (int j = i+1; j < words.size(); j++) {
				
				for (String s1 : words.elementAt(i)) {
					for (String s2 : words.elementAt(j)) {
						
						int[] struct1 = IOImplementation.buildWordStructure( alphabet, s1);
						int[] struct2 = IOImplementation.buildWordStructure( alphabet, s2);
						
						if ( compareWords(struct1, struct2) ) {
							
							int startingVertex = map.getIndex(s1);
							int targetVertex = map.getIndex(s2);
							
							myGraph.setEdge(startingVertex, targetVertex);
						}
						
					}}
				
		}}
		
		return myGraph;
	}
	

}
