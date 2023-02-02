package Ej1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		Scanner sc= new Scanner (System.in);
		/*realiza una aplicación Java, que solicite al usuario el código de un departamento y
		muestre por pantalla el DNI, primer apellido y nombre de los empleados de dicho departamento, así como
		el nombre de dicho departamento*/
		
		try {
			//cargo el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establecemos la conexion con la BD
			Connection conexion= DriverManager.getConnection("jdbc:mysql://localhost/empleados","vespertino","password");
			
			
			//Hago la consulta
			int codigo;
			String menu="DNI\t "+"apellido1   "+"nombre\t"+"nombreDepartamento";
			
			//si introduxco el codigo 6 o 7 me sale solo el menu porque no existe ningun empleado con codigo_departamento 6 o 7
			do {
				System.out.println("Introduce el codigo a pedir, tiene que estar entre 1 y 7");
				codigo=sc.nextInt();
			}while(codigo<1||codigo>7);
			
			Statement sentencia=conexion.createStatement();
			String sql ="select e.nif,e.apellido1,e.nombre,d.nombre as nombreDepartamento from empleado e INNER JOIN departamento d on e.codigo_departamento=d.codigo where d.codigo="
					+ codigo;
			
			ResultSet resul=sentencia.executeQuery(sql);
			
			
			/*metodo que se coloca en una posicion antes de la primera fila de ResultSet
			en este caso pongo la negación porque si no hay registro alguno es que es 
			un campo vacío y nos muestra el mensaje por pantalla*/
			if(!resul.isBeforeFirst()) {
				System.out.println("No existen empleados con ese codigo");
			}else {
				System.out.println(menu);
				while(resul.next() ) {
					System.out.printf("%s, %s  , %s\t, %s",
					resul.getString(1),
					resul.getString(2),
					resul.getString(3),
					resul.getString(4)+"\n");
			}
			}
			
			resul.close();
			sentencia.close();
			conexion.close();
			
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		

	}

}
