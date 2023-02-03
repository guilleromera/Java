package Procesos;

public class Ejercicio5 {
	
	public static void leerArgs(String[] args) {
		if(args.length<1) {
			System.out.println("No se ha introducido ningun arg");
			System.exit(1);
		}
		//es otra forma de ponerlo, tambien se puede poner método==true;
		else if(Ejercicio5.compruebaCadena(args)) {
			System.out.println("Es una cadena");
			System.exit(2);
		}
		else if(!Ejercicio5.compruebaCadena(args)) {
			int num=Integer.parseInt(args[0]);
			if(num<0) {
				System.out.println("Número negativo");
				System.exit(3);
			}if(num>0) {
				System.out.println("Numero positivo");
				System.exit(4);
			}else {
			System.out.println("No se cumple ninguna condicion");
			System.exit(0);
			}
		}		
	}
	
	//metodo booleano que nos devuelve true si la comprobacion de la cadena es true
	public static boolean compruebaCadena(String[]args) {
		boolean resultado = false;
		for(int i=0;i<args[0].length();i++) {
			if(args[0].charAt(i)>=65&&args[0].charAt(i)<=90 || args[0].charAt(i)>=97&&args[0].charAt(i)<=122) {
				resultado=true;
			}else {
				resultado=false;
			}
		}
		return resultado;
	}
	public static void main(String[] args) {
		Ejercicio5.leerArgs(args);
	}

}
