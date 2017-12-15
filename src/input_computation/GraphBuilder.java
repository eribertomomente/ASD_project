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
	

	
	private static boolean areInRelation(int[] first, int[] second) {
		boolean flag = false;
		for ( int i=0; i< first.length; i++) {
			
			// caso in cui sono entrambi a zero, va bene (a meno che non siano tutti)
			if ( first[i] != 0 || second[i] != 0 ) {
				if (first[i] < second[i]) {
					return false;
				} else if (first[i] != second[i]){
					// verifico che almeno un char sia maggiore. Altrimenti se ho tutti zeri mi deve restituire falso.
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * WARNING in words cannot be duplicated elements TODO però c'è contains
	 * @param words
	 * @return
	 */
	public static MapStringToInt getVertices(MyList<MyList<String>> words) {
		
		// conto numero di elementi in <words>
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
			for (int j = 0; j <= i; j++) {
				
				for (String s1 : words.elementAt(i)) {
					for (String s2 : words.elementAt(j)) {
						
						int[] struct1 = IOImplementation.buildWordStructure( alphabet, s1);
						int[] struct2 = IOImplementation.buildWordStructure( alphabet, s2);
						
						if ( areInRelation(struct1, struct2) ) {
							
							int startingVertex = map.getIndex(s1);
							int targetVertex = map.getIndex(s2);
							
							myGraph.setEdge(startingVertex, targetVertex);
						}
						
					}}
				
		}}
		
		return myGraph;
	}
	

}
