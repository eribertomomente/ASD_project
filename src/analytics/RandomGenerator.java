package analytics;

import data_structure.MyList;

/**
 * Classe che genera numeri casuali,
 * migliore del random di sistema
 */
public class RandomGenerator {

	// seme del prng
	private double seed;
	
	
	/**
	 * @param s seme iniziale
	 */
	public RandomGenerator(double s) {
	   seed = s;
	}
	
	
	/**
	 * imposta il valore del seme a s
	 * @param s nuovo seme
	 */
	public void setSeed(double s) {
	   seed = s;
	}

	
	/** 
	 * @return numero compreso tra 0 e 1 (e aggiorna il seme)
	 */
	public double get() {
	
	   // Costanti
	   final int a = 16087;
	   final int m = 2147483647;
	   final int q = 127773;
	   final int r = 2836;
	
	   // Variabili
	   double lo, hi, test;
	
	   hi = (int) (seed / q);	// troncamento
	   lo = seed - q * hi;
	   test = a * lo - r * hi;
	   if (test < 0.0) {
	      seed = test + m;
	   } else {
	      seed = test;
	   }
	   return seed / m;
	}
	
	// TODO modificare in funzione dei chars non delle parole
	public static String randomWordsGen (int number){

		   RandomGenerator r = new RandomGenerator(23456789);
		   StringBuffer sb =  new StringBuffer();
		   
		   for (int i = 0; i < number; i++) {
			   int wordLength = (int) Math.round(r.get() * 10); // 0 <= length < 10
			   
			   for (int j =0; j<wordLength; j++) {
				   // TODO considerare tutti i 256 chars
				   long n = (Math.round(r.get()*(128-33))+33); // elimino i primi 33 char di controllo + lo spazio TODO is right?
//				   while (n == 127 || n == 255 || n == 135 || n == 133){ // 127 represents "delete", 255 "no-breaking space", 135,133 TODO
				   while (n == 127) {
					   n=(Math.round(r.get()*(256-33))+33);
				   }
				   sb.append((char)n);
			   }
			  sb.append(" ");
		   }
		   return sb.toString();
	}
	
	private static int getSeed() {
		int div = 10000;
		long time = System.currentTimeMillis();
		return (int) time%div;
	}
	
	/**
	 * probabilità maggiore del 20% che esca uno spazio
	 * @param number totale caratteri
	 * @return
	 */
	public static String randomStringGen (int charLen) {
		RandomGenerator r = new RandomGenerator(getSeed());
		   StringBuffer sb =  new StringBuffer();
		   
		   for (int i = 0; i < charLen; i++) {
			   int prob = (int) Math.round( r.get()*5 ); // probabilità che mi esca uno spazio
			   if (prob == 0) {
				   sb.append(" ");
			   } else { // potrebbe riuscire lo spazio. Ecco perchè maggiore del 20%
				   char rnd = (char) Math.round( r.get()*255 );
				   sb.append(rnd);
			   }
		   }
		   return sb.toString();
		   
	}


	// esempio di uso della classe RandomGenerator,
	// stampa 10 numeri casuali compresi tra 1 e 100
	public static void main(String[] args) {

	   System.out.println(randomStringGen(100));
	}

}