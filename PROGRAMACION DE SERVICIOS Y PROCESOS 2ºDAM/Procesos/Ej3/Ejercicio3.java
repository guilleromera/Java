package Procesos;

public class Ejercicio6 {
	
	//solo se ejecutara el ejercicio si el argumento introducido es una cadena
	public static void lanzaCadena(String [] args) {
		if(args.length==0||Ejercicio6.compruebaCadena(args)==false) {
			System.out.println("No se ha introducido nada o no se ha introducido un String");
			System.exit(1);
		}
		if(Ejercicio6.compruebaCadena(args)) {
			for(int i=0;i<5;i++) {
				String cadena="";
				for(int a=0;a<args.length;a++) {
					if(args[a].charAt(a)==32) {
						cadena+=" ";
					}else {
						cadena+=args[a];
					}
				}
				System.out.println("La cadena introducida es: "+cadena);
			}
			System.exit(0);
		}
	}
	//metodo que comprueba si el tipo introducido es un string
	public static boolean compruebaCadena(String[]args) {
		boolean resultado = false;
		for(int i=0;i<args[0].length();i++) {
			if(args[0].charAt(0)>=65&&args[0].charAt(i)<=90 || args[0].charAt(i)>=97&&args[0].charAt(i)<=122) {
				resultado=true;
			}else {
				resultado=false;
			}
		}
		return resultado;
	}
	public static void main(String[] args) {
		Ejercicio6.lanzaCadena(args);
	}

}
