package ImageProcessing_Pack;

/*
 * Obiect pentru stocarea unei imagini cu un singur canal si a dimensiunilor acesteia
 */

public class Image2D {
	private int width;
	private int height;
	private int[][] data;

	// Constructor cu dimensiuni si valorile pixelilor
	public Image2D(int width, int height, int[][] data) {
		this.width = width;
		this.height = height;
		this.data = data;
	}
	// Constructor cu dimensiuni
	public Image2D(int width, int height) {
		this.width = width;
		this.height = height;
		this.data = new int[width][height];
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if (width <= 0)
			return;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if (height <= 0)
			return;
		this.height = height;
	}

	public int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		this.data = data;
	}
	// Modificarea unui pixel individual in imagine
	public void setValue(int x, int y, int value) {
		if (x >= width || y >= height || x < 0 || y < 0)
			return;
		data[x][y] = value;
	}

}
