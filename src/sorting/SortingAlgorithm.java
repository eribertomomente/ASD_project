package sorting;

import java.io.*;
import java.util.*;

public class SortingAlgorithm {
	 
	    // A utility function to get maximum value in arr[]
	    static int getMax(int arr[], int n)
	    {
	        int mx = arr[0];
	        for (int i = 1; i < n; i++)
	            if (arr[i] > mx)
	                mx = arr[i];
	        return mx;
	    }
	 
	    // A function to do counting sort of arr[] according to
	    // the digit represented by exp.
	    static void countSort(String a[], int n, int digit)
	    {
	    		int wordLength = a[0].length();
	        String output[] = new String[n]; // output array
	        int count[] = new int[256];
	        
	        for (int i=0; i<256; i++) {
	        		count[i]=0;
	        }
	 
	        // Store count of occurrences in count[]
	        for (int i = 0; i < n; i++) {
	            count[ (int) a[i].charAt(wordLength-digit-1) ]++;
	        }
	 
	        // Change count[i] so that count[i] now contains
	        // actual position of this digit in output[]
	        for (int i = 1; i < 256; i++)
	            count[i] += count[i - 1];
	 
	        // Build the output array
	        for (int i = n - 1; i >= 0; i--)
	        {
	        		int c1 = (int) 	a[i].charAt(wordLength-digit-1);
	        		int c2 = count[ (int) a[i].charAt(wordLength-digit-1) ];
	            output[count[ (int) a[i].charAt(wordLength-digit-1) ]-1] = a[i];
	            count[ (int) a[i].charAt(wordLength-digit-1) ]--;
	        }
	 
	        // Copy the output array to a[], so that a[] now
	        // contains sorted numbers according to current digit
	        for (int i = 0; i < n; i++)
	            a[i] = output[i];
	    }
	 
	    // The main function to that sorts a[] of size n using
	    // Radix Sort
	    static void radixsort(String a[], int n)
	    {
	        // Find the maximum number to know number of digits
	        int m = a[0].length();
	 
	        // Do counting sort for every digit. Note that instead
	        // of passing digit number, exp is passed. exp is 10^i
	        // where i is current digit number
	        // TODO check < or <=
	        for (int i = 0; i < m; i++)
	            countSort(a, n, i);
	    }
	 
	    // A utility function to print an array
	    static void print(String arr[], int n)
	    {
	        for (int i=0; i<n; i++)
	            System.out.print(arr[i]+" ");
	    }
	 
	 
	    public static void main (String[] args)
	    {
	        String arr[] = {"ciao", "come", "stai", "bene", "tuuu"};
	        int n = arr.length;
	        radixsort(arr, n);
	        print(arr, n);
	    }

}
