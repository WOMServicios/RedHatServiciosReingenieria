package cl.wom.middleware.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	static PropertiesUtil propi = new PropertiesUtil();
	static Properties prop = propi.getRemoteProperties("APP_ENV");
	static String user = prop.getProperty("database.bscs.username");
	static String password = prop.getProperty("database.bscs.password");
	static String host = prop.getProperty("database.bscs.host");
	static String port = prop.getProperty("database.bscs.port");
	static String databaseName = prop.getProperty("database.bscs.databasename");
	
//	static String user = prop.getRemoteProperties("database.bscs.username").toString();
//	static String password = prop.getRemoteProperties("database.bscs.password").toString();
//	static String host = prop.getRemoteProperties("database.bscs.host").toString();
//	static String port = prop.getRemoteProperties("database.bscs.port").toString();
//	static String databaseName = prop.getRemoteProperties("database.bscs.databasename").toString();

	
	static Connection conn = null;

	static public enum DataBaseSchema {
	    BSCS,
	}
	
	public static Connection getConnection(DataBaseSchema shema) throws SQLException {
		try {
			System.out.println("system property: "+ password);
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