package data_structure;


public class MyStringTokenizer {
	
	/**
	 * mutabile
	 */
	
	private String[] words;
	private int index;
	
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

	public  boolean hasMoreTokens() {
		if (index < this.words.length){
			return true;
		}
		return false;
	}
	
	public String nextToken() {
		String res = this.words[index];
		this.index++;
		return res;
	}
	
	public static void main(String[] args) {
		MyStringTokenizer st = new MyStringTokenizer( "se nonna non era serena non si rasserenerava nonno" );
		
		while ( st.hasMoreTokens() ){
			
			System.out.println( st.nextToken() );
			
		}
	}
	
	

}
