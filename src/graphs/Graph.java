package graphs;

import java.util.ArrayList;

import data_structure.MapStringToInt;

public class Graph {

	private ArrayList<ArrayList<Integer>> graph;

	// EXTRA ATTRIBUTES:
	private int[] color;			// keep the "status" of each vertex

	// CONSTANTS:	
	private static final int WHITE = 0;
	private static final int GREY = 1;
	private static final int BLACK = 2;

	// CONSTRUCTOR:	
	public Graph(){
		graph= new ArrayList<ArrayList<Integer>>();
	}



	/**
	 * @return number of vertices
	 */
	public int size(){ 
		return this.graph.size();
	}


	/**
	 *  add a new empty vertex in the graph
	 */
	public void addVertex (){		 
		this.graph.add( new ArrayList<Integer>() );
	}


	/**
	 * build an edge on the graph from <start> to <target>
	 * @param u starting vertex
	 * @param v target vertex
	 */
	public void setEdge(int start, int target){
		this.graph.get(start).add(target);
	}




	/**
	 * DEEP FIRST SEARCH
	 */
	public int DFS_MaxPath(){
		
		int verticesNum = this.graph.size();
		
		color = new int[verticesNum];
		int[] maxPath = new int[verticesNum];

		//inizializzo il vettore dei colori
		for ( int i=0; i< this.graph.size(); i++){
			color[i] = WHITE;
			maxPath[i]=0;
		}    
		
		for ( int i=0; i< this.graph.size(); i++){
			// TODO forse dovrei vedere piuttosto se il maxPath l'ho mai modificato più che il colore... booooh
			if (color[i] == WHITE){
				maxPath = this.DFSVisit(i, maxPath);
			}
		}	
		
		// ritorno il max in maxPath
		int max=0;
		for (int i=0; i<maxPath.length; i++) {
			if (maxPath[i]>max) {
				max= maxPath[i];
			}
		}
		return max;
	}


	private int[] DFSVisit(int vertex, int[] maxPath ) {
		color[vertex] = GREY;
		for ( int v : this.graph.get(vertex) ){
			
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


	public String toString(MapStringToInt map){
		StringBuffer s = new StringBuffer("digraph G_T {\n");
		for ( int i = 0; i< this.graph.size(); i++){
			s.append(String.format("%d [label=\"%s\"];\n", i, map.getString(i)));
		}
		for ( int i = 0; i< this.graph.size(); i++){
			for ( int j = 0; j< this.graph.get(i).size(); j++){
				s.append(String.format("%d -> %d;\n", i, this.graph.get(i).get(j) ));
			}
		}
		s.append("}");
		return s.toString();
	}
	
	public String toString(){
		StringBuffer s = new StringBuffer();
		for ( int i = 0; i< this.graph.size(); i++){
			s.append(String.format("%d -> ", i));
			for ( int j = 0; j< this.graph.get(i).size(); j++){
				s.append(String.format("%d, ", this.graph.get(i).get(j) ));
			}
			s.append("\n");
		}
		return s.toString();
	}

}
