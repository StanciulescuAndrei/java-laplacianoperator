package ImageIO_Pack;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ImageProcessing_Pack.Image2D;

public class ImageReader {

	private String path;
	private ImageProducer producer;
	private ImageConsumer consumer;
	private ImageBuffer buffer;
	
	// Variabile pentru masurarea timpului de citire
	long startTime, endTime;

	private Image2D image;

	public ImageReader(String _path) {
		this.path = _path;
	}

	public void SetImagePath(String _path) {
		this.path = _path;
	}

	public void StartReadOperation() {
		startTime = System.currentTimeMillis();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Dimensiune imagine: " + img.getWidth() + " x " + img.getHeight());
		int imageSize = img.getWidth() * img.getHeight();
		image = new Image2D(img.getWidth(), img.getHeight());

		int blockSize = imageSize / 4;
		
		// Initializam buffer-ul, producer si consumer
		buffer = new ImageBuffer(blockSize);
		producer = new ImageProducer(buffer, img);
		consumer = new ImageConsumer(buffer);

		producer.start();
		consumer.start();
		try {
			// Asteptam sa se termine citirea datelor
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Acum transformam imaginea din sir de valori in tablou;
		int index = 0;
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				image.setValue(x, y, ((int)consumer.getFullImage()[index]) & 0xff);
				index++;
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println("Citire finalizata in " + (endTime - startTime) + "ms");

	}

	public Image2D getImage() {
		return image;
	}

}
