import java.io.File;
import java.util.Scanner;

public class EnunciadoA {

	public static void main(String[] args) {
		/*
		 * Realiza un programa en Java que utilice el método listFiles(s) para mostrar
		 * la lista de ficheros en un directorio cualquiera (el que tú elijas)
		 */

		Scanner sc = new Scanner(System.in);
		String ruta = "";
		// intoducimos la ruta por pantalla,introduzco /home/vespertino
		System.out.println("Introduce la ruta a mostrar");
		ruta = sc.nextLine();

		// al archivo creado le pasamos la ruta
		File f = new File(ruta);

		// creo un array de ficheros y los recorro para saber si cada carpeta es archivo
		// o directorio

		File lista[] = f.listFiles();
		for (int a = 0; a < lista.length; a++) {
			if (lista[a].isFile()) {
				System.out.println("El nombre del fichero es: " + lista[a].getName());
			} else {
				System.out.println("El nombre del directorio es: " + lista[a].getName());
			}
		}

	}

}
