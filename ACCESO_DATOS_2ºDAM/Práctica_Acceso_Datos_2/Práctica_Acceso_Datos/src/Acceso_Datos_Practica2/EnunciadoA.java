package Acceso_Datos_Practica2;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;
import java.util.Scanner;

public class EnunciadoA {

	public static void main(String[] args) throws IOException {
		/* creo el fichero binario, se crea en la ruta a nivel del package,haciendo refresh en el proyecto aparece
		dejo el archivo binario pero se puede borrar y empezar ejecutando el EnunciadoA que nos pedirá la inserción de datos*/
		File f = new File("Fichero.dat");

		if (!f.exists()) {
			f.createNewFile();
		}
		
		EnunciadoA.comparaId(f);

	}

	// método para insertar datos en el archivo en cuestión
	public static void insertaDatos(File fich) throws IOException {
		// como voy a leer y escribir datos el segundo parámetro es "rw"
		RandomAccessFile fichero = new RandomAccessFile(fich, "rw");

		// lo creo para introducir datos por teclado
		Scanner sc = new Scanner(System.in);
		int id;// cada int ocupa 4 bytes
		String apellido;// cada char ocupa 2 bytes, seteamos la longitud del apellido a 10 por tanto la
						// longitud es 10x2=20 bytes
		int departamento;// otro int que ocupa 4 bytes
		double salario;// cada double ocupa 8 bytes

		try {
			fichero.seek(fichero.length());// me posiciono al final del archivo para que cada vez que añada la
											// informacion, si no la sobreescribe
			System.out.println("Introduce el id");// Pido todos los datos y los almaceno en variables
			id = sc.nextInt();

			System.out.println("Introduce el apellido");// Pido todos los datos y los almaceno en variables
			apellido = sc.next();
			// sirve para almacener cadenas de caracteres, ya que no podemos escribir
			// Strings
			StringBuilder sb = new StringBuilder(apellido);
			// seteo la longitud a un maximo de 10 caracteres, si es inferior lo
			// autocompleta por defecto
			sb.setLength(10);

			System.out.println("Introduce el departamento");
			departamento = sc.nextInt();

			System.out.println("Introduce el salario");
			salario = sc.nextDouble();

			// escribo en su tipo correcto debido a que es un fichero binario y tengo que
			// introducir los datos en orden
			fichero.writeInt(id);
			// casteo a toString
			fichero.writeChars(sb.toString());
			fichero.writeInt(departamento);
			fichero.writeDouble(salario);

			// trato excepciones
		} catch (EOFException e) {
			e.getMessage();
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		// cierro el fichero para que se guarde la información, sino no se guarda en
		// escritura
		fichero.close();
	}

	// método para mostrar todos los datos
	public static void muestraDatos(File f) throws IOException {

		// como solo voy a leer el segundo parametro es "r" de read
		RandomAccessFile fichero = new RandomAccessFile(f, "r");

		// menu que va a aparecer cada vez que muestra los datos,SOLO PARA WINDOWS
		//String menu = "ID " + "Apellido " + "Departamento " + "Salario";
		
		try {
			//System.out.println(menu);
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
				System.out.print("ID: "+num + " \nApellido: " + nombre + "\nDepartamento: " + dep + "\nSalario: " + sal + "\n");
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
						EnunciadoA.muestraDatos(f);
						break;
					}
					/*si la consulta no existe y el indice es igual a la posicion maxima -36
					 es que no existe el ID que es igual a la consulta pedida e inserto los datos pedidos por pantalla y muestro el total de datos a continuacion*/
					if (consulta != id&&indice==aleatorio.length()-36) {
						System.out.println("El id " + consulta + " no existe");
						EnunciadoA.insertaDatos(f);
						EnunciadoA.muestraDatos(f);
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
