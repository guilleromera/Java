package ejercicio09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeeCadenas {
	public static void main(String[] args) throws IOException {


		File fIn=new File("inputEj09.txt");
		FileInputStream fichero = new FileInputStream(fIn);
		InputStreamReader fir = new InputStreamReader(fichero);
		BufferedReader br = new BufferedReader(fir);
		String linea = br.readLine();
		boolean encontrado=false;

		while (linea != null && !encontrado) {

			System.out.println("Palabra a comprobar: " + linea);

			// Leo la siguiente palabra si es * finalizo con el booleano a true y no leo más
			if (! linea.equals("*")) {
				linea = br.readLine();
			}else encontrado=true;
		}

		System.out.println("Fin comprobacion");

	}
}
