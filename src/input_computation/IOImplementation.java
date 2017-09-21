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
			if ( ! words.elementAt(subIndex -1).contains(currentWord) ){
				words.elementAt(subIndex -1).insert(currentWord);
			}
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
