package ejercicio09;

import java.io.File;
import java.io.IOException;

public class CompruebaCadena {
	public static void main(String[] args) throws IOException {

		File directorio = new File(".//bin");
		
		ProcessBuilder pb = new ProcessBuilder("java", "ejercicio09.LeeCadenas");
		
		File fOut = new File("salida09.txt");
		File fErr = new File("errorEj09.txt");
				
		pb.redirectOutput(ProcessBuilder.Redirect.appendTo(fOut));
		pb.redirectError(ProcessBuilder.Redirect.appendTo(fErr));
		
		pb.directory(directorio);
		Process p = pb.start();
		
	}
}
