package main;

public class TestImageProcessing {

	public static void main(String[] args) {

		testGetRed();
		testGetGreen();
		testGetBlue();

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
		
}
