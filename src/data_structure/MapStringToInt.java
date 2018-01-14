package data_structure;

import java.util.NoSuchElementException;

public class MapStringToInt {
	
	/**
	 * MISSION: 	Implementare una funzione biiettiva che mappi stringhe in interi.
	 * 					L'utilità di questa funzione è di avere una corrispondenza tra le "parole" che identificano i nodi del grafo e gli indici di tali nodi.
	 * 					WARNING: si presuppone che non esistano stringhe ripetute
	 * 					MapStringToInt è immutable.
	 * 
	 * FUNZIONE DI ASTRAZIONE:  Un array di stringhe elements conterrà tutte le parole inserite.
	 * 												  Un intero cardinality mi mantiene in memoria quanti elementi ho già inserito, per migliorare le performance.
	 * 												  L'intero associato a una data stringa s, è la posizione di s in elements
	 */	
	
	private String[] elements;
	private int cardinality;
	
	/**
	 * Costruttore
	 * @param dim numero elementi da mappare
	 */
	public MapStringToInt (int dim) {
		elements = new String[dim];
	}
	
	/**
	 * WARNING s non deve già esistere in this
	 * @param s nuova parola da mappare
	 */
	public void insertKey(String s){
		elements[cardinality] = s;
		cardinality ++;
	}
	
	/**
	 * WARNING s deve essere unica in this
	 * @param s
	 * @return l'intero in cui è mappata la stringa s
	 */
	public int getIndex (String s) {
		for(int i=0; i<cardinality; i++) {
			if (elements[i] == s) {
				return i;
			}
		}
		throw new NoSuchElementException();
	}
	
	/**
	 * @param index
	 * @return la stringa che viene mappata in index
	 */
	public String getString (int index) {
		if (!(index < cardinality && index >= 0)) {
			throw new IllegalArgumentException();
		}
		return elements[index];
	}
	
}