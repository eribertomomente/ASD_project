package graphs;

@SuppressWarnings("serial")
public class FullGraphException extends Exception{
	
	public FullGraphException(){	
	super("The graph is full. Max vertices number of the current graph has been reached.");
	}

}
