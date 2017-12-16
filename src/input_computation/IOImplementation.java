package input_computation;

import java.io.BufferedInputStream;
import java.util.Scanner;

import data_structure.MyList;
import data_structure.MyStringTokenizer;
import sorting.SortingAlgorithm;
import data_structure.MapCharToInt;

public class IOImplementation {
	
	/**
	 * This function reads unlimited lines of text from the command line
	 * WARNING every newline-char is substituted with a whitespace
	 * @return the input collected in a string
	 */
	public static String getInput(){
		StringBuffer sb = new StringBuffer();
		 Scanner stdin = new Scanner(new BufferedInputStream(System.in));
	        while (stdin.hasNext()) {
	        	sb.append(" "+stdin.nextLine());
	        }
	        stdin.close();
	        return sb.toString();
	}
	
	/**
	 * this function extracts every word from a string and puts them in the list of lists <words>
	 * 
	 * INVARIANT:    - <words> is a list of lists. Each sublist contains strings of the same length
	 *                         - Sublists in <words> does not contain repeated words.
	 *                         - The sublist at the "i" position in <words> contains words of length = i+1. ( 0 <= i <= words.length-1 )
	 * 
	 * @param <input> string to be analyzed
	 * @return <words> described above
	 */
	public static MyList<MyList<String>> listWords(String input){
		
		MyList<MyList<String>> words = new MyList<MyList<String>>();	
		// TODO myStringTokenizer implementare
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
	 * this function checks if there are <cardinality> sublists in the main list. If not it creates empty sublists
	 * @param <list> origin list of lists
	 * @param <cardinality>	number of the sublists that must be in the main list at the end of this function
	 * @return a list containing <cardinality> sublists
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
	 * dire anche che elimina i duplicati
	 * @param words
	 * @return
	 */
	public static void sortWords( MyList<MyList<String>> words){
		
		for (int i=0; i<words.size(); i++) {
			// ordino solo se ho almeno 2 stringhe in ogni sublist
			if (words.elementAt(i).size() > 1) {
				
				// ordino 
				SortingAlgorithm.radixSort(words.elementAt(i));
				
				// elimino le parole duplicate
				MyList<String> noDuplicates = deleteDuplicatedWords(words.elementAt(i));

				// sostiuisco la sublist con i doppioni con quella senza
				words.setElementAt(noDuplicates, i);
			}
		}
	}
	
	/*
	 * REQUIRE i duplicati consecutivi
	 * TODO
	 */
	private static MyList<String> deleteDuplicatedWords (MyList<String> words){
		
		MyList<String> noDuplicates = new MyList<String>();
		
		noDuplicates.insert(words.elementAt(0));
		
		for (int i = 1; i < words.size(); i++) {
			
			String currentWord=words.elementAt(i);
			if( ! currentWord.equals(noDuplicates.lastElement() )) {
				noDuplicates.insert(currentWord);
			}
		}
		
		return noDuplicates;
	}
	

	/**
	 * @param <input> words to be analyzed
	 * @return a MyMap which puts in relation the chars present in <input> with an increasing integer 
	 */
	public static MapCharToInt getAlphabet ( MyList<MyList<String>> input ){
		
		MapCharToInt alphabet = new MapCharToInt();
		
		for( int i=0; i<input.size(); i++ ) {
			MyList<String> sub = input.elementAt(i);
			
			for( int j=0; j<sub.size(); j++ ) {
				String word = sub.elementAt(j);
				
				for (int k = 0; k < word.length(); k++) {
					char ch = word.charAt(k);
					alphabet.insertKey(ch);
				}
			}
		}
		return alphabet;
	}
	
	/**
	 *  TODO copiare nella consegna del progetto
	 * @param <alphabet>
	 * @param <word>
	 * @return
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
