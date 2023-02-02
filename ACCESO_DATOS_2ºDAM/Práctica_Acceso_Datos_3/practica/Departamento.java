package practica;

public class Departamento {

	// Atributos
	private int numDept;
	private String nombre;
	private String localidad;

	// Metodo constructor
	public Departamento(int numDept, String nombre, String localidad) {
		this.numDept = numDept;
		this.nombre = nombre;
		this.localidad = localidad;
	}

	// Getters para poder acceder a los datos
	public int getNumDept() {
		return numDept;
	}

	public String getNombre() {
		return nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

}