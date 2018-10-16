package cl.wom.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	static Connection con = null;
	static String password = "knh#5tgl20k0lpm.l";
	static String user = "OPSH_BSCSREP_SYSADM_CBI";
	static String host = "10.120.241.44";
	static String port = "1550";
	static String databaseName = "BSCSDESA";
	
	
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
