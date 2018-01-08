package analytics;

import data_structure.Graph;
import data_structure.MapCharToInt;
import data_structure.MapStringToInt;
import data_structure.MyList;
import input_computation.GraphBuilder;
import input_computation.IOImplementation;

public class TimeAnalysis {
	
	
	/**
	 * ALGORITMO 4
	 * @return la granularita' del sistema
	 */
	public static long granularity() {

		long t0 = System.currentTimeMillis();
		long t1 = System.currentTimeMillis();
		
		while (t1 == t0) {
			t1 = System.currentTimeMillis();
		}
		return t1-t0;
	}
	
	/**
	 * ALGORITMO 5
	 * @param d input di esecuzione
	 * @param tMin
	 * @return
	 */
	public static long calcolaRip_main(int len, long tMin) {
		long t0=0;
		long t1=0;
		long rip = 1;
		while (t1-t0 <= tMin) {
			rip *= 2; // stima di rip con crescita esponenziale
			t0 = System.currentTimeMillis();
			for (int i=1; i <= rip; i++) {
				projectMain(len);
			}
			t1 = System.currentTimeMillis();
		}
		// ricerca esatta del numero di ripetizioni per bisezione
		// approssimiamo a 5 cicli
		long max = rip;
		long min = rip/2;
		int cicliErrati = 5;
		while ( max-min  >=  cicliErrati ) {
			rip = (max + min)/2; // valore mediano
			t0 = System.currentTimeMillis();	
			
			for (int i=1; i<= rip; i++) {
				projectMain(len);
			}
			t1 = System.currentTimeMillis();
			if ( t1-t0 <= tMin) {
				min = rip;
			} else {
				max = rip;
			}
		}
		return max;
	}
	
	/**
	 * ALGORITMO 5
	 * @param d input di esecuzione
	 * @param tMin
	 * @return
	 */
	public static long calcolaRip_prepara(int len, long tMin) {
		long t0=0;
		long t1=0;
		long rip = 1;
		while (t1-t0 <= tMin) {
			rip *= 2; // stima di rip con crescita esponenziale
			t0 = System.currentTimeMillis();
			for (int i=1; i <= rip; i++) {
				prepara(len);
			}
			t1 = System.currentTimeMillis();
		}
		// ricerca esatta del numero di ripetizioni per bisezione
		// approssimiamo a 5 cicli
		long max = rip;
		long min = rip/2;
		int cicliErrati = 5;
		while ( max-min  >=  cicliErrati ) {
			rip = (max + min)/2; // valore mediano
			t0 = System.currentTimeMillis();	
			
			for (int i=1; i<= rip; i++) {
				prepara(len);
			}
			t1 = System.currentTimeMillis();
			if ( t1-t0 <= tMin) {
				min = rip;
			} else {
				max = rip;
			}
		}
		return max;
	}
	
	/**
	 * ALGORITMO 6
	 * @param d
	 * @param rip
	 */
	public static double calcoloDeiTempi( int len, long rip) {
		long t0 = System.currentTimeMillis();
		for (int i=1; i<=rip; i++) {
			projectMain(len);
		}
		long t1 = System.currentTimeMillis();
		long tTot = t1-t0;
		double tSing = tTot/ (double)rip;
		return tSing;
	}
	
	/**
	 * genera in input casuale
	 * @return
	 */
	public static String prepara (int charLen) {
		return RandomGenerator.randomStringGen(charLen);
	}
	
	/**
	 * ALGORITMO 7
	 * @param d
	 * @param tMin
	 */
	public static double tempoMedioNetto(int len, long tMin) {
		
		long ripTara = calcolaRip_prepara(len, tMin);
		long ripLordo = calcolaRip_main(len, tMin);
		ripLordo +=ripTara;
		
		long t0 = System.currentTimeMillis();

		for( int i = 1; i<=ripTara; i++) {
			prepara(len);
		}
		long t1 = System.currentTimeMillis();
		long tTara = t1-t0; // tempo totale di esecuzione della tara
		t0 = System.currentTimeMillis();
		
		for (int i =1; i <= ripLordo; i++) {
			prepara(len);
			projectMain(len);
		}
		
		t1 = System.currentTimeMillis();
		long tLordo = t1-t0; // tempo totale di esecuzione del lordo
		double tMedio = tLordo/(double)ripLordo - tTara/(double)ripTara; // tempo medio di esecuzione
		return tMedio;		
	}
	
	/**
	 * ALGORITMO 9
	 * @c campione consigliabile 5 o 10
	 * @za statistica 1.96
	 * 
	 */
	public static double[] misurazione(int len, long tMin, double Delta) {
		double za = 1.96;
		int c = 5;
		
		double t = 0;
		double sum2 = 0;
		double cn = 0;
		
		double delta;
		double e = 0;
		do {
			for (int i =0; i < c; i++) {
				double m = tempoMedioNetto(len, tMin);
				t = t+m;
				sum2 = sum2 +m*m; 
			}
			cn += c;
			e = t/cn;	// misurazione del tempo medio
			double s = Math.sqrt(sum2/cn-e*e);
			delta = (1/Math.sqrt(cn)) *za*s;
		} while(! (delta < Delta));
		double[] res = {e, delta};
		return res;
	}
	
	/**
	 * Funzione principale da analizzarne la complessita'
	 * @param inputLength
	 */
	public static void projectMain(int inputLength) {
		String input = RandomGenerator.randomStringGen(inputLength);
		MyList<MyList<String>> words = IOImplementation.listWords( input );
		IOImplementation.sortWords(words);
		IOImplementation.deleteDuplicatedWords(words);MapCharToInt alphabet = IOImplementation.getAlphabet(words);
		MapStringToInt vertices = GraphBuilder.getVertices(words);
		Graph g = GraphBuilder.buildGraph(words, alphabet, vertices);
		g.DFS_MaxPath();
	}
	

	public static void main(int startingInputLength, int iterations, int increment, long tMin) {
		
		System.out.println("**** MISURAZIONE MAIN ****");
		
		int inputLength = startingInputLength;
		for (int i =0; i< iterations; i++) {
			
			double medio = tempoMedioNetto(inputLength, tMin);
			
			double Delta = medio/10;
			double[] res = misurazione(inputLength, tMin, Delta);
			
			System.out.println(res[0]+ "\t\t" + res[1]);
			inputLength += increment;
		}
		
		System.out.println("\n");
	}
	
	public static void main (String args[]) {
		
		int startingInputLength = 10;
		int iterations = 5;
		int increment = 10;
		long tMin = (long) (granularity()/0.05);
		
		main(startingInputLength, iterations, increment, tMin);
		TimeAnalysis_InputManager.main(startingInputLength, iterations, increment);
		TimeAnalysis_GraphBuilding.main(startingInputLength, iterations, increment);
		TimeAnalysis_maxPath.main(startingInputLength, iterations, increment);
	}

}
