package sorting;


import data_structure.MyList;

public class SortingAlgorithm {
	
	/**
	 * MISSION: implementare un algoritmo di ordinamento di stringhe
	 */
	 

    /**
     * Implementazione di RadixSort su stringhe
     * @param <words> lista di stringhe da ordinare
     * 					REQUIRE:  <words> deve contenere stringhe di lunghezza uguale
     */
    public static void radixSort(MyList<String> words){
    		if (words.size() > 0) {
    			
	        // Trovo il numero di caratteri che compongono le stringhe in input
    			//		da REQUIRE so che words[i].length() è cost per ogni i in [0..words.length]
	        int digit = words.elementAt(0).length();
	 
	        // lancio countingSort per ogni "digit"
	        for (int i = 0; i < digit; i++) {
	        		countingSort(words, i);
	        }
    		}
    }

	/**
	 * Implementazione di CountingSort su stringhe
	 * @param <words> lista di stringhe da ordinare secondo la digit-esima posizione
	 * @param <digit> parametro
	 */
    public static void countingSort(MyList<String> words, int digit){
    
    		int wordLength = words.elementAt(0).length();
    		int listLength = words.size();
    		
    		//array di stringhe ordinate alla digit-esima posizione
        String output[] = new String[listLength];
        
        //array delle occorrenze
        int count[] = new int[256];
        
        for (int i=0; i<256; i++) {
        		count[i]=0;
        }
 
        // Conto delle occorrenze
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
 


 
 
    public static void main (String[] args){
        String arr[] = {"ciao", "come", "stai", "bene", "tuuu"};
        MyList<String> ciccio = new MyList<String>();
        for(int i = 0; i<arr.length; i++) {
        		ciccio.insert(arr[i]);
        }
        radixSort(ciccio);
        System.out.print(ciccio.toString());
    }

    
}
