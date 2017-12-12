package input_computation;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

import data_structure.MyList;
import data_structure.MapCharToInt;
import java.util.Arrays;

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
		StringTokenizer st = new StringTokenizer( input );
		
		while ( st.hasMoreTokens() ){
			
			String currentWord = st.nextToken();
			int subIndex = currentWord.length();
			
			words = fixMissingLists(words, subIndex);
		    words.elementAt(subIndex -1).insert(currentWord);
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
			
			MyList<String> newGroup = new MyList<String>();
			list.insert(newGroup);
			return fixMissingLists( list, cardinality );
			
		} else {
			return list;
		}
		
	}
	
	/*
	 * TODO
	 */
	public static MyList<MyList<String>> deleteDuplicatedWords (MyList<MyList<String>> words){
		
		for (int i = 0; i < words.size(); i++) {
			MyList<String> sublist = words.elementAt(i);
			
		}
		
		return words;
	}
	
	//	TODO devo fare un ordinamento lessicografico di ogni sublist in modo che i doppioni mi appaiano consecutivamente
	
	/*
	 * Radix Sort 
	 */
	public static MyList<String> myRadixSort(MyList<String> list ) {
		for ( int i = list.size() -1; i >= 0; i--) {
			list = myCountingSort( list, i);
		}
		return list;
	}
	
	public static MyList<String> myCountingSort(MyList<String> list, int index){
		
		MyList<String> sorted = new MyList<String>();
		int[] c = new int[256];
		
		// for j← 1 to length[A]
		for ( int i = 0; i < list.size(); i++ ) {
			// C[A[j]] ← C[A[j]]+1
			c[ list.elementAt(i).charAt(index) ] ++;
		}
		
		// for i← 2 to k
		for ( int i = 1; i < 256; i++) {
			// C[i] ← C[i]+C[i-1]
			c[i] += c[i-1];
		}
		
		// for j ← length[A] downto 1
		for ( int i = list.size()-1; i>= 0; i--) {
			
			// calcolo A[j]
			char A_j = list.elementAt(i).charAt(index);
			// calcolo C[A[j]] 
			int C_A_j = c [ (int) A_j ];
			// devo cercare la prima parola che ha quel carattere alla <index> posizione TODO attenzione alle parole già inserite
			for ( int j = 0; j < list.size(); j++) {
				if ( list.elementAt(j).charAt(index) == (char) C_A_j ) {
					// B[C[A[j]]] ← A[j]
					sorted.insert(list.elementAt(j), C_A_j -1) ;
					break;
				}
			}
		}
		// C[A[j]] ← C[A[j]]-1
		
		
		return sorted;
	}


	public static MyList<String> myRadixSortPro(MyList<String> words ) {
		for ( int i = words.size() -1; i >= 0; i--) {
			char[] h = new char[words.size()]; 
			for (int j=0; j<words.size(); j++) {
				
				// in h raccolgo tutte le i-esime lettere di ogni parola di words
				char c;
				
				// non devo generare errori se becco parole più corte di altre
				if ( words.elementAt(i).length() >= j ) {
					c = words.elementAt(i).charAt(j);
				} else {
					// TODO devo occuparmi di gestire questi spazi che vado ad aggiungere
					c = ' ';
				}
				
				h[j]=c;

			}
			
			// devo riassemblare la stringa ora
			// parto ricomponendo ogni parola dall'inizio o da capo in modo da aggiungere sempre in coda/testa il nuovo char
		}
		return words;
	}
	
	public static char[] myCountingSortPro(char[] a){
		
		char[] sorted = new char[a.length];
		int[] c = new int[256];
		
		// for j← 1 to length[A]
		for ( int j = 0; j < a.length; j++ ) {
			// C[A[j]] ← C[A[j]]+1
			c[ a[j] ] ++;
		}
		
		// for i← 2 to k
		for ( int i = 1; i < 256; i++) {
			// C[i] ← C[i]+C[i-1]
			c[i] += c[i-1];
		}
		
		// for j ← length[A] downto 1
		for ( int j = list.size()-1; j>= 0; j--) {
			
			sorted[ c[ a[j] ] ] = a[j];
			c[a[j]] -- ;

		}
		
		return sorted;
	}

	/**
	 * @param <input> words to be analyzed
	 * @return a MyMap which puts in relation the chars present in <input> with an increasing integer 
	 */
	public static MapCharToInt getAlphabet ( MyList<MyList<String>> input ){
		
		MapCharToInt alphabet = new MapCharToInt();
		
		while( input.hasNext() ) {
			MyList<String> sub = input.getData();
			
			while (sub.hasNext() ) {
				String word = sub.getData();
				
				for (int i = 0; i < word.length(); i++) {
					char ch = word.charAt(i);
					alphabet.insertKey(ch);
				}
				sub = sub.getNext();
			}
			input = input.getNext();
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
	

	
	
	

	public static void main(String[] args) {
		//MyList<String> words =  listWords( getInput() ) ;
		MyList<MyList<String>> words =  listWords( "come ciao stai? I am eri" ) ;
		
		System.out.println("********** WORDS DOWN HERE *************");
		for (int i = 0; i< words.size(); i++){
			System.out.println(words.elementAt(i));
		}
		
		MapCharToInt alpha= getAlphabet(words);
		System.out.println("********** alphabet DOWN HERE *************");
		System.out.println( alpha.toString() );

		System.out.println("********** arrays DOWN HERE *************");
		while( words.hasNext() ) {
					
			MyList<String> sub = words.getData();
			
			while (sub.hasNext() ) {
				int[] s = buildWordStructure( alpha, sub.getData() );
				System.out.println( Arrays.toString(s) );
				sub = sub.getNext();
			}
			words = words.getNext();
		}
	}

}
