package data_structure;

public class MyStringTokenizer {
	
	/**
	 * mutabile
	 */
	
	private String[] buffer;
	
	public MyStringTokenizer(String str) {
		int count=1;
		for (int i = 0; i <str.length(); i++) {
			if (str.charAt(i)==' ') { 	count++; }
		}
		
		this.buffer= new String[count];
		
		for (int i = 0; i<count; i++) {
			StringBuffer s = new StringBuffer();
			int j=0;
			while
		}
		this.buffer
	}
	
	public  boolean hasMoreTokens() {
		if (this.buffer.length()>0){
			return true;
		}
		return false;
	}
	
	public String nextToken() {
		for (int i=0; i < this.buffer.length(); i++) {
			while (this.buffer)
		}
		this.buffer.de
	}
	
	

}
