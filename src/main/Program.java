package main;

public class Program {
	public static void main(String args[]) {
		int[][] food = Helper.read("images/charlie_beach.png");
    	int[][] onions = Helper.read("images/charlie.png");
    	double[][] distance = DistanceBasedSearch.distanceMatrix(onions, food); 	
    	Helper.show(ImageProcessing.matrixToRGBImage(distance , 0, 255),
    			"Distance");
    	int[] p = Collector.findBest(distance, true);
    	Helper.drawBox(p[0], p[1], onions[0].length, onions.length, food);
    	Helper.show(food, "Found!");
	}
}