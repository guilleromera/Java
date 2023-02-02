package practica;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio01 {

	public static void main(String[] args) throws IOException {
		// Instancio f con el fichero Departamentos.dat
		File f = new File("Departamentos.dat");
		// Si el fichero no existe lo creo
		if (!f.exists()) {
			f.createNewFile();
		}
		// Creo 7 departamentos
		Departamento dept1 = new Departamento(1, "Marketing", "Madrid");
		Departamento dept2 = new Departamento(2, "Logistica", "Getafe");
		Departamento dept3 = new Departamento(3, "Gestion", "Leganes");
		Departamento dept4 = new Departamento(4, "RRHH", "Mostoles");
		Departamento dept5 = new Departamento(5, "Directivo", "Torrelodones");
		Departamento dept6 = new Departamento(6, "Administrativo", "Galapagar");
		Departamento dept7 = new Departamento(7, "IT", "Valdemoro");

		// Vuelvo todos los departamentos en un vector
		Departamento vector[] = { dept1, dept2, dept3, dept4, dept5, dept6, dept7 };

		// Metodos para salida de binarios
		FileOutputStream fOut = new FileOutputStream(f);
		DataOutputStream dOut = new DataOutputStream(fOut);

		// Por cada posicion del vector, escribo el numero, el nombre y la localidad
		for (int i = 0; i < vector.length; i++) {
			dOut.writeInt(vector[i].getNumDept());
			dOut.writeUTF(vector[i].getNombre());
			dOut.writeUTF(vector[i].getLocalidad());
		}
		// Cierro la salida de datos
		dOut.close();
	}
}