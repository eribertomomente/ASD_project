package data_structure;

import java.util.NoSuchElementException;

public class MapCharToInt {
	
	/**
	 * chiave valore
	 * solo char to int
	 * la posizione in elements determina qual è la lettera corrispondente in ascii
	 * TODO ste robe sotto vanno bene per l'invariante di MyMap
	 * INVARIANT:	- "alphabet" does not contains repeated chars
	 * 						- "alphabet" contains only chars used in the input
	 * 
	 * @return a MyMap which puts in relation the chars present in <input> with an increasing integer 
	 * 
	 */
	
	private int[] elements;
	private int cardinality;
	
	public MapCharToInt () {
		elements = new int[256];
		for (int i = 0; i < 256; i++) {
			elements[i]= -1 ;
		}
		cardinality = 0;
	}
	
	
	public void insertKey(char key){
		if ( ! this.contains(key) ) {
			int position = (int) key;
			elements[position] = cardinality;
			cardinality ++;
		}
	}
	
	public int getIndex (char key) {
		if ( ! this.contains(key) ) {
			throw new NoSuchElementException();
		}
		return  elements[ (int) key ];
	}
	
	public boolean contains( char key ) {
		int position = (int) key;
		if (elements[position] == -1){
			return false;
		}
		return true;
	}
	
	public int size () {
		return cardinality;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 256; i++) {
			if ( elements[i] != -1 ) {
				sb.append(elements[i] + " - " + (char) i + "\n");
			}
		}
		return sb.toString();
	}
	
public static void main(String[] args) {
		
		MapCharToInt map = new MapCharToInt();

		map.insertKey('c');
		map.insertKey('o');
		map.insertKey('o');
		map.insertKey('a');

		System.out.println( map.toString() );
		
		// int first = map.getIndex("ciccio");
		int sec = map.getIndex('c');
		boolean bool = map.contains('b');
		System.out.println( sec + " " + bool);
		
		
	}
	
}
