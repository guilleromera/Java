package Procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio5Parte2 {
	
	public static void lanzarEjer(String args) {
		try {
			File ruta= new File(".//bin");
			ProcessBuilder pb= new ProcessBuilder("java","Procesos.Ejercicio5",args);
			pb.directory(ruta);
			Process p=pb.start();
			
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
			//capturo los errores que puedan salir en el Ejercicio5
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
		Ejercicio5Parte2 a= new Ejercicio5Parte2();
		a.lanzarEjer("20");
		System.out.println("Se acabó el programa");

	}

}
