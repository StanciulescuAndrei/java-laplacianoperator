package ImageIO_Pack;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ImageProcessing_Pack.Image2D;

public class ImageWriter {
	private String path;
	
	private long startTime, endTime;

	public ImageWriter(String path) {
		this.path = path;
	}

	public void writeImageToFile(Image2D image) {
		System.out.println("Inceputul etapei de scriere a imaginii");
		startTime = System.currentTimeMillis();
		
		// Instantiem o imagine buffer noua
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_BYTE_GRAY);
		int index = 0;
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				// Scriem pe rand valoarea fiecarui pixel in buffer-ul de scriere
				((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).setElem(index, image.getData()[x][y] & 0xff);
				index++;
			}
		}

		File imageFile = new File(path);
		try {
			// Scrie imaginea in format BMP in fisierul destinatie
			ImageIO.write(bufferedImage, "bmp", imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();
		System.out.println("Scriere finalizata in " + (endTime - startTime) + "ms");
	}

}
