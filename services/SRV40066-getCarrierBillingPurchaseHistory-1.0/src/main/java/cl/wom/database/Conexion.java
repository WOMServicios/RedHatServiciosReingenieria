package cl.wom.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	static Connection con = null;
	static String password = "carrierdes09";
	static String user = "CARRIERBILLING";
	static String host = "10.120.148.136";
	static String port = "1521";
	static String databaseName = "WAPPLDESA";

	public static Connection conectar() {
		  try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		try {
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + databaseName, user,
					password);
//			con=DriverManager.getConnection("jdbc:oracle:thin:@10.120.148.136:1521:WAPPLDESA","CARRIERBILLING","carrierdes09");
			if (con != null) {
				System.out.println("Conectado");
			}
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		}
		return con;
	}
}
