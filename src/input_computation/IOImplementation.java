package input_computation;

import java.io.BufferedInputStream;
import java.util.Scanner;

import data_structure.MyList;
import data_structure.MyStringTokenizer;
import sorting.SortingAlgorithm;
import data_structure.MapCharToInt;

public class IOImplementation {
	
	/**
	 * MISSION: implementare una classe per l'acquisizione, la gestione e l'elaborazione dell'input
	 */
	
	/**
	 * Questa funzione legge illimitate linee di testo da stdin
	 * WARNING ogni ritorno a capo viene sostuito con uno spazio
	 * @return stringa contente l'input
	 */
	public static String getInput(){
		StringBuffer sb = new StringBuffer();
		Scanner stdin = new Scanner(new BufferedInputStream(System.in)); 
		if(stdin.hasNext()) {	 // gestisce il caso in cui non c'è una linea di testo
			sb.append(stdin.nextLine());
		}
	 	while (stdin.hasNext()) {
	 		sb.append("\n"+stdin.nextLine());
	 	}
	 	stdin.close();
	 	return sb.toString();
	}	
	
	/**
	 * Questa funzione legge ogni parola dalla stringa in input e le salva in una lista di liste
	 * 
	 * INVARIANT:    - words e' una lista di liste. Ogni sottolista contiene stringhe della stessa lunghezza.
	 *                         - La sottolista in posizione i-esima in <words> contiene parole di lunghezza i+1. ( 0 <= i <= words.length-1 )
	 * 
	 * @param input string da analizzare
	 * @return words
	 */
	public static MyList<MyList<String>> listWords(String input){
		
		MyList<MyList<String>> words = new MyList<MyList<String>>();
		MyStringTokenizer st = new MyStringTokenizer( input );
		
		while ( st.hasMoreTokens() ){
			
			String currentWord = st.nextToken();
			int len = currentWord.length();
		
			words = fixMissingLists(words, len);
		    words.elementAt(len -1).insert(currentWord);
		}
		return words;
	}
	
	/**
	 * Questa funzione verifica che ci siano cardinality sottoliste nella lista principale. 
	 * 
	 * this function checks if there are cardinality sublists in the main list. If not it creates empty sublists
	 * @param list origin list of lists
	 * @param cardinality	number of the sublists that must be in the main list at the end of this function
	 * @return a list containing cardinality sublists
	 */
	private static MyList<MyList<String>> fixMissingLists (MyList<MyList<String>> list, int cardinality){
		
		if ( list.size() < cardinality ) {
			
			MyList<String> newWordList = new MyList<String>();
			list.insert(newWordList);
			return fixMissingLists( list, cardinality );
			
		} else {
			return list;
		}
		
	}
	
	/**
	 * ordina i gruppi di parole contenuti in words
	 * @param words lista di gruppi di parole da ordinare
	 */
	public static void sortWords( MyList<MyList<String>> words){
		for (int i=0; i<words.size(); i++) {
			// ordino solo se ho almeno 2 stringhe in ogni sublist
			if (words.elementAt(i).size() > 1) {
				SortingAlgorithm.radixSort(words.elementAt(i));
			}
		}
	}
	

	/**
	 * Elimina i duplicati di stringhe già esistenti
	 * REQUIRE stringhe equivalenti devono essere memorizzate successivamente.
	 * @param words
	 */
	public static void deleteDuplicatedWords (MyList<MyList<String>> words){
		
		for (int i=0; i<words.size(); i++) {
			
			if (words.elementAt(i).size() != 0) {
				MyList<String> noDuplicates = new MyList<String>();
				noDuplicates.insert(words.elementAt(i).elementAt(0));
				
				for (int j = 1; j < words.elementAt(i).size(); j++) {			
					String currentWord=words.elementAt(i).elementAt(j);
					if( ! currentWord.equals(noDuplicates.lastElement() )) {
						noDuplicates.insert(currentWord);
					}
				}
				words.setElementAt(noDuplicates, i);
			}
		}
	}
	

	/**
	 * @param input gruppi di parole da analizzare
	 * @return una corrispondenza tra caratteri e interi
	 */
	public static MapCharToInt getAlphabet ( MyList<MyList<String>> input ){
		
		MapCharToInt alphabet = new MapCharToInt();
		
		for( int i=0; i<input.size(); i++ ) {
			MyList<String> sub = input.elementAt(i);
			for( int j=0; j<sub.size(); j++ ) {
				
				String word = sub.elementAt(j);
				if (word != null) {
					for (int k = 0; k < word.length(); k++) {
						char ch = word.charAt(k);
						alphabet.insertKey(ch);
					}
				}
				
		}}
		return alphabet;
	}
	
	/**
	 * Costruisce il "vettore dei caratteri"
	 * @param alphabet alfabeto dell'input
	 * @param word parola da cui creare il vettore
	 */
	public static int[] buildWordStructure ( MapCharToInt alphabet, String word) {
		
		int[] structure = new int[alphabet.size()];
		
		for (int i = 0; i < word.length(); i++) {
			char ch =  word.charAt(i);
			structure[ alphabet.getIndex(ch) ] ++;
		}
		return structure;
	}
	
	
	
// TODO TEST check each function with empty sublist
	
	

	public static void main(String[] args) {
		//MyList<String> words =  listWords( getInput() ) ;
		MyList<MyList<String>> words =  listWords( "come come ciao stai? I am eri ciao" ) ;
		sortWords(words); 
		deleteDuplicatedWords(words);
	
		System.out.println("********** WORDS DOWN HERE *************");
		for (int i = 0; i< words.size(); i++){
			System.out.println(words.elementAt(i));
		}
		
		MapCharToInt alpha= getAlphabet(words);
		System.out.println("********** alphabet DOWN HERE *************");
		System.out.println( alpha.toString() );

		System.out.println("********** arrays DOWN HERE *************");
		/*
		while( words.hasNext() ) {
					
			MyList<String> sub = words.getData();
			
			while (sub.hasNext() ) {
				int[] s = buildWordStructure( alpha, sub.getData() );
				System.out.println( Arrays.toString(s) );
				sub = sub.getNext();
			}
			words = words.getNext();
		}
		*/
	}

}
