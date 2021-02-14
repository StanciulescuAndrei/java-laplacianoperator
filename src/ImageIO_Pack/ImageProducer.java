package ImageIO_Pack;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ImageProducer extends Thread {
	private ImageBuffer buffer;
	private BufferedImage image;

	byte[] blockData;

	public ImageProducer(ImageBuffer buffer, BufferedImage image) {
		this.buffer = buffer;
		this.image = image;
	}

	public void run() {
		blockData = new byte[buffer.getBufferSize()];
		int blockSize = buffer.getBufferSize();

		// Citim imaginea si facem conversia RGB -> grayscale
		byte[] rawImageData = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		byte[] pixels = new byte[rawImageData.length / 3];
		/*
		 * Pentru fiecare pixel al imaginii citim 3 octeti (BGR)
		 * Filtrul Laplace lucreaza pe imagini cu un singur canal, deci in timpul citirii vom face si conversia. 
		 * Pentru a compensa pentru perceptia diferita a culorilor vom folosi formula:
		 * G = 0.3 * R + 0.59 * G + 0.11 * B
		 * 
		 * Folosim & 0xff pentru a face conversia intre byte cu semn in intreg fara semn (valorile sunt intre 0 - 255)
		 */
		for (int pixel = 0; pixel + 2 < rawImageData.length; pixel += 3) {
			pixels[pixel / 3] = (byte) ((0.3 * (double) (rawImageData[pixel + 2] & 0xff))
					+ (0.59 * (double) (rawImageData[pixel + 1] & 0xff))
					+ (0.11 * (double) (rawImageData[pixel] & 0xff)));
		}

		for (int block = 0; block < 4; block++) {
			// Citim pe rand fiecare sfert de imagine si le punem in buffer pentru consumer
			for (int i = 0; i < blockSize; i++) {
				blockData[i] = pixels[block * blockSize + i];
			}
			buffer.putData(blockData);
			System.out.println("Producer a pus blocul " + (block + 1) + "/4 in buffer");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
