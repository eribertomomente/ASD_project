package input_computation;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class IOTest {

	public static void main(String[] args) throws IOException {
		/*
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		StringBuffer s = new StringBuffer( in.readLine() );
		while (in.ready()) { 
			  s.append( in.readLine() ); 
			}
		System.out.println("fatto");
		System.out.println(s.toString());
		*/
		StringBuffer s = new StringBuffer();
		 Scanner stdin = new Scanner(new BufferedInputStream(System.in));
	        while (stdin.hasNext()) {
	        	s.append(" "+stdin.nextLine());
	        }
	        stdin.close();
	        System.out.println( s );
	
		
		
		
		//usare il string tokenizer
		
		
		
		/*
	    Scanner scanner = new Scanner(System.in);

	    //  prompt for the user's name
	    System.out.print("Enter your name: ");

	    // get their input as a String
	    String username = scanner.next();

	    // prompt for their age
	    System.out.print("Enter your age: ");

	    // get the age as an int
	    int age = scanner.nextInt();

	    System.out.println(String.format("%s, your age is %d", username, age));

*/
		
		
	}

}
