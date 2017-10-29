package main;

public class TestDistanceBasedSearch {

	public static void main(String[] args) {
		testPixelAbsoluteError();
		testMeanAbsoluteError();
		testDistanceMatrix();
		testHasAtLeastOneElem();
	}
	
	public static void testPixelAbsoluteError() {
		int patternPixel = 0b11110000_00001111_01010101;
		int imagePixel = 0b00001111_01010101_11110000;
		double ref = 150.0;
		
		double error = DistanceBasedSearch.pixelAbsoluteError(patternPixel, imagePixel);
		
		if (error == ref) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test failed. Returned value = " + error + " Expected value = " + ref);
		}
	}
	
	public static void testMeanAbsoluteError() {
		int[][] pattern = {{0b11110000_00001111_01010101, 0b00000001_10000000_00000000}};
		int[][] image = {{0b11110000_00001111_01010101, 0b00000001_10000000_00000000}};
		
		double ref = 0.0;
		double meanError = DistanceBasedSearch.meanAbsoluteError(0, 0, pattern, image);
		
		if (meanError == ref) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test failed. Returned value = " + meanError + " Expected value = " + ref);
		}
	}
	
	public static void testDistanceMatrix() {
		int[][] pattern = {{0b11110000_00001111_01010101}};
		int[][] image = {{0b11110000_00001111_01010101, 0b00000000_00000000_00000001},
						 {0b00000000_00000000_00000001, 0b00000000_00000000_00000001}};
		
		double[][] ref = {{0.0, 113.0},{113.0, 113.0}};
		double[][] distances = DistanceBasedSearch.distanceMatrix(pattern, image);
		
		boolean fail = false;
		
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances.length; j++) {
				if (distances[i][j] != ref[i][j]) {
					System.out.println("Test failed. Returned value = " + distances[i][j] + " Expected value = " + ref[i][j]);
					fail = true;
				}
			}
		}
		
		if (!fail) {
			System.out.println("Test passed");
		}
	}
	
	public static void testHasAtLeastOneElem() {
		int[][] arr1 = {{}};
		int[][] arr2 = {{1}};
		
		if (!DistanceBasedSearch.hasAtLeastOneElem(arr1) && DistanceBasedSearch.hasAtLeastOneElem(arr2)) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test failed");
		}
	}

}






