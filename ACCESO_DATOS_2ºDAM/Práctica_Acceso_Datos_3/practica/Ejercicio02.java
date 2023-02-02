package practica;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio02 {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {

		// Instancio f con el fichero Departamentos.dat
		File f = new File("Departamentos.dat");
		// Metodos para lectura de binarios
		FileInputStream fIn = new FileInputStream(f);
		DataInputStream dIn = new DataInputStream(fIn);
		// Lista de departamentos
		ArrayList<Departamento> lista = new ArrayList<Departamento>();

		// Variables
		int numDept;
		String nomDept;
		String locDept;

		// Leo los ficheros binarios siguiendo la secuencia
		try {
			while (true) {
				numDept = dIn.readInt();
				nomDept = dIn.readUTF();
				locDept = dIn.readUTF();
				// Creo un objeto departamento y lo vuelco en la lista
				lista.add(new Departamento(numDept, nomDept, locDept));
			}
		} catch (Exception e) {
			// Traza
			System.out.println("He llegado al final de la lectura");
		}

		// Cierro la lectura de ficheros
		dIn.close();

		// ---------Acaba la lectura del fichero, empieza el volcado a xml---------

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();

		// Creo un documento al que le paso el namespace, el elemento raiz y el doctype
		Document documento = implementation.createDocument(null, "departamentos", null);

		// Asigno version
		documento.setXmlVersion("1.0");

		// Volcare cada atributo del objeto en cada nodo elemento
		for (int i = 0; i < lista.size(); i++) {

			Element departamento = documento.createElement("departamento");

			Element numero = documento.createElement("numero");
			Text textNumero = documento.createTextNode("" + lista.get(i).getNumDept());
			numero.appendChild(textNumero);
			departamento.appendChild(numero);

			Element nombre = documento.createElement("nombre");
			Text textNombre = documento.createTextNode(lista.get(i).getNombre());
			nombre.appendChild(textNombre);
			departamento.appendChild(nombre);

			Element localidad = documento.createElement("localidad");
			Text textLocalidad = documento.createTextNode(lista.get(i).getLocalidad());
			localidad.appendChild(textLocalidad);
			departamento.appendChild(localidad);

			documento.getDocumentElement().appendChild(departamento);

		}

		// Creo fuente y resultado
		Source source = new DOMSource(documento);
		Result result = new StreamResult(new File("empresa.xml"));

		// Los transformo a fichero
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, result);

		// Traza
		System.out.println("He acabado con la escritura del xml");

	}

}