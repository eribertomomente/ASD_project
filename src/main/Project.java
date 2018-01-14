package main;

import data_manager.*;
import data_structure.*;

public class Project {
	
	public static void main(String[] args) {
		
		String input = InputManager.getInput();
		
		/* gestione dell'input */
		MyList<MyList<String>> words = InputManager.listWords(input);
		InputManager.sortWords(words);
		InputManager.deleteDuplicatedWords(words);
		
		/* Costruzione del grafo */
		MapCharToInt alphabet = InputManager.getAlphabet(words);
		MapStringToInt vertices = GraphBuilder.getVertices(words);
		Graph g = GraphBuilder.buildGraph(words, alphabet, vertices);
		
		/* Calcolo del cammino massimo */
		int maxPath = g.DFS_MaxPath();
		System.out.println(maxPath + " ");
		
		/* stampa in formato .dot */
		System.out.println(g.toString(vertices));
		
		
	}

}
