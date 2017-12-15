package graphs;

public class GraphTest extends Graph {
	
	

	public static void main(String[] args) throws VertexNotInGraphException {
		/*
		Graph test = new Graph();
		
		for (int i = 0; i < 7; i++){
			test.addVertex();
		}
		
		test.setEdge(0, 6);
		test.setEdge(0, 5);
		test.setEdge(0, 4);
		test.setEdge(2, 1);
		test.setEdge(3,1);
		test.setEdge(4,3);
		test.setEdge(5,4);
		test.setEdge(5,2);
		test.setEdge(6,3);
		
		String g = test.toString();
		System.out.println(g);
		test.BFS(0);
		
		System.out.print("distance: \t ");
		for (int i = 0; i< test.size(); i ++){
		System.out.print( " " + test.getDistance()[i] );
		}
		System.out.print("\npredecessors: \t");
		for (int i = 0; i< test.size(); i ++){
			System.out.print( " " + test.getPred()[i] );
			}
		System.out.println();
		System.out.println( test.minPath(0, 3).toString()+ "\n" );

		System.out.println( "il nodo pi� lontano dal nodo 0 �: " + test.farthestVertex(0) );
		System.out.println( "il nodo pi� lontano da 0 � a distanza: " + test.maxDistance(0) + "\n" );		
		*/
		//System.out.println("diametro: " + test.diameter() + "\n");
		
		Graph tree = new Graph();
		for (int i = 0; i < 11; i++){
			tree.addVertex();
		}
		tree.setEdge( 0,10 );
		tree.setEdge( 0,8 );
		tree.setEdge( 0,5 );
		tree.setEdge( 1,5 );
		tree.setEdge( 2,5 );
		tree.setEdge( 2,3);
		tree.setEdge( 3,2 );
		tree.setEdge( 4,5 );
		tree.setEdge( 5,1 );
		tree.setEdge( 5,2 );
		tree.setEdge( 5,4 );
		tree.setEdge( 5, 0 );
		tree.setEdge( 6,8 );
		tree.setEdge( 7,8 );
		tree.setEdge( 8,7 );
		tree.setEdge( 8,6 );
		tree.setEdge( 8,0 );
		tree.setEdge( 9,10 );
		tree.setEdge( 10,9 );
		tree.setEdge( 10,0 );
		System.out.println(tree.toString() );
		
		//System.out.println("diametro: " + tree.diameter() + "\n");
		
		Graph t = new Graph();
		for (int i = 0; i < 6; i++){
			t.addVertex();
		}
		t.setEdge( 0,1 );
		t.setEdge( 0,2 );
		t.setEdge( 0,3 );
		t.setEdge( 0,4 );
		t.setEdge( 0,5 );
		t.setEdge( 1,2);
		t.setEdge( 1,3 );
		t.setEdge(  1,4);
		t.setEdge( 1,5 );
		t.setEdge( 2,3 );
		t.setEdge( 4,5 );
		System.out.println(t.toString() );
		
		//System.out.println("diametro: " + t.diameter() + "\n");
		
		
		/*
		System.out.println("\n DFS: \n");
		
		Graph cycle = new Graph();
		for (int i = 0; i < 5; i++){
			cycle.addVertex();
		}
		cycle.setEdge( 0,1 );
		cycle.setEdge( 0,2 );
		cycle.setEdge( 0,3 );
		cycle.setEdge( 1,2);
		cycle.setEdge( 3,1 );
		cycle.setEdge( 3,4);
		cycle.setEdge( 4,0 );
		System.out.println(cycle.toString() );
		
		cycle.DFS();
		System.out.print("Pred:\t\t");
		for (int i = 0; i< cycle.size(); i ++){
			System.out.print( "\t" + cycle.getPred()[i] );
		}
		System.out.print("\nStart time:\t");
		for (int i = 0; i< cycle.size(); i ++){
			System.out.print( "\t" + cycle.getStartTime()[i] );
		}
		System.out.print("\nEnd time:\t");
		for (int i = 0; i< cycle.size(); i ++){
			System.out.print( "\t" + cycle.getEndTime()[i] );
		}
		System.out.println("\n");
		
		System.out.println( "il grafo � ciclico? " + cycle.isCyclical() );
		
		*/
		

	}

}
