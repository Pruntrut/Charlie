package main;

public class TestImageProcessing {

	public static void main(String[] args) {
		
		testGetRed();
		testGetGreen();
		testGetBlue();
		testGetRGBArray();
		testOutOfLimit();
		testGetGray();
		testGetRGB();
		testGetRGB2();
		testToGray();
		testToRGB();

	}

	public static void testGetRed() {
		
		int color = 0b11110000_00001111_01010101;
    	int ref = 0b11110000;
    	int red = ImageProcessing.getRed(color);
    	if (red == ref) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + red + " Expected value = " + ref);
    	}
    	
	}
	
	public static void testGetGreen() {
		
		int color = 0b11110000_00001111_01010101;
    	int ref = 0b00001111;
    	int green = ImageProcessing.getGreen(color);
    	if (green == ref) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + green + " Expected value = " + ref);
    	}
    	
	}
	
	public static void testGetBlue() {
		
		int color = 0b11110000_00001111_01010101;
    	int ref = 0b01010101;
    	int blue = ImageProcessing.getBlue(color);
    	if (blue == ref) {
    		System.out.println("Test passed");
    	} else {
    		System.out.println("Test failed. Returned value = " + blue + " Expected value = " + ref);
    	}
    	
	}
	
	public static void testGetRGBArray() {
		
		int color = 0b11110000_00001111_01010101;
		int[] ref = {0b11110000, 0b00001111, 0b01010101};
		int[] rgbArray = ImageProcessing.getRGBArray(color);
		
		boolean fail = false;
		
		for (int i = 0; i < rgbArray.length; i++) {
			if (rgbArray[i] != ref[i]) {
				System.out.println("Test failed. Returned value = " + rgbArray[i] + " Expected value = " + ref[i]);
				fail = true;
			}
		}
		
		if (!fail) {
			System.out.println("Test passed");
		}
	}
	
	public static void testGetGray() {
		
		int color = 0b11110000_00001111_0101010;
		double ref = 99.0;
		double gray = ImageProcessing.getGray(color);
		
		if (gray == ref) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test failed. Returned value = " + gray + " Expected value = " + ref);
		}
	}
	
	public static void testOutOfLimit() {
		
		int[] numbers = {0, 255, -1,256, -12, 278, 18};
		int[] ref = {0, 255, 0, 255, 0, 255, 18};
		
		boolean fail = false;
		
		for (int i = 0; i < numbers.length; i++) {
			int bounded = ImageProcessing.outOfLimit(numbers[i]);
			
			if (bounded != ref[i]) {
				System.out.println("Test failed. Returned value = " + bounded + " Expected value = " + ref[i]);
				fail = true;
			}
		}
		
		if (!fail) {
			System.out.println("Test passed");
		}
		
	} 
	
	public static void testGetRGB() {
		
		int red = 0b11110000;
		int green = 0b00001111;
		int blue = 0b01010101;
		int ref = 0b11110000_00001111_01010101;
		
		int rgb = ImageProcessing.getRGB(red, green, blue);
		
		if (rgb == ref) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test failed. Returned value = " + rgb + " Expected value = " + ref);
		}
		
	}
	
	public static void testGetRGB2() {
		
		double gray = 99.0;
		int ref = 0b11000110110001101100011;
		
		int rgb = ImageProcessing.getRGB(gray);
		
		if (rgb != ref) {
			System.out.println("Test failed. Returned value = " + rgb + " Expected value = " + ref);
		} else {
			System.out.println("Test passed");
		}
		
	}
	
	public static void testToGray() {
		
		int[][] rgb = { {0b11110000_00001111_0101010, 0b11110000_00001111_0101010}, 
							 {0b11110000_00001111_0101010, 0b11110000_00001111_0101010} };
		double[][] ref = { {99.0, 99.0}, {99.0, 99.0}};
		double[][] grays = ImageProcessing.toGray(rgb);
		
		boolean fail = false;
		
		for (int i = 0; i < grays.length; i++) {
			for (int j = 0; j < grays[i].length; j++) {
				if (ref[i][j] != grays[i][j]) {
					fail = true;
					System.out.println("Test failed. Returned value = " + grays[i][j] + " Expected value = " + ref[i][j]);
				}
			}
		}
		
		if (!fail) {
			System.out.println("Test passed");
		}
		
	}
	
	public static void testToRGB() {
		double[][] grays = { {99.0, 99.0}, {99.0, 99.0}};
		int[][] ref = { {0b11000110110001101100011, 0b11000110110001101100011}, 
				 {0b11000110110001101100011, 0b11000110110001101100011} };
		
		int[][] rgb = ImageProcessing.toRGB(grays);

		boolean fail = false;

		for (int i = 0; i < rgb.length; i++) {
			for (int j = 0; j < rgb[i].length; j++) {
				if (ref[i][j] != rgb[i][j]) {
					fail = true;
					System.out.println("Test failed. Returned value = " + rgb[i][j] + " Expected value = " + ref[i][j]);
				}
			}
		}

		if (!fail) {
			System.out.println("Test passed");
		}
		
	}
		
}





















