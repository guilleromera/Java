package Ej2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio2 {

	public static void main(String[] args) {
		 /*realiza una aplicación Java que visualice el primer apellido del empleado con más
		 salario y el nombre del departamento al que pertenece.*/
		
		try {
			//cargo el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establecemos la conexion con la BD
			Connection conexion= DriverManager.getConnection("jdbc:mysql://localhost/empleados","vespertino","password");
			
			
			//Hago la consulta;
			Statement sentencia=conexion.createStatement();
			String sql ="select e.apellido1,d.nombre as nombreDepartamento from empleado e INNER JOIN departamento d on e.codigo_departamento=d.codigo where e.salario=(select max(salario) from empleado)";
			
			ResultSet resul=sentencia.executeQuery(sql);
			
			String menu="apellido "+" nombreDepartamento";
			System.out.println("Muestro el apellido y el nombre de departamento del trabajador con el salario mas alto\n");
			System.out.println(menu);
			
			while(resul.next()) {
				System.out.printf("%s\t, %s ",
				resul.getString(1),
				resul.getString(2)+"\n");
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
