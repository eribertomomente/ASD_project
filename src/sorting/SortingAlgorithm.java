package sorting;


import data_structure.MyList;

public class SortingAlgorithm {
	 

	 
	    // A function to do counting sort of <a>[] according to
	    // the digit represented by exp.
		/**
		 * 
		 * @param words
		 * @param n
		 * @param digit
		 */
	    public static void countingSort(MyList<String> words, int digit)
	    {
	    		int wordLength = words.elementAt(0).length();
	    		int listLength = words.size();
	    		//array di stringhe ordinate alla digit-esima posizione
	        String output[] = new String[listLength];
	        //array delle occorrenze
	        int count[] = new int[256];
	        
	        for (int i=0; i<256; i++) {
	        		count[i]=0;
	        }
	 
	        // Conto le occorrenze
	        for (int i = 0; i < listLength; i++) {
	            count[ (int) words.elementAt(i).charAt(wordLength-digit-1) ]++;
	        }
	 
	        for (int i = 1; i < 256; i++) {
		        // incremento count[i] affinchè contenga l'attuale posizione di i in oputput[]
	            count[i] += count[i - 1];
	        }
	 
	        for (int i = listLength - 1; i >= 0; i--) {
	            output[count[ (int) words.elementAt(i).charAt(wordLength-digit-1) ]-1] = words.elementAt(i);
	            count[ (int) words.elementAt(i).charAt(wordLength-digit-1) ]--;
	        }

	        // copio output[] in <words> affinchè <words> contenga
	        // le stringhe ordinate sulla base del digit corrente
	        for (int i = 0; i < listLength; i++) {
	        		words.setElementAt( output[i], i);
	        }
	    }
	 
	    /**
	     * REQUIRE:  <words> deve contenere stringhe di lunghezza uguale
	     * @param words
	     */
	    public static void radixSort(MyList<String> words)
	    {
	    		if (words.size() > 0) {
	    			
		        // Trovo la lunghezza delle stringhe dell'array in input
	    			// 		per sapere il numero dei digit totali
	    			//		da require so che words[i].length() è cost per ogni i in [0..words.length]
		        int digit = words.elementAt(0).length();
		 
		        // lancio countingSort per ogni digit
		        for (int i = 0; i < digit; i++) {
		        		countingSort(words, i);
		        }
	    		}
	    }
	
	 
	 
	    public static void main (String[] args)
	    {
	        String arr[] = {"ciao", "come", "stai", "bene", "tuuu"};
	        MyList<String> ciccio = new MyList<String>();
	        for(int i = 0; i<arr.length; i++) {
	        		ciccio.insert(arr[i]);
	        }
	        radixSort(ciccio);
	        System.out.print(ciccio.toString());
	    }

}
