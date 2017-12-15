package data_structure;

import java.util.NoSuchElementException;

public class MapStringToInt {

	/**
	 * Inserimenti in theta(1)
	 * Ricerca in O(#elementi)
	 */
	
	private String[] elements;
	private int cardinality;
	
	public MapStringToInt (int dimension) {
		elements = new String[dimension];
	}
	
	// O(n)
	public void insertKey(String key){
		if ( ! this.contains(key) ) {
			elements[cardinality] = key;
			cardinality ++;
		}
	}
	
	// O(n)
	public int getIndex (String key) {
		for(int i=0; i<cardinality; i++) {
			if (elements[i] == key) {
				return i;
			}
		}
		throw new NoSuchElementException();
	}
	
	public String getString (int index) {
		if (!(index < cardinality && index >= 0)) {
			throw new IllegalArgumentException();
		}
		return elements[index];
	}
	
	private boolean contains( String key ) {
		for(int i=0; i<cardinality; i++) {
			if (elements[i] == key) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cardinality; i++) {
			sb.append(elements[i] + " - " + i + "\n");
		}
		return sb.toString();
	}
	

public static void main(String[] args) {
		
		MapStringToInt map = new MapStringToInt(5);

		map.insertKey("ciao");
		map.insertKey("come");
		map.insertKey("stai");

		System.out.println( "inizio");
		System.out.println( map.toString() );

		System.out.println( "fine");
		
		// int first = map.getIndex("ciccio");
		int sec = map.getIndex("ciao");
		boolean bool = map.contains("stai");
		System.out.println( sec + " " + bool);
		
		
	}
	
	
}