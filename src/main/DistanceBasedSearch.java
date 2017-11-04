package main;

public class DistanceBasedSearch {

	/**
	 * Computes the mean absolute error between two RGB pixels, channel by channel.
	 * @param patternPixel : a integer, the second RGB pixel.
	 * @param imagePixel : a integer, the first RGB pixel.
	 * @return a double, the value of the error for the RGB pixel pair. (an integer in [0, 255])
	 */
	public static double pixelAbsoluteError(int patternPixel, int imagePixel) {
		int[] patternRGBArray = ImageProcessing.getRGBArray(patternPixel);
		int[] imageRGBArray = ImageProcessing.getRGBArray(imagePixel);
		
		double sum = 0.0;
		
		for (int i = 0; i < patternRGBArray.length; i++) {
			sum += Math.abs(patternRGBArray[i] - imageRGBArray[i]);
		}
		
		return (double)sum/(double)patternRGBArray.length;
	}

	/**
	 * Computes the mean absolute error loss of a RGB pattern if positioned
	 * at the provided row, column-coordinates in a RGB image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a double, the mean absolute error
	 * @return a double, mean absolute error value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is -1
	 */
	public static double meanAbsoluteError(int row, int col, int[][] pattern, int[][] image) {
		
		assert hasAtLeastOneElem(pattern) && hasAtLeastOneElem(image);
		assert pattern.length <= image.length && pattern[0].length <= image[0].length;
		
		double sum = 0.0;
		int pixelNumber = 0;
		
		for (int i = 0; i < pattern.length; i++) {
			for(int j = 0; j < pattern[i].length; j++) {
				sum += pixelAbsoluteError(pattern[i][j], image[row + i][col + j]);
				
				pixelNumber++;
			}
		}
		
		assert pixelNumber > 0;
    	
		return (1/(double)pixelNumber) * sum;
		
	}

	/**
	 * Compute the distanceMatrix between a RGB image and a RGB pattern
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original RGB image, 
	 * the distance (meanAbsoluteError) between the image's window and the pattern
	 * placed over this pixel (upper-left corner) 
	 */
	public static double[][] distanceMatrix(int[][] pattern, int[][] image) {
		
		assert hasAtLeastOneElem(pattern) && hasAtLeastOneElem(image);
		assert pattern.length <= image.length && pattern[0].length <= image[0].length;
		
		double[][] distances = new double[image.length - pattern.length + 1][image[0].length - pattern[0].length + 1];
		
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances[i].length; j++) {
				distances[i][j] = meanAbsoluteError(i, j, pattern, image);
			}
		}
    	
		return distances;
	}
	
	/**
	 * Compute the distanceMatrix between a RGB image and a RGB pattern according to a
	 * specfic strategy: wrapping or mirroring
	 * @param pattern : an 2D array of integers, the RGB pattern to find
	 * @param image : an 2D array of integers, the RGB image where to look for the pattern
	 * @param statergy: a String, if equal to "wrap" or "mirror", executes those strategy, uses
	 * default method otherwise.
	 * @return a 2D array of doubles, containing for each pixel of a original RGB image, 
	 * the distance (meanAbsoluteError) between the image's window and the pattern
	 * placed over this pixel (upper-left corner) 
	 */
	public static double[][] distanceMatrix(int[][] pattern, int[][] image, String strategy) {
		
		assert hasAtLeastOneElem(pattern) && hasAtLeastOneElem(image);
		
		if (!strategy.equals("wrap") && !strategy.equals("mirror")) {
			return distanceMatrix(pattern, image);
		}
		
		double[][] distances = new double[image.length][image[0].length];
		
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances[i].length; j++) {
				int row, col;
				
				if (strategy.equals("wrap")) {
					row = i % distances.length;
					col = j % distances[i].length;
				} else { // if strategy == "mirror"
					row = distances.length - 2 - (i % distances.length);
					col = distances.length - 2 - (j % distances[i].length);
				}
				
				distances[i][j] = meanAbsoluteError(row, col, pattern, image);
			}
		}
    	
		return distances;
		
	}
	
	/**
	 * Returns true if given 2D array has at least one element
	 * @param array : a 2D integer array
	 * @return a boolean
	 */
	public static boolean hasAtLeastOneElem(int[][] array) {
		return array.length >= 1 && array[0].length >= 1;
	}
}






















