package Acceso_Datos_Practica2;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EnunciadoB {

	public static void main(String[] args) throws IOException {
		//utilizo el fichero del enunciado A
		File f = new File("Fichero.dat");
		
		EnunciadoB.comparaId(f);
		
	}

	// método para mostrar todos los datos
	public static void muestraDatos(File f) throws IOException {

		// como solo voy a leer el segundo parametro es "r" de read
		RandomAccessFile fichero = new RandomAccessFile(f, "r");
		
		try {
			
			for (int i = 0; i < fichero.length(); i++) {
				int num = fichero.readInt();
				// acumulo en una variable string cada char hasta un maximo de 10 length
				String nombre = "";
				for (int a = 0; a < 10; a++) {
					nombre += fichero.readChar();
				}
				int dep = fichero.readInt();
				double sal = fichero.readDouble();
				// linea a linea todos los atributos
				System.out.print("ID: " + num + " \nApellido: " + nombre + "\nDepartamento: " + dep + "\nSalario: "
						+ sal + "\n");
			}
		} catch (EOFException e) {
			e.getMessage();
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		fichero.close();
	}

	
	public static void comparaId(File f) throws IOException {
		RandomAccessFile aleatorio = new RandomAccessFile(f, "r");
		Scanner sc = new Scanner(System.in);
		
		//el indice me servirá para recorrer los registros
		int indice = 0;
		int id = 0;
		int consulta = 0;

		try {
			//posiciono el fichero en 0
			aleatorio.seek(0);
			if (aleatorio.length() == 0) {
				//si el fichero está vacio lo primero que hará será escribir datos y lo muestra
				EnunciadoA.insertaDatos(f);
				EnunciadoA.muestraDatos(f);
			} else {
				//si el fichero no está vacio paso a hacer una consulta del ID para ver si está guardado o no
				System.out.println("Introduce un ID");
				consulta = sc.nextInt();
				
				while (true) {
					//posiciono en 0 el fichero
					aleatorio.seek(indice);
					id = aleatorio.readInt();
					
					//lo vuelvo a posicionar en 0 porque el entero id leido se ha posicionado en 4 por los 4 bytes del tipo int
					aleatorio.seek(indice);
					
					/*si la consulta es igual a un id existente de los empleados guardados
					devuelve la informacion de todos los empleados e imprime por pantalla que este ID ya existe*/
					if(consulta==id) {
						System.out.println("El id: " + consulta + " ya existe");
						//si existe muestro todos los datos de todos los empleados
						EnunciadoA.muestraDatos(f);
						break;
					}
					/*si la consulta no existe y el indice es igual a la posicion maxima -36
					 es que no existe el ID y lo muestro por pantalla*/
					if (consulta != id&&indice==aleatorio.length()-36) {
						System.out.println("El id " + consulta + " no existe");
						break;
					}
					/*si no he entrado a ningun if el indice suma 36 posiciones para pasar al siguiente registro y vuelve a consultar los ids
					hasta que indice==aleatorio.length()-36 en la que habría llegado al ultimo id del ultimo registro y nos diría que no existe*/
					indice+=36;
				}
			}
		} catch (EOFException e) {
			e.getMessage();
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		aleatorio.close();
	}
}
