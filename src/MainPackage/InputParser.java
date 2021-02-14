package MainPackage;

public class InputParser {
	public int checkInput(String[] inputArgumets) {
		// Verificam sa avem exact 3 argumente
		if (inputArgumets.length != 3) {
			System.out.println("Incorrect number of parameters");
			System.out.println("Usage: imageProcessor sourceFile destinationFile 0|1");
			System.out.println("Exiting...");
			return 1;
		}

		/*
		 * Incercam sa parsam al treilea parametru ca int. Daca nu reuseste
		 * iesim din aplicatie
		 */
		try {
			Integer.parseInt(inputArgumets[2]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 1;
		}

		return 0;
	}
}
