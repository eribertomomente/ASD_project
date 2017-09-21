package graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private ArrayList<ArrayList<Integer>> graph;

	// EXTRA ATTRIBUTES:
	private int[] color;			// keep the "status" of each vertex
	private int[] pred;			// <pred[v]> is the predecessor of <v> in the path from <node> to <v>
	private int[] distance;	// distance[v] is the number of edges from <node> to <v>
	private int[] startTime;	// keep the time when each vertex begins to be visited
	private int[] endTime;	// keep the time when each vertex ends to be visited
	private boolean isCyclical = false;	// TRUE if the graph if cyclical, FALSE otherwise

	// CONSTANTS:	
	private static final int WHITE = 0;
	private static final int GREY = 1;
	private static final int BLACK = 2;
	private static final int INFINITY = -1;
	private static final int NONE = -1;

	// CONSTRUCTOR:	
	public Graph(){
		graph= new ArrayList<ArrayList<Integer>>();
	}

	// GETTERS:

	public int[] getPred() { return pred; }

	public int[] getDistance() { return distance; }

	public int[] getStartTime() { return startTime; }

	public int[] getEndTime() { return endTime; }

	public boolean isCyclical() { 	return isCyclical; }


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
	 * 	BREADTH FIRST SEARCH
	 * @param node starting vertex
	 * @throws VertexNotInGraphException if <node> does not belong to this
	 */
	public void BFS( int node ) throws VertexNotInGraphException{

		if (this.graph.size() <= node) {
			throw new VertexNotInGraphException( "The vertex " + node + "does not belong to the current graph" );
		}

		int verticesNum = this.graph.size();
		color = new int[verticesNum];		
		pred = new int[verticesNum];		
		distance = new int[verticesNum];	
		List<Integer> queue = new ArrayList<Integer>();					// contain the vertices still to be analyzed

		for (int i = 0; i < verticesNum; i++){
			color[i] = WHITE;
			pred[i] = NONE;
			distance[i] = INFINITY;
		}

		color[node]= GREY;
		distance[node]=0;
		queue.add(node);
		while ( ! queue.isEmpty() ){
			int vertex = (int) queue.get(0);
			for ( int v : this.graph.get(vertex) ){
				if (color[v] == WHITE){
					color[v] = GREY;
					distance[v] = distance[vertex] +1;
					pred[v] = vertex;
					queue.add(v);
				}
			}
			queue.remove(0);
			color[vertex]=BLACK;
		}
	}


	/**
	 * Print vertices in the minimum path from <s> to <v>
	 * @param s starting vertex
	 * @param v destinational vertex
	 * @return the min path from <s> to <v>
	 * @throws VertexNotInGraphException if <v> or <s> does not belong to this
	 */
	public List<Integer> minPath (int s, int v) throws VertexNotInGraphException{
		if (this.graph.size() <= s) {
			throw new VertexNotInGraphException( "The vertex " + s + "does not belong to the current graph" );
		}
		if (this.graph.size() <= v) {
			throw new VertexNotInGraphException( "The vertex " + v + "does not belong to the current graph" );
		}
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(v);
		this.BFS(s);
		int[] predecessors = this.getPred();
		int current = predecessors[v];
		while (current != s) {
			path.add(current);
			current=predecessors[current];
		}
		path.add(s);
		return path;
	}


	/**
	 * @param v starting vertex
	 * @return the farthest vertex from <v>
	 * @throws VertexNotInGraphException if <v> does not belong to this
	 */
	public int farthestVertex (int v) throws VertexNotInGraphException{
		if (this.graph.size() <= v) {
			throw new VertexNotInGraphException( "The vertex " + v + "does not belong to the current graph" );
		}
		this.BFS(v);
		int max=0;
		for  (int i = 0; i< this.getDistance().length; i++){
			if (this.getDistance()[i] > this.getDistance()[max]){
				max = i;
			}
		}
		return max;
	}


	/**
	 * @param v starting vertex
	 * @return the distance from <v> to the farthest vertex 
	 * @throws VertexNotInGraphException if <v> does not belong to this
	 */
	public int maxDistance (int v) throws VertexNotInGraphException{
		if (this.graph.size() <= v) {
			throw new VertexNotInGraphException( "The vertex " + v + "does not belong to the current graph" );
		}
		this.BFS(v);
		int max=0;
		for  (int i = 0; i< this.getDistance().length; i++){
			if ( this.getDistance()[i] > max ){
				max = this.getDistance()[i];
			}
		}
		return max;
	}


	/**
	 * @return the max distance between two vertices in this
	 * @throws VertexNotInGraphException // TODO ???????????????
	 */
	public int diameter () throws VertexNotInGraphException{	// TODO � inutile perch� in questa procedura ho gi� blindato il codice
		if (this.graph == null){
			return 0;
		} else {
			int farthest = this.farthestVertex(0);	// 0 is just a random vertex but we are totally sure that this vertex exists
			int diam= this.maxDistance(farthest);
			return diam;
		}
	}


	/**
	 * DEEP FIRST SEARCH
	 */
	public void DFS(){
		int verticesNum = this.graph.size();
		color = new int[verticesNum];		
		pred = new int[verticesNum];
		startTime = new int[verticesNum];		
		endTime = new int[verticesNum];	

		for ( int i=0; i< this.graph.size(); i++){
			color[i] = WHITE;
			pred[i] = NONE;
		}    	
		int time = 0;    	
		for ( int i=0; i< this.graph.size(); i++){
			if (color[i] == WHITE){
				time = this.DFSVisit(i, time);
			}
		}		
	}


	private int DFSVisit(int vertex, int time) {
		color[vertex] = GREY;
		time++;
		startTime[vertex] = time;
		for ( int v : this.graph.get(vertex) ){
			if ( color[v] == WHITE ){
				pred[v] = vertex;
				time = this.DFSVisit( v, time);
			} else if( color[v] == GREY ){
				isCyclical = true;
			}
		}
		color[vertex] = BLACK;
		time++;
		endTime[vertex] = time;
		return time;
	}


	public String toString(){
		StringBuffer s = new StringBuffer();
		for ( int i = 0; i< this.graph.size(); i++){
			s.append(String.format("[%d] --> ", i));
			for ( int j = 0; j< this.graph.get(i).size(); j++){
				s.append(String.format("%d -> ", this.graph.get(i).get(j)));
			}
			s.delete(s.length()-4, s.length());
			s.append("\n");
		}
		return s.toString();
	}


}
