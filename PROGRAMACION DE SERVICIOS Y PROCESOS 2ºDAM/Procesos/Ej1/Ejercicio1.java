package Procesos;

public class Ejercicio2 {
	
	//método estático para leer el nombre
	public static void leerNombre(String[] nombre) {
		if (nombre.length == 0) {
			System.out.println("No has introducido parametros");
			System.exit(2); //finaliza por aqui si no se han intoducido datos en el main
		} else {
			//se lo tengo que pasar por argumentos directos
			System.out.println("El nombre leido es: " + nombre[0]);
			System.exit(1); //finalizacion correcta del programa
		}
	}
	public static void main(String[] args) {
		Ejercicio2.leerNombre(args);
	}

}
