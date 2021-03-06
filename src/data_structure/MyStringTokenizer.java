package data_structure;

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
		
		int count=0; // stima del numero di parole in str 
		for (int i = 1; i <str.length(); i++) {
			if(		(str.charAt(i - 1) != ' ' && str.charAt(i) == ' ') ||
					(i == (str.length() - 1) && str.charAt(i) != ' ')) {
				count++;
			}
		}
		
		this.words = new String[count];
		
		int i=0;
		int j=0;
		StringBuffer buffer = new StringBuffer(); // buffer per accumulare le lettere di una parola
		
		// finche' non ho raccolto tutte le parole
		// e finche' non ho scandito tutti i caratteri
		while ( j<str.length() ) {
			// se il carattere non è uno spazio lo aggiungo nel buffer
			if(str.charAt(j) != ' ') {
				// salvo la lettere nel buffer
				buffer.append(str.charAt(j));
			// altrimenti se è uno spazio e il buffer non è vuoto
			} else if (buffer.length() > 0) {
				// creo una nuova parola in words
				this.words[i++] = buffer.toString();
				buffer = new StringBuffer();
			}

			j++;
		}

		// controllo per quale condizione ero uscito dal while
		if (str.charAt(str.length() - 1) != ' ') {
			// salvo anche l'ultima parola rimasta nel buffer
			this.words[i] = buffer.toString();
		}

		this.index=0;
	}
	
	/**
	 * @return true se ci sono ancora token da restituire, false altrimenti.
	 */
	public boolean hasMoreTokens() {
		// se il mio indice e' minore del numero di parole 
		if (this.index < this.words.length){
			return true;
		}
		return false;
	}
	
	/**
	 * WARNING: verifica di restituire sempre stringhe di lunghezza maggiore di zero
	 * @return l'index-esimo token
	 */
	public String nextToken() {
		String res =  this.words[this.index];
		this.index++;
		return res;
	}

}
