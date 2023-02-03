package Procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//vamos a llamar al ejercicio5
public class Ejercicio6V2 {
	
	public static void lanzaCadena(String  args) {
		
		try {
			File ruta= new File(".//bin");
			ProcessBuilder pb= new ProcessBuilder("java","Procesos.Ejercicio6",args);
			pb.directory(ruta);
			Process p= pb.start();
			
			try {
				InputStream is= p.getInputStream();
				int c;
				
				while((c=is.read())!=-1) {
					System.out.print((char)c);
				}
				is.close();	
			}catch(Exception e) {
				e.printStackTrace();
			}
			//capturo los errores que puedan salir
			try {
				InputStream er = p.getErrorStream();
				BufferedReader brer = new BufferedReader(new InputStreamReader(er));
				String liner = null;
				while ((liner = brer.readLine()) != null) {
					System.out.println("ERROR " + liner);

				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Ejercicio6V2.lanzaCadena("Amo mi pelo");
		System.out.println("Ha finalizado el programa");

	}

}
