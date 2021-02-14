package MainPackage;
import ImageIO_Pack.*;
import ImageProcessing_Pack.*;

public class ImageProcessor {

	public static void main(String[] args) {

		InputParser inputParser = new InputParser();

		/*
		 * Mai intai verificam daca argumentele corespund formatului pe care il asteptam,
		 * altfel finalizam aplicatia
		 */
		if (inputParser.checkInput(args) != 0) {
			return;
		}

		String sourceFile = args[0];
		String destinationFile = args[1];
		int type = Integer.parseInt(args[2]); // type seteaza pozitivitatea filtrului: 0 = negativ, 1 = pozitiv

		
		// Initializam citirea imaginii prin producer si consumer
		ImageReader imgReader = new ImageReader(sourceFile);
		imgReader.StartReadOperation();
		
		// Initializam filtrul si il aplicam imaginii
		LaplacianOperator laplaceFilter = new LaplacianOperator(type);
		laplaceFilter.SetInputConnection(imgReader.getImage());
		laplaceFilter.Update();

		// Scriem imaginea in fisierul destinatie
		ImageWriter imWriter = new ImageWriter(destinationFile);
		imWriter.writeImageToFile(laplaceFilter.GetOutput());

	}

}
