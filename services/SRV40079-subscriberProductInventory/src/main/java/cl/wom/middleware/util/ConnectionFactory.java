package cl.wom.middleware.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static PropertiesUtil prop = new PropertiesUtil();
	static String user = prop.getProperties("database.bscs.username").toString();
	static String password = prop.getProperties("database.bscs.password").toString();
	static String host = prop.getProperties("database.bscs.host").toString();
	static String port = prop.getProperties("database.bscs.port").toString();
	static String databaseName = prop.getProperties("database.bscs.databasename").toString();
	
	static Connection conn = null;

	static public enum DataBaseSchema {
	    BSCS,
	}
	
	public static Connection getConnection(DataBaseSchema shema) throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("system property: "+System.getProperty("database.bscs.host"));
		
		switch (shema) {
		case BSCS:
			return DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + databaseName, user,password);
		default:
		return null;
	}
}
}