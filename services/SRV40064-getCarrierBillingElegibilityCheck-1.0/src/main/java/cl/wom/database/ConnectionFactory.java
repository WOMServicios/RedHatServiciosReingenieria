package cl.wom.database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.camel.language.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


public class ConnectionFactory {
	@Value("${database.wappl.name}")
    private  String email;
	
	
	
	
	static Connection con = null;
	static public enum DataBaseSchema {
	    BSCS,
	    WAPPL;
	}
	public void prueba() {
		System.err.println(email+" emaillllll");

	}

//	public static Connection conectar(String password,String user,String host,String port,String databaseName) {
//		  try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//
//		try {
//			
//			con = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + databaseName, user,
//					password);
////			con=DriverManager.getConnection("jdbc:oracle:thin:@10.120.148.136:1521:WAPPLDESA","CARRIERBILLING","carrierdes09");
//			if (con != null) {
//				System.out.println("Conectado");
//			}
//		} catch (SQLException e) {
//			System.out.println("No se pudo conectar a la base de datos");
//			e.printStackTrace();
//		}
//		return con;

	public static Connection getConnection(DataBaseSchema shema) throws ClassNotFoundException, SQLException {
//		ResourceBundle rb = ResourceBundle.getBundle("application.properties");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
//		switch (shema) {
//			case BSCS:
//				return DriverManager.getConnection("jdbc:oracle:thin:@" + "${database.replica.host}" + ":" + "${database.replica.port}" + ":" + "${database.replica.name}", "${database.replica.username}","${database.replica.password}");
//			case WAPPL:
//				return DriverManager.getConnection("jdbc:oracle:thin:@" + "${database.wappl.host}" + ":" + "${database.wappl.port}" + ":" + "${database.wappl.name}", "${database.wappl.username}","${database.wappl.password}");
//
////				return DriverManager.getConnection("jdbc:oracle:thin:@" + rb.getString("${database.wappl.host}") + ":" + rb.getString("${database.wappl.port}") + ":" + rb.getString("${database.wappl.name}"), rb.getString("${database.wappl.username}"),	rb.getString("${database.wappl.password}"));				
//		default:
//			return null;
//		}
		return null;
	}
	

	
}
