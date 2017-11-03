package main;

public class SimilarityBasedSearch {

	/**
	 * Computes the mean value of a gray-scale image given as a 2D array 
	 * @param image : a 2D double array, the gray-scale Image
	 * @return a double value between 0 and 255 which is the mean value
	 */
	public static double mean(double[][] image) {
		
		assert image.length > 0 && image[0].length > 0;
		
		double mean = 0;
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				mean = mean + image[i][j];
			}
		}
		mean = mean/(image.length*image[0].length);
		return mean; 
	}
	

	/**
	 * Computes the mean value of a gray-scale pattern in an image
	 * @param matrix : a 2D double array, the gray-scale Image
	 * @param row : the y coordinate of the upper left corner of the pattern
	 * @param col : the x coordinate of the upper left corner of the pattern
	 * @param width : the width of the pattern
	 * @param height : the height of the pattern
	 * @return a double value between 0 and 255 which is the mean value
	 */
	static double windowMean(double[][] image, int row, int col, int width, int height) {
		
		assert image.length > 0 && image[0].length > 0;
		
		double avg = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				avg = avg + image[i + row][j + col];
			}
		}
		avg = avg/(width*height);
		return avg;
	}
	
	
	/**
	 * Computes the Normalized Cross Correlation of a gray-scale pattern if positioned
	 * at the provided row, column-coordinate in a gray-scale image
	 * @param row : a integer, the row-coordinate of the upper left corner of the pattern in the image.
	 * @param column : a integer, the column-coordinate of the upper left corner of the pattern in the image.
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of double, the gray-scale image where to look for the pattern
	 * @return a double, the Normalized Cross Correlation value at position (row, col) between the pattern and the part of
	 * the base image that is covered by the pattern, if the pattern is shifted by x and y.
	 * should return -1 if the denominator is 0
	 */
	public static double normalizedCrossCorrelation(int row, int col, double[][] pattern, double[][] image) {
		
		assert pattern.length > 0 && pattern[0].length > 0;
		assert image.length > 0 && image[0].length > 0;
		
		double imageMean = windowMean(image, row, col, pattern.length, pattern[0].length);
		double patternMean = mean(pattern);
		
		double sum = 0;
		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[0].length; j++) {
				sum = sum + (image[i + row][j + col] - imageMean) * (pattern[i][j] - patternMean);
			}
		}
		
		double imageSum = 0;
		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[0].length; j++) {
				imageSum = imageSum + (image[i + row][j + col] - imageMean) * (image[i + row][j + col] - imageMean);
			}
		}
		
		double patternSum = 0;
		for (int i = 0; i < pattern.length; i++) {
			for (int j = 0; j < pattern[0].length; j++) {
				patternSum = patternSum + (pattern[i][j] - patternMean) * (pattern[i][j] - patternMean);
			}
		}
		
		return sum/Math.sqrt(imageSum * patternSum); 
	}

	
	/**
	 * Compute the similarityMatrix between a gray-scale image and a gray-scale pattern
	 * @param pattern : an 2D array of doubles, the gray-scale pattern to find
	 * @param image : an 2D array of doubles, the gray-scale image where to look for the pattern
	 * @return a 2D array of doubles, containing for each pixel of a original gray-scale image, 
	 * the similarity (normalized cross-correlation) between the image's window and the pattern
	 * placed over this pixel (upper-left corner)
	 */
	public static double[][] similarityMatrix(double[][] pattern, double[][] image) {
		
		assert pattern.length > 0 && pattern[0].length > 0;
		assert image.length > 0 && image[0].length > 0;
		assert pattern.length <= image.length && pattern[0].length <= image[0].length;	
		
		double[][] similarities = new double[image.length - pattern.length + 1][image[0].length - pattern[0].length + 1];
		
		for (int i = 0; i < similarities.length; i++) {
			for (int j = 0; j < similarities[i].length; j++) {
				similarities[i][j] = normalizedCrossCorrelation(i, j, pattern, image);
			}
		}
		
		return similarities; 
	}

}
