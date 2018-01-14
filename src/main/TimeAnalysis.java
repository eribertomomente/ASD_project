package main;

import analytics.*;

public class TimeAnalysis {
	
	public static void mainTimeAnalysis (int startingInputLength, int iterations, int increment) {
		
		/* I test sono stati effettuati con i seguenti valori: */
		//		startingInputLength = 1000;
		//		iterations = 50;
		//		increment = 1000;
		
		TimeAnalysis_Project.timeAnalysis(startingInputLength, iterations, increment);
		TimeAnalysis_InputManager.timeAnalysis(startingInputLength, iterations, increment);		
		TimeAnalysis_GraphManager.timeAnalysis(startingInputLength, iterations, increment);

	}
	
	public static void main(String[] args) {
			int startingInputLength = Integer.parseInt(args[0]);
			int iterations = Integer.parseInt(args[1]);
			int increment = Integer.parseInt(args[2]);
			mainTimeAnalysis(startingInputLength, iterations, increment);
	}
}
