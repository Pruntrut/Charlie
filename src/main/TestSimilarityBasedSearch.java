package main;

public class TestSimilarityBasedSearch {

	public static void main(String[] args) {
		testMean();
		testWindowMean();
		testNormaizedCrossCorrelation();
		testSimilarityMatrix();
		testSimilarityMatrix("wrap");
		testSimilarityMatrix("mirror");
	}
	
	public static void testMean() {		
		double[][] image = {{1,2,3},{4,5,6}};
		double ref = 3.5;
		double mean = SimilarityBasedSearch.mean(image);
		
		if (mean == ref) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test failed. Returned value = " + mean + " Expected value = " + ref);
		}
	}
	
	public static void testWindowMean() {
		double[][] image = {{1,2,3},{4,5,6},{7,8,9}};
		double ref = 7.0;
		double windowMean = SimilarityBasedSearch.windowMean(image, 1, 1, 2, 2);
		
		if (windowMean != ref) {
			System.out.println("Test failed. Returned value = " + windowMean + " Expected value = " + ref);
		} else {
			System.out.println("Test passed");
		}
	}
	
	public static void testNormaizedCrossCorrelation() {
		double[][] pattern = {{12.0, 85.4}};
		double[][] image = {{0.0, 12.5, 14.2}, 
						 	{0.5, 255.0, 0.0}};
		double ref = 1.0000000000000002;
		double ncc = SimilarityBasedSearch.normalizedCrossCorrelation(0, 1, pattern, image);
		
		if (ncc != ref) {
			System.out.println("Test failed. Returned value = " + ncc + " Expected value = " + ref);
		} else {
			System.out.println("Test passed");
		}
	}
	
	public static void testSimilarityMatrix() {
		testSimilarityMatrix("");
	}
	
	public static void testSimilarityMatrix(String strategy) {
		double[][] pattern = ImageProcessing.toGray(Helper.read("images/onions.png"));
		int[][] image = Helper.read("images/food.png");
		
		double[][] grayImage = ImageProcessing.toGray(image);	
		double[][] similarites;
		
		if (strategy.equals("wrap") || strategy.equals("mirror")) {
			similarites = SimilarityBasedSearch.similarityMatrix(pattern, grayImage, strategy);
		} else {
			similarites = SimilarityBasedSearch.similarityMatrix(pattern, grayImage);
		}
	
		int[] best = Collector.findBest(similarites, false);   
		int[] ref = {456,39};
		
		boolean fail = false;
		
		for (int i = 0; i < best.length; i++) {
			if (best[i] != ref[i]) {
				fail = true;
				System.out.println("Test failed.Returned value = " + best[i] + " Expected value = " + ref[i]);
			}
		}
		
		if (!fail) {
			System.out.println("Test passed");
		}
	}

}
