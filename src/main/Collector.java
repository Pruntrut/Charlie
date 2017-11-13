package main;

import java.util.ArrayList;

public class Collector {

	/**
	 * Find the row, column coordinates of the best element (biggest or smallest) for the given matrix
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean, indicates if the smallest element is the best or not (biggest is then the best)
	 * @return an array of two integer coordinates, row first and then column
	 */
	public static int[] findBest(double[][] matrix, boolean smallestFirst) {
		assert matrix.length > 0 && matrix[0].length > 0;
		
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		int[] minCoords = new int[2];
		int[] maxCoords = new int[2];
		
		
		for (int i = 0; i  < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				double elem = matrix[i][j];
				
				if (elem  < min) {
					min = elem;
					minCoords[0] = i;
					minCoords[1] = j;
				} 
				
				if (elem > max) {
					max = elem;
					maxCoords[0] = i;
					maxCoords[1] = j;
				}
			}
		}
		
		if (smallestFirst) {
			return minCoords;
		} else {
			return maxCoords;
		}
		
	}

	
	/**
	 * Find the row, column coordinate-pairs of the n best (biggest or smallest) elements of the given matrix
	 * @param n : an integer, the number of best elements we want to find 
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean,  indicates if the smallest element is the best or not (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBest(int n, double[][] matrix, boolean smallestFirst) {
		assert n <= matrix.length * matrix[0].length;
		
		assert matrix.length > 0 && matrix[0].length > 0;
		
		double[][] copy = copyMatrix(matrix);
		double extreme;
		int[][] bestArray = new int[n][];
		
		if (smallestFirst) {
			extreme = Double.POSITIVE_INFINITY;
		} else {
			extreme = Double.NEGATIVE_INFINITY;
		}
		
		for (int i = 0; i < n; i++) {
			int[] best = findBest(copy, smallestFirst);
			bestArray[i] = best;
			copy[best[0]][best[1]] = extreme;
		}
		
		
		return bestArray;
	}
	
	

	/**
	 * BONUS 
	 * Notice : Bonus points are underpriced ! 
	 * 
	 * Sorts all the row, column coordinates based on their pixel value
	 * Hint : Use recursion !
	 * @param matrix : an 2D array of doubles
	 * @return A list of points, each point is an array of length 2.
	 */
	public static ArrayList<int[]> quicksortPixelCoordinates(double[][] matrix) {

		ArrayList<int[]> arrayList = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				int[] coords = {i, j};
				arrayList.add(coords);
			}
		}
		
		return quicksort(arrayList, matrix);
	}
	
	/** Helper function for quicksortPixelCoordinates
	 * @param array
	 * @param reference
	 * @return Sorted array
	 */
	private static ArrayList<int[]> quicksort(ArrayList<int[]> array, double[][] reference) {
		
		if (array.size() <= 1) {
			return array;
		} 
		
		int[] pivot = array.get(0);
		ArrayList<int[]> smaller = new ArrayList<>();
		ArrayList<int[]> larger = new ArrayList<>();
		ArrayList<int[]> equal = new ArrayList<>();

		
		for (int[] item : array) {
			double itemValue = reference[item[0]][item[1]];
			double pivotValue = reference[pivot[0]][pivot[1]];
			
			if (itemValue > pivotValue) {
				larger.add(item);
			} else if (itemValue < pivotValue) {
				smaller.add(item);
			} else {
				equal.add(item);
			}
		}
		
		ArrayList<int[]> sortedSmaller = quicksort(smaller, reference);
		ArrayList<int[]> sortedLarger = quicksort(larger, reference);
		ArrayList<int[]> sortedEqual = quicksort(equal, reference);
		
		sortedEqual.addAll(sortedLarger);
		sortedSmaller.addAll(sortedEqual);
		
		return sortedSmaller;
	}

	
	/**
	 * BONUS
	 * Notice : Bonus points are underpriced !
	 * 
	 * Use a quick sort to find the row, column coordinate-pairs of the n best (biggest or smallest) elements of the given matrix
	 * Hint : return the n first or n last elements of a sorted ArrayList  
	 * @param n : an integer, the number of best elements we want to find 
	 * @param matrix : an 2D array of doubles
	 * @param smallestFirst : a boolean, indicate if the smallest element is the best or not (biggest is the best)
	 * @return an array of size n containing row, column-coordinate pairs
	 */
	public static int[][] findNBestQuickSort(int n, double[][] matrix, boolean smallestFirst) {

		assert n <= matrix.length * matrix[0].length;
		
		ArrayList<int[]> sorted = quicksortPixelCoordinates(matrix);
		int[][] nBest = new int[n][];
		
		for (int i = 0; i < n; i++) {
			if (smallestFirst) {
				nBest[i] = sorted.get(i);
			} else {
				nBest[n-i-1] = sorted.get(sorted.size() - n + i);
			}
		}

		return nBest;
	}
	
	/**
	 * Deep copies a 2D Matrix
	 * @param matrix
	 * @return A deep copy of matrix
	 */
	public static double[][] copyMatrix(double[][] matrix) {
		double[][] copy = new double[matrix.length][];
		
		for (int i = 0; i < matrix.length; i++) {
			copy[i] = new double[matrix[i].length];
			
			for (int j = 0; j < matrix[i].length; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		
		return copy;
		
	}
}
