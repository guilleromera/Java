package Procesos;

public class Ejercicio7 {

	public static void sumNumeros(String[] args) {
		try {
			try {
				if(args.length==0) {
					System.out.println("No has introducido ningun parametro");
				}
				if(args.length==2) {
					int n1,n2,suma = 0;
					n1=Integer.parseInt(args[0]);
					n2=Integer.parseInt(args[1]);
					for(int i=n1;i<n2;i++) {
						suma+=i;
					}
					System.out.println("El resultado de la suma es: "+suma);
					System.exit(0);
				}
				//en caso de no ser un numero entero lo muestro por pantalla junto a los args
			}catch(NumberFormatException e) {
				System.out.println("Algun argumento no es int, revisalos: "+args[0]+" "+args[1]);
				System.exit(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Ejercicio7.sumNumeros(args);
	}

}
