package ImageIO_Pack;

public class ImageConsumer extends Thread {
	private ImageBuffer buffer;
	// imageData contine imaginea finala dupa ce toate fragmentele de imagine au fost trimise
	private byte[] imageData;
	// blockData stocheaza fragmentele intermediare de imagine
	private byte[] blockData;

	public ImageConsumer(ImageBuffer buffer) {
		this.buffer = buffer;
	}

	public void run() {

		int blockSize = buffer.getBufferSize();
		// In buffer vom avea cate un sfert de imagine, deci dimensiunea pentru imaginea finala va fi 4 * dimensiunea buffer-ului
		imageData = new byte[blockSize * 4];
		blockData = new byte[blockSize];

		for (int block = 0; block < 4; block++) {
			// Citim pe rand fiecare sfert de imagine din buffer in memoria ce va tine imaginea completa

			blockData = buffer.getData();
			// Fiecare bloc va avea un offset asociat, in functie de cate blocuri au fost scrie inaintea lui
			for (int i = 0; i < blockSize; i++) {
				imageData[block * blockSize + i] = blockData[i];
			}
			System.out.println("Consumer a preluat blocul " + (block + 1) + "/4 din buffer");
		}
	}
	
	// Intoarce imaginea completa la finalul citirii
	public byte[] getFullImage() {
		return imageData;
	}
}
