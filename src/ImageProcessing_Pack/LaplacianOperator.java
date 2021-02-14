package ImageProcessing_Pack;

/*
 * O implementare concreta a filtrului generic de imagine
 * Defineste nucleul de convolutie specific operatorului Laplace discret 
 * si implementeaza metoda Update pentru a aplica operatia de convolutie fiecarui pixel al imaginii 
 */

public class LaplacianOperator extends GenericImageFilter {

	/*
	 * Kernel-ul este initializat cu o aproximare discreta a operatiei Laplace
	 * 
	 */
	public LaplacianOperator(int sign) {
		if(sign == 0){
			int [][] kernelData = {
					{0, -1, 0 }, 
					{-1, 4, -1},
					{0, -1, 0 }};
			this.SetKernel(new Image2D(3, 3, kernelData));
		}
		else{
			int [][] kernelData = {
					{0, 1, 0 }, 
					{1, -4, 1},
					{0, 1, 0 }};
			this.SetKernel(new Image2D(3, 3, kernelData));
		}
	}

	// Implementeaza metoda abstracta a clasei de tip filtru generic pentru a face operatia dorita
	@Override
	public void Update() {
		System.out.println("Inceputul rutinei de procesare a imaginii");
		startTime = System.currentTimeMillis();
		proc = new Image2D(source.getWidth(), source.getHeight());
		for (int x = 0; x < proc.getWidth(); x++) {
			for (int y = 0; y < proc.getHeight(); y++) {
				// Calculam operatia de convolutie in fiecare punct al imaginii vechi si o salvam in imaginea noua
				proc.setValue(x, y, convolutionAtPoint(x, y) + 122);
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println("Procesare finalizata in " + (endTime - startTime) + "ms");
	}

	

}
