package main;

public class Program {
	public static void main(String args[]) {
    	String charliePath = "images/charlie.png";
    	String beachPath = "images/charlie_beach.png";
    	
    	findWithDistance(charliePath, beachPath);
    	findWithSimilarity(charliePath, beachPath);
    	
    	compareDistanceAndSimilarity();
	}
	
	public static void findWithDistance(String patternPath, String imagePath) {
		System.out.println("Finding " + patternPath + " using distance based seach");
    	int[][] pattern = Helper.read(patternPath);
    	int[][] image = Helper.read(imagePath);
    	
    	double[][] distance = DistanceBasedSearch.distanceMatrix(pattern, image); 
    	int[] coords = Collector.findBest(distance, true);
    	
    	System.out.println("Done!");
    	showImageWithBox(image, pattern, coords, "Found with Distance");
   	}
	
	public static void findWithSimilarity(String patternPath, String imagePath) {
		System.out.println("Finding "+ patternPath +" using similarity based search");
		int[][] pattern = Helper.read(patternPath);
    	int[][] image = Helper.read(imagePath);
		double[][] patternGrey = ImageProcessing.toGray(pattern);
		double[][] imageGrey = ImageProcessing.toGray(image);
		
		double[][] similarity = SimilarityBasedSearch.similarityMatrix(patternGrey, imageGrey);
		int[] coords = Collector.findBest(similarity, false);
		
		System.out.println("Done!");
		showImageWithBox(image, pattern, coords, "Found with Similarity");
	}
	
	public static void showImageWithBox(int[][] image, int[][] pattern,int[] coords, String message) {
		Helper.drawBox(coords[0], coords[1], pattern[0].length, pattern.length, image);
		Helper.show(image, message);
	}
	
	public static void compareDistanceAndSimilarity() {
		System.out.println("Compare Distance and Similarity based search");
		int[][] image = Helper.read("images/image-dark.png");
		int[][] pattern = Helper.read("images/pattern.png");
		double[][] imageGray = ImageProcessing.toGray(image);
		double[][] patternGray = ImageProcessing.toGray(pattern);
		
		double[][] distance = DistanceBasedSearch.distanceMatrix(pattern, image);
		double[][] similarity = SimilarityBasedSearch.similarityMatrix(patternGray, imageGray);
		
		int[] bestDist = Collector.findBest(distance, true);
		int[] bestSim = Collector.findBest(similarity, false);

		Helper.drawBox(bestDist[0], bestDist[1], pattern[0].length, pattern.length, image);    	
    	Helper.drawBox(bestSim[0], bestSim[1], pattern[0].length, pattern.length, image, pattern[0].length/15, 0x00ff00);;
    	Helper.show(image, "Compare Dist (red) and Sim (green)");
    	
    	System.out.println("Done!");
    }
}