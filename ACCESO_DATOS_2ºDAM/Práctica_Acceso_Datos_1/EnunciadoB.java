import java.io.File;
import java.util.Scanner;

public class EnunciadoB {

	public static void main(String[] args) {
		/*
		 * Realiza un programa en Java que muestre los ficheros de un directorio. El
		 * nombre del directorio se pasará al programa desde los argumentos de main().
		 * Si el directorio no existe se debe mostrar un mensaje indicándolo.
		 */

		String nombre = args[0];
		File f = new File(nombre);

		// primero verifico que el directorio exista y si no es asi, lo muestro por
		// pantalla
		File lista[] = f.listFiles();
		if (f.exists() && f.isDirectory()) {
			for (int a = 0; a < lista.length; a++) {
				// recorro cada uno de los ficheros y muestro su nombre
				System.out.println("El nombre del fichero es: " + lista[a].getName());
			}
		} else {
			System.out.println("El directorio no existe");
		}
		// ejecutamos el ejercicio con /home/vespertino otra vez
	}

}
