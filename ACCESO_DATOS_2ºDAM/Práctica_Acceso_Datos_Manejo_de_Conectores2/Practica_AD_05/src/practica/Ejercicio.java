package practica;

import java.sql.*;
import java.util.Scanner;

public class Ejercicio {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int opc;
		try {
			// Conexion a la base de datos con la base de datos y la contraseña
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleados", "vespertino",
					"password");
			Statement sentencia = conexion.createStatement();
			// Menu
			do {
				do {
					System.out.println("1. Añade la columna fecha_alta a la BBDD");
					System.out.println("2. Añadir empleado");
					System.out.println("3. Salir");
					opc = sc.nextInt();
				} while (opc > 3);
				if (opc == 1) {
					creoColumnaFechaAlta(sentencia);
				} else if (opc == 2) {
					creoEmpleado(sentencia);
				} else if (opc == 3) {
					System.out.println("Salgo del programa. Te me cuidas, que hace fresquito.");
				}
			} while (opc != 3);
			// Cierra la conexcion
			conexion.close();
			sentencia.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void creoColumnaFechaAlta(Statement sentencia) {
		try {
			// Añade a la tabla empleado una columna llamada fecha_alta de tipo date
			String sql = "alter table empleado add fecha_alta date";
			sentencia.executeUpdate(sql);
			// Traza que informa si la ejecucion fue correcta
			System.out.println("--------La columna ha sido añadida correctamente--------");
		} catch (SQLException e) {
			// Traza que informa si la columna ya existe
			System.err.println("------------------La columna ya existe------------------");
		}
	}

	public static void creoEmpleado(Statement sentencia) throws SQLException {

		// Variables
		boolean deptExiste;
		boolean numEmpExiste;
		String fecha_alta;
		String fecha_BBDD = "";

		// Muestra la fecha actual de la BBDD
		String sql_fecha_sistema = "select current_date()";

		// Fecha hoy
		ResultSet rsFecha;
		rsFecha = sentencia.executeQuery(sql_fecha_sistema);
		while (rsFecha.next()) {
			// Guarda la fecha de hoy
			fecha_BBDD = rsFecha.getString(1);
		}

		// Busqueda de departamento
		System.out.println("Ingrese id de departamento:");
		int id_departamento = sc.nextInt();
		String sqlBuscaDepto = "SELECT codigo FROM departamento WHERE codigo = " + id_departamento;
		ResultSet rsDepartamento = sentencia.executeQuery(sqlBuscaDepto);
		// Si existe lo guarda en una variable booleana
		if (rsDepartamento.next()) {
			deptExiste = true;
		} else {
			deptExiste = false;
		}

		// Busqueda empleado ID
		System.out.println("Ingrese id de empleado:");
		int id_empleado = sc.nextInt();
		String sqlBusqEmpleado = "SELECT codigo FROM empleado WHERE codigo = " + id_empleado;
		ResultSet rsEmpleado = sentencia.executeQuery(sqlBusqEmpleado);
		// Si existe lo guarda en una variable booleana
		if (rsEmpleado.next()) {
			numEmpExiste = true;
		} else {
			numEmpExiste = false;
		}
		sc.nextLine();

		// Resto de datos
		System.out.println("Ingrese nif:");
		String dni_empleado = sc.nextLine().toUpperCase();
		System.out.println("Ingrese nombre:");
		String nombre = sc.nextLine();
		System.out.println("Ingrese apellido 1:");
		String apellido1 = sc.nextLine();
		System.out.println("Ingrese apellido 2:");
		String apellido2 = sc.nextLine();
		System.out.println("Ingrese salario:");
		double salario = sc.nextDouble();
		sc.nextLine();

		// Fecha de alta
		System.out.println("Ahora se ingresará la fecha de alta");
		System.out.println("Ingresa el día: (FORMATO DD)");
		String dia = sc.nextLine();
		System.out.println("Ingresa Mes: (FORMATO MM)");
		String mes = sc.nextLine();
		System.out.println("Ingresa Año: (FORMATO YYYY)");
		String anyo = sc.nextLine();
		// Concatena la fecha con el formato que utiliza la base de datos
		fecha_alta = anyo + "-" + mes + "-" + dia;

		// Va recorriendo cada variable booleana comprobandp que se cumplen los
		// requisitos, tambien comprueba que el salario es mayor que 0 y los apellidos
		// tienen una longitud mayor que 0
		if (deptExiste == true && numEmpExiste == false && salario > 0 && apellido1.length() > 0
				&& apellido2.length() > 0 && fecha_BBDD.equalsIgnoreCase(fecha_alta)) {
			String sqlINSERT = "INSERT INTO empleado (codigo,nif,nombre,apellido1,apellido2,salario,codigo_departamento,fecha_alta)"
					+ " VALUES (" + id_empleado + ",'" + dni_empleado + "','" + nombre + "','" + apellido1 + "','"
					+ apellido2 + "'," + salario + "," + id_departamento + ",'" + fecha_alta + "')";
			// Ejecuta el insert
			sentencia.execute(sqlINSERT);
			// Traza que informa que el insert fue correcto
			System.out.println("-----------Enhorabuena, el INSERT fue introducido correctamente-----------");
		} else {
			// Va recorriendo cada if mostrando los mensajes con los errores
			if (deptExiste == true) {
				System.out.println("El departamento " + id_departamento + " esta en la tabla departamentos.");
			}
			if (deptExiste == false) {
				System.err.println("El departamento " + id_departamento + " no existe.");
			}
			if (numEmpExiste == true) {
				System.err.println("El ID de empleado " + id_empleado + " ya esta en uso.");
			}
			if (numEmpExiste == false) {
				System.out.println("El numero de empleado " + id_empleado + " esta disponible");
			}
			if (salario <= 0) {
				System.err.println("El salario no puedo ser inferior e igual a 0.");
			}
			if (apellido1.length() == 0) {
				System.err.println("Apellido1 con valor NULL, no puede ser nulo.");
			}
			if (apellido2.length() == 0) {
				System.err.println("Apellido2 con valor NULL, no puede ser nulo.");
			}
			if (!fecha_BBDD.equalsIgnoreCase(fecha_alta)) {
				System.err.println("Fecha distinta al dia actual, introduzca una fecha de alta correcta");
			}
			// Traza que muestra que no se ha insertado
			System.out.println("-----------No se ha podido realizar el INSERT-----------");
		}
		// Se cierra la conexion
		rsFecha.close();
		rsDepartamento.close();
		rsEmpleado.close();
	}
}
