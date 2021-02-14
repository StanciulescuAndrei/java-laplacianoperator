package ImageProcessing_Pack;

/*
 * O implementare generica a manipulatorului de imagini
 * Implementeaza un filtru de convolutie cu metoda de Update abstracta,
 * pentru a putea fi definita ulterior pentru fiecare filtru in parte
 */

abstract public class GenericImageFilter implements ImageManipulator{
	
	
	// Imaginile pentru kernel, sursa si imagine procesata
	protected Image2D kernel;
	protected Image2D source;
	protected Image2D proc;
	// Date pentru timing-ul aplicatiei
	protected long startTime;
	protected long endTime;

	public GenericImageFilter(int [][] kernelData, int... kernelSize) {
		if(kernelSize.length == 1){ // un singur argument, deci avem kernel patrat
			this.kernel = new Image2D(kernelSize[0], kernelSize[0], kernelData);
		} else if(kernelSize.length == 2){ // 2 argumente, deci avem un kernel dreptunghiular
			this.kernel = new Image2D(kernelSize[0], kernelSize[1], kernelData);
		}
	}
	
	public GenericImageFilter() {
		this.kernel = null;
	}

	abstract public void Update();
	
	@Override
	public void SetInputConnection(Image2D input) {
		this.source = input;
	}

	@Override
	public void SetKernel(Image2D kernel) {
		this.kernel = kernel;
	}

	@Override
	public Image2D GetOutput() {
		return proc;
	}
	
	protected int convolutionAtPoint(int x, int y) {
		/*
		 * Functie de calcul a convolutiei intre imagine si kernel in punctul (x, y)
		 * Rezultatul trebuie stocat in alta locatie, nu tot in sursa, pentru a nu
		 * afecta pasii urmatori ai convolutiei
		 */
		int posx, posy;
		int sum = 0;
		for (int dx = -kernel.getWidth() / 2; dx <= kernel.getWidth() / 2; dx++) {
			for (int dy = -kernel.getHeight() / 2; dy <= kernel.getHeight() / 2; dy++) {
				posx = x + dx;
				posy = y + dy;
				if (posx >= 0 && posx < source.getWidth() && posy >= 0 && posy < source.getHeight()) {
					// Punctul pe care il calculam e in interiorul imaginii
					sum = sum + kernel.getData()[dx + kernel.getWidth() / 2][dy + kernel.getHeight() / 2]
							* source.getData()[posx][posy];
				}
				else
					return 0;
			}
		}
		return (sum);

	}

}
