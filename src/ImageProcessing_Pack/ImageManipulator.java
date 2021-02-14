package ImageProcessing_Pack;

/*
 * Interfata pentru un obiect ce lucreaza pe imagini 2D
 * Interfata este folosita pentru o implementare de tip pipeline,
 * unde se spiecifica conexiunile de intrare si de iesire pentru procesorul de imagini
 */

interface ImageManipulator {
	public void SetInputConnection(Image2D input);
	public Image2D GetOutput();
	void SetKernel(Image2D kernel);
}
