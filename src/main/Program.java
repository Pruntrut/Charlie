package main;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] pattern = Helper.read("Pattern.png");
		int[][] array = Helper.read("Image.png");
		double[][] distance = DistanceBasedSearch.distanceMatrix(pattern, array);
		int[] coords = Collector.findBest(distance, true);
		//Helper.drawBox(coords[1], coords[2], w, h, dst);
		//Helper.show(array, title);
	}

}
