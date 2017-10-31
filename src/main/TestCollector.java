package main;

public class TestCollector {
	
	public static void main(String[] args) {
		testFindBest(true);
		testFindBest(false);
	}
	
	private static void testFindBest(boolean smallestFirst) {
		double[][] matrix = {{0.001, 3.2, -9.11}, {0, 11.1, -1234}, {1234, 15, 0.1}};
		
		int[] extreme = Collector.findBest(matrix, smallestFirst);		
		int[] ref = new int[2];
		
		if (smallestFirst) {
			ref[0] = 1;
			ref[1] = 2;
		} else {
			ref[0] = 2;
			ref[1] = 0;
		}
		
		boolean fail = false;
		
		for (int i = 0; i < extreme.length; i++) {
			if (extreme[i] != ref[i]) {
				System.out.println("Test failed. Returned value = " + extreme[i] + " Expected value = " + ref[i]);		
				fail = true;
			}
		}
		
		if (!fail) {
			System.out.println("Test passed");
		}
	}

}
