package data_manager;

import data_structure.Graph;
import data_structure.MapCharToInt;
import data_structure.MapStringToInt;
import data_structure.MyList;

public class GraphBuilder {
	
	/**
	 * MISSION In questa classe sono presenti funzioni utili per la costruzione del grafo
	 */
	

	/**
	 * stabilisce la relazione tra due "vettori di caratteri" presentata nella consegna del progetto
	 * @param first prima parola
	 * @param second seconda parola
	 * @return true se first e' in relazione con second, false altrimenti
	 */
	private static boolean areInRelation(int[] first, int[] second) {
		boolean flag = false;
		for ( int i=0; i< first.length; i++) {
			
			// caso in cui sono entrambi a zero, va bene (a meno che non siano tutti)
			if ( first[i] != 0 || second[i] != 0 ) {
				if (first[i] < second[i]) {
					return false;
				} else if (first[i] != second[i]){
					// verifico che almeno un char sia maggiore. Altrimenti se ho tutti zeri restituisco falso.
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * Genera la corrispondenza tra parole e indici dei nodi del grafo
	 * WARNING  words non devo contenere parole ripetute.
	 * @param words lista di gruppi di parole
	 * @return l'enumerazione stringa-intero
	 */
	public static MapStringToInt getVertices(MyList<MyList<String>> words) {
		
		// conto numero di elementi in words
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
	
	/**
	 * inserisce nel grafo tanti vertici quante le parole in words
	 * @param myGraph grafo vuoto
	 * @param words lista di gruppi di parole
	 * @return il grafo
	 */
	private static void insertVertices(Graph myGraph, MyList<MyList<String>> words) {
		// calculate number of elements in words
		int numberWords = 0;
		for (int i = 0; i < words.size(); i++) {
			numberWords += words.elementAt(i).size();
		}
		// add a vertex for each word
		for (int i = 0; i < numberWords; i++) {
			myGraph.addVertex();
		}
	}
	
	/**
	 * Costruisce un arco tra due nodi del grafo se le parole in quei nodi sono in relazione.
	 * @param myGraph grafo
	 * @param words lista di gruppi di parole
	 * @param alphabet alfabeto dell'input
	 * @param map mappa stringa-intero
	 * @return il grafo con archi fra tutti i nodi in relazione
	 */
	private static void buildEdges(Graph myGraph, MyList<MyList<String>> words, MapCharToInt alphabet, MapStringToInt map ) {
		
		for(int i = 0; i < words.size(); i++) {
			for (int j = 0; j <= i; j++) {
				
				for (String s1 : words.elementAt(i)) {
					if (s1!= null) {
						
						for (String s2 : words.elementAt(j)) {
							if (s2 != null) {
							
								int[] struct1 = InputManager.buildWordStructure( alphabet, s1);
								int[] struct2 = InputManager.buildWordStructure( alphabet, s2);
								
								if ( areInRelation(struct1, struct2) ) {							
									int startingVertex = map.getIndex(s1);
									int targetVertex = map.getIndex(s2);
									myGraph.setEdge(startingVertex, targetVertex);
								}
						}}
				}}
		}}
	}
	
	/**
	 *  crea e costruisce un grafo con le parole contenute in words, secondo la relazione nella consegna del progetto
	 * @param words lista di gruppi di parole
	 * @param alphabet alfabeto dell'input
	 * @param vertices mappa stringa-intero
	 * @return il grafo delle parole
	 */
	public static Graph buildGraph(MyList<MyList<String>> words, MapCharToInt alphabet, MapStringToInt vertices) {
		Graph myGraph = new Graph();
		// insert as many vertex as the number of words
		insertVertices(myGraph, words);
		// build edges
		buildEdges(myGraph, words, alphabet, vertices);
		return myGraph;
	}
	
	
	

}
