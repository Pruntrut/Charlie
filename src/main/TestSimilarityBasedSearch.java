package main;

public class TestSimilarityBasedSearch {

	public static void main(String[] args) {
		testMean();
		testWindowMean();
		testNormaizedCrossCorrelation();
		testNCCPatternEqualImage();
		testSimilarityPatternEqualImage();
		testSimilaritySimple();
		testMirrorIndex();
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
	
	public static void testNCCPatternEqualImage() {     
	  double[][] pattern = {{ 0,   0, 0 },
	                       { 0, 255, 0 },
	                       { 0,   0, 0 }};
	  double similarity = SimilarityBasedSearch.normalizedCrossCorrelation(0, 0, pattern, pattern);
	  if (similarity == 1.0) {
	    System.out.println("Test passed");      
	  } else {
	    System.out.println("Test failed. Returned value = " + similarity + " Expected value = 1.0");
	  }
	}
	
	public static void testSimilarityPatternEqualImage() {     
	  double[][] pattern = {{ 0, 255}};
	  double[][] similarity = SimilarityBasedSearch.similarityMatrix(pattern, pattern);
	  if (similarity.length == 1) {
	    if (similarity[0][0] == 1.0) {
	    	System.out.println("Test passed");  
	    } else {
	      System.out.println("Test failed. Expected value 1.0 but was " + similarity[0][0]);       
	    }
	  } else {
	    System.out.println("Test failed. Expected length 1 but was " + similarity.length);       
	  }
	}
	
	public static void testSimilaritySimple() {
	  double[][] image = {{ 3, 2, 2, 2 },
	                      { 0, 3, 0, 0 }};
	  double[][] pattern = {{ 0, 3, 0}};
	  double[][] similarity = SimilarityBasedSearch.similarityMatrix(pattern, image);
	 
	  if (similarity.length == 2 && similarity[0].length == 2) {
	    if (similarity[0][0] == -0.5 && similarity[0][1] == -1.0 &&
	        similarity[1][0] ==  1.0 && similarity[1][1] == -0.5) {
	    	System.out.println("Test passed");  
	    } else {
	      System.out.println("Test failed. Wrong values");  
	    }
	  } else {
	    System.out.println("Test failed. Incorrect size");       
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
	
	public static void testMirrorIndex() {
		int index1 = 5;
		int index2 = 0;
		int result1 = SimilarityBasedSearch.mirrorIndex(index1, 4);
		int result2 = SimilarityBasedSearch.mirrorIndex(index2, 4);
		
		if (result1 == 1 && result2 == 0) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test failed. Returned values = {" + result1 + ", " + result2 + "} Expected values = {1, 0}");
		}
	}

}
