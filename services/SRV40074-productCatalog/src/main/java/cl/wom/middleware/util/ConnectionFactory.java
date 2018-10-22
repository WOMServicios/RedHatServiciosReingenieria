package cl.wom.middleware.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionFactory {
	static PropertiesUtil util = new PropertiesUtil();
	static Properties prop = util.getProperties("APP_ENV");
	static Properties sql = Util.getProperties("SQL_ENV");
	static String user = prop.getProperty("database.bscs.username");
	static String password = prop.getProperty("database.bscs.password");
	static String host = prop.getProperty("database.bscs.host");
	static String port = prop.getProperty("database.bscs.port");
	static String databaseName = prop.getProperty("database.bscs.databasename");
	
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