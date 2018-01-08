package data_structure;

import java.util.StringTokenizer;

public class MyStringTokenizer {
	
	/**
	 * MISSSION: Questa classe è l'implementazione dell'analoga classe Java: StringTokenizer.
	 * 					  MyStringTokenizer è immutable.
	 * 
	 * FUNZIONE DI ASTRAZIONE: MyStringTokenizer viene rappresentato con un array di stringhe che non contengono spazi e un intero che 
	 * 													indica qual è il primo elemento non "letto" dal client.
	 */
	
	private String[] words;
	private int index;
	
	/**
	 * Costruttore
	 * @param <str> stringa da scomporre
	 */
	public MyStringTokenizer(String str) {
		
		int count=1; // numero di parole in str 
		for (int i = 0; i <str.length(); i++) {
			if (str.charAt(i)==' ') { 	count++; }
		}
		
		this.words= new String[count];
		
		int i=0;
		int j=0;
		StringBuffer buffer = new StringBuffer(); // buffer per accumulare le lettere di una parola
		
		// finchè non ho raccolto tutte le parole
		// e finchè non ho scandito tutti i caratteri
		while (i<count && j<str.length()) {
			if (str.charAt(j) == ' ') {
				
				// creo una nuova parola in words
				this.words[i] = buffer.toString();
				buffer = new StringBuffer();
				i++;
			} else {
				
				// salvo la lettere nel buffer
				buffer.append(str.charAt(j));
			}
			j++;
		}
		
		// controllo per quale condizione ero uscito dal while
		if ( j==str.length() ) {
			// salvo anche l'ultima parola rimasta nel buffer
			this.words[i] = buffer.toString();
		}
		
		this.index=0;
	}
	
	/**
	 * @return true se ci sono ancora tokens da restituire, false altrimenti.
	 */
	public  boolean hasMoreTokens() {
		if (index < this.words.length){
			if (this.words[index].length()==0) {
				index++;
				return hasMoreTokens();
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * WARNING: verifica di restituire sempre stringhe di lunghezza maggiore di zero
	 * @return l'index-esimo token
	 */
	public String nextToken() {
		String res = this.words[index];
		this.index++;
		return res;
	}
	
	public static void main(String[] args) {
//		StringTokenizer q = new StringTokenizer("ciao");
//		while (q.hasMoreTokens()) {
//			String s = q.nextToken();
//		}
		MyStringTokenizer st = new MyStringTokenizer( "                                        " );
		
		while ( st.hasMoreTokens() ){
			
			System.out.println( st.nextToken() );
			
		}
		System.out.println( "ciao");
	}
	
	

}
