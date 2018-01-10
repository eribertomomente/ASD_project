package data_structure;

public class Graph {
	
	/**
	 * MISISON: implementare un grafo. Graph e' immutabile e unbounded.
	 * 
	 * FUNZIONE DI ASTRAZIONE: Graph viene implementato come liste di adiacenza.
	 */

	private MyList<MyList<Integer>> graph;

	/**
	 * Costruttore
	 */
	public Graph(){
		graph= new MyList<MyList<Integer>>();
	}

	/**
	 * @return numero di vertici
	 */
	public int size(){ 
		return this.graph.size();
	}

	/**
	 *  aggiunge un nuovo vertice nel grafo
	 */
	public void addVertex (){		 
		this.graph.insert( new MyList<Integer>() );
	}

	/**
	 * costruisce un arco orientato da start a target
	 * @param start vertice di partenza dell'arco
	 * @param target vertice di destinazione dell'arco
	 */
	public void setEdge(int start, int target){
		this.graph.elementAt(start).insert(target);
	}
	
	private int[] color;	// mantiene lo "stato" di ogni vertice
	
	// costanti
	private static final int WHITE = 0;
	private static final int GREY = 1;
	private static final int BLACK = 2;

	/**
	 * 
	 * @return
	 */
	public int DFS_MaxPath(){
		
		int verticesNum = this.size();
		
		color = new int[verticesNum];
		int[] maxPath = new int[verticesNum];

		//inizializzo il vettore dei colori
		for ( int i=0; i<verticesNum; i++){
			color[i] = WHITE;
			maxPath[i]=0;
		}    
		
		for ( int i=0; i<verticesNum; i++){
			if (color[i] == WHITE){
				maxPath = this.DFSVisit(i, maxPath);
			}
		}	
		
		// ricerca del max in maxPath
		int max=0;
		for (int i=0; i<maxPath.length; i++) {
			if (maxPath[i]>max) {
				max= maxPath[i];
			}
		}
		return max;
	}

	/**
	 * 
	 * @param vertex
	 * @param maxPath
	 * @return
	 */
	private int[] DFSVisit(int vertex, int[] maxPath ) {
		color[vertex] = GREY;
		for ( int v : this.graph.elementAt(vertex) ){
			 
			if ( color[v] == WHITE ){
				maxPath = this.DFSVisit(v, maxPath);
			} 
			// aggiorno il max
			if(maxPath[v]+1 > maxPath[vertex]) { 
				maxPath[vertex]=maxPath[v]+1;
			}
		}
		color[vertex] = BLACK;
		return maxPath;
	}

	/**
	 * WARNING alcuni char vengono non stampati. Non bestemmiare se si vedono parole piu corte
	 * @param original
	 * @return
	 */
	private String avoidErrorChar (String original) {
		StringBuffer modSB = new StringBuffer();
		for (int j=0; j < original.length(); j++) {
			char c = original.charAt(j);
			if (c == '\n') {
				modSB.append("\\n");  // rimpiazzo il ritorno a capo con il simbolo \n
			} else if (c == '"') {
				modSB.append("''"); // rimpiazzo le doppie virgolette (") con due apostofi ('')
			} else if ((int)c > 31 || (int)c !=127){ // non stampo i primi 32 caratteri di controllo
				modSB.append(c);
			}
		}
		return modSB.toString();
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	public String toString(MapStringToInt map){
		StringBuffer s = new StringBuffer("digraph G_T {\n");
		for ( int i = 0; i< this.graph.size(); i++){
			String original = map.getString(i);
			String modified = avoidErrorChar( original );
			s.append(String.format("%d [label=\"%s\"];\n", i, modified));
		}
		for ( int m = 0; m< this.graph.size(); m++){
			for ( int n = 0; n< this.graph.elementAt(m).size(); n++){
				s.append(String.format("%d -> %d;\n", m, this.graph.elementAt(m).elementAt(n) ));
			}
		}
		s.append("}");
		return s.toString();
	}
	
	public String toString(){
		StringBuffer s = new StringBuffer();
		for ( int i = 0; i< this.graph.size(); i++){
			s.append(String.format("%d -> ", i));
			for ( int j = 0; j< this.graph.elementAt(i).size(); j++){
				s.append(String.format("%d, ", this.graph.elementAt(i).elementAt(j) ));
			}
			s.append("\n");
		}
		return s.toString();
	}

}
