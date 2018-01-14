package analytics;

import data_manager.GraphBuilder;
import data_manager.InputManager;
import data_structure.Graph;
import data_structure.MapCharToInt;
import data_structure.MapStringToInt;
import data_structure.MyList;

public class TimeAnalysis_GraphManager {

	
	/**
	 * ALGORITMO 5
	 * @param d input di esecuzione
	 * @param tMin
	 * @return
	 */
	public static long calcolaRip_GraphBuilding(int len, long tMin) {
		long t0=0;
		long t1=0;
		long rip = 1;
		while (t1-t0 <= tMin) {
			rip *= 2; // stima di rip con crescita esponenziale
			t0 = System.currentTimeMillis();
			for (int i=1; i <= rip; i++) {
				graphBuilding(len);
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
				graphBuilding(len);
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
	 * genera una tara casuale
	 * @return
	 */
	public static MyList<MyList<String>> prepara (int charLen) {
		String input = RandomGenerator.randomStringGen(charLen);

		/* gestione dell'input */
		MyList<MyList<String>> words = InputManager.listWords(input);
		InputManager.sortWords(words);
		InputManager.deleteDuplicatedWords(words);
		
		return words;
	}
	
	/**
	 * ALGORITMO 7
	 * @param d
	 * @param tMin
	 */
	public static double tempoMedioNetto(int len, long tMin) {
		
		long ripTara = calcolaRip_prepara(len, tMin);
		long ripLordo = calcolaRip_GraphBuilding(len, tMin);
		
		long t0 = System.currentTimeMillis();

		for( int i = 1; i<=ripTara; i++) {
			prepara(len);
		}
		long t1 = System.currentTimeMillis();
		long tTara = t1-t0; // tempo totale di esecuzione della tara
		t0 = System.currentTimeMillis();
		
		for (int i =1; i <= ripLordo; i++) {
			graphBuilding(len);
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
	public static void graphBuilding(int inputLength) {
		String input = RandomGenerator.randomStringGen(inputLength);

		/* gestione dell'input */
		MyList<MyList<String>> words = InputManager.listWords(input);
		InputManager.sortWords(words);
		InputManager.deleteDuplicatedWords(words);
		
		/* Costruzione del grafo */
		MapCharToInt alphabet = InputManager.getAlphabet(words);
		MapStringToInt vertices = GraphBuilder.getVertices(words);
		Graph g = GraphBuilder.buildGraph(words, alphabet, vertices);
		
		/* Calcolo del cammino massimo */
		g.DFS_MaxPath();
		/* stampa in formato .dot */
		g.toString(vertices);
	}
	
	public static void timeAnalysis(int startingInputLength, int iterations, int increment) {
		
		System.out.println("**** MISURAZIONE GRAPH MANAGER ****");
		
		int inputLength = startingInputLength;
		for (int i =0; i< iterations; i++) {
			
			long tMin = (long) (TimeAnalysis_Project.granularity()/0.05);
			double medio = tempoMedioNetto(inputLength, tMin);
			
			double Delta = medio/10;
			double[] res = misurazione(inputLength, tMin, Delta);
			
			System.out.println(res[0] + "\t" + res[1]);
			inputLength += increment;
		}
		
		System.out.println("\n");
	}
	
}
