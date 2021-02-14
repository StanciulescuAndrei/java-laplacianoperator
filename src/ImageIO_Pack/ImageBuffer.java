package ImageIO_Pack;

/*
 * Clasa de tip buffer pentru imagine
 * Obiectul tine un array de bytes ce reprezinta datele ce trebuie trimise de la producer la consumer
 * 
 */
public class ImageBuffer {
	private byte[] bufferData;
	private int bufferSize;
	private boolean availableData = false;

	public ImageBuffer(int bufferSize) {
		this.bufferSize = bufferSize;
		this.bufferData = new byte[this.bufferSize];
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public synchronized void putData(byte[] data) {
		while (availableData == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Transfera datele de la intrare in memoria buffer-ului
		for (int i = 0; i < bufferSize; i++) {
			bufferData[i] = data[i];
		}
		availableData = true;
		notifyAll();
	}

	public synchronized byte[] getData() {
		while (availableData == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		availableData = false;
		notifyAll();
		// Trimite mai departe datele din buffer
		return bufferData;
	}
}
