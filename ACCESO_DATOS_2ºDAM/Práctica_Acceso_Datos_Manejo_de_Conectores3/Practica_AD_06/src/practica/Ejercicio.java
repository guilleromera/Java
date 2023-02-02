package practica;

import java.sql.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Ejercicio {
	public static void main(String[] args) {
		/*Utiliza la interfaz PreparedStatement para visualizar el primer apellido, NIF y salario de los empleados de 
		un departamento cuyo valor se recibe desde los argumentos de main(). Visualiza también el nombre del departamento.
		Visualiza al final el salario medio y el número de empleados del departamento. Si el departamento no 
		existe en la tabla departamentos, visualiza un mensaje indicándolo.*/
		
		try {
			//Cargo el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleados","vespertino","password");
			
			/*asigno el departamento al valor 0 pasado por argumento, tiene que ser String
			este valor es el que vamos a introducir en función del departamento que queramos consultar*/
			String dep = args[0];
			
			//creo una variable de numero entero para castear el String que recibimos por argumento
			int depart = Integer.parseInt(dep);
			DecimalFormat formato = new DecimalFormat("##,##0.00");
			
			//Selecciono el primer apellido, nif,salario y nombre de departamento de los empleados cuyo departamento se recibe por argumento
			String sql = "Select e.apellido1,e.nif,e.salario,d.nombre as nombreDepartamento from empleado e "
					+ "inner join departamento d on e.codigo_departamento=d.codigo "
					+ "where codigo_departamento=?";//con el signo '?' se indican los parametros que serán incluidos en la consulta.
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			/*este setInt nos recoge 2 parametros. 1º: el número de parametros que vamos a introducir , 2º: El número que vamos a utilizar para la consulta
			en este caso, la consulta del departamento que vamos a hacer es el numero que introducimos por argumento*/
			sentencia.setInt(1, depart);
			ResultSet rs = sentencia.executeQuery();
			
			//variable que recoge la suma media de los salarios
			double sumSalario = 0;
			//variable que recoge el numero de empleados que aparecen
			int contadorEmpleados = 0;
			/*metodo que se coloca en una posicion antes de la primera fila de ResultSet
			 * pongo la negación porque si no hay ningun registro es un campo vacío
			 y por tanto, no existe ese departamento*/
			if (!rs.isBeforeFirst()) {
				//como solo existen 7 departamentos, entrará por aqui el programa si num<1||num>7
				System.out.println("El departamento: " + depart + " no existe en la tabla.");
			} else {
				//entra por aqui si los valores introducidos estan entre 1 y 7 incluidos ambos
				while (rs.next()) {
					String apellido = rs.getString(1);
					String nif = rs.getString(2);
					double salario = rs.getDouble(3);
					String salarioFormateado = formato.format(salario);
					String nombre=rs.getString(4);
					System.out.println("Apellido: " + apellido +"  DNI: " + nif +"  Salario: " +salarioFormateado +"  NombreDepartamento: "+nombre);
					
					
					sumSalario+=salario;//por cada valor añado el salario a esta variable
					contadorEmpleados++;//por cada empleado sumo 1 al contador
				}
				System.out.println("-------------------------");
				double salMedio=sumSalario/contadorEmpleados;
				System.out.println("El salario medio total es: "+salMedio+ " y el número de empleados es: "+contadorEmpleados);
			}
			sentencia.close();
			conexion.close();
			
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}