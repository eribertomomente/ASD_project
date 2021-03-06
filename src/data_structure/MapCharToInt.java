package data_structure;

import java.util.NoSuchElementException;

public class MapCharToInt {
	
	/**
	 * MISSION: 	Implementare una funzione biiettiva che mappi caratteri in interi.
	 * 					WARNING: si presuppone che non esistano chars ripetuti
	 * 					MapCharToInt è immutable.
	 * 	
	 * INVARIANTE: elements non contiene caratteri ripetuti
	 * 
	 * FUNZIONE DI ASTRAZIONE:  elements un array di 256 interi rappresenta il mio alfabeto.
	 * 												  Se alla posizione i-esima di elements viene scritto j significa che l'i-esimo carattere ascii viene mappato in j.
	 * 												  Un intero cardinality mi mantiene in memoria quanti elementi ho già inserito, per migliorare le performance.
	 */	
	
	private int[] elements;
	private int cardinality;
	
	/**
	 * Costruttore
	 */
	public MapCharToInt () {
		elements = new int[256];
		for (int i = 0; i < 256; i++) {
			elements[i]= -1 ;
		}
		cardinality = 0;
	}
	
	/**
	 * Inserisce un nuovo carattere c
	 * @param c
	 */
	public void insertKey(char c){
		if ( ! this.contains(c) ) {
			int position = (int) c;
			elements[position] = cardinality;
			cardinality ++;
		}
	}

	public int getIndex (char c) {
		if ( ! this.contains(c) ) {
			throw new NoSuchElementException();
		}
		return  elements[ (int) c ];
	}
	
	/**
	 * @param c
	 * @return true se this contiene già c, false altrimenti
	 */
	public boolean contains( char c ) {
		int position = (int) c;
		if (elements[position] == -1){
			return false;
		}
		return true;
	}
	
	/**
	 * @return
	 */
	public int size() {
		return cardinality;
	}
	
}
