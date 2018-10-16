package cl.wom.middleware.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	
	
	
	static Connection conn = null;

	static public enum DataBaseSchema {
	    BSCS,
	    WAPPL;
	}
	
	public static Connection getConnection(DataBaseSchema shema, Properties prop) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String user = "";
		String password = "";
		String host = "";
		String port = "";
		String databaseName = "";
		
		System.out.println(prop.getProperty("database.bscs.user"));
		System.out.println(prop.getProperty("database.bscs.password"));
		System.out.println(prop.getProperty("database.bscs.host"));
		System.out.println(prop.getProperty("database.bscs.port"));
		System.out.println(prop.getProperty("database.bscs.databaseName"));
		
		switch (shema) {
			case BSCS:
				 user = prop.getProperty("database.bscs.user");
				 password = prop.getProperty("database.bscs.password");
				 host = prop.getProperty("database.bscs.host");
				 port = prop.getProperty("database.bscs.port");
				 databaseName = prop.getProperty("database.bscs.databaseName");
				return DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + databaseName, user,	password);
				//return DriverManager.getConnection("jdbc:oracle:thin:@" + System.getProperty("database.bscs.host") + ":" + System.getProperty("database.bscs.port") + ":" + System.getProperty("database.bscs.databasename"), System.getProperty("database.bscs.username"),	System.getProperty("database.bscs.password"));
			case WAPPL:
				 user = prop.getProperty("database.wappl.user");
				 password = prop.getProperty("database.wappl.password");
				 host = prop.getProperty("database.wappl.host");
				 port = prop.getProperty("database.wappl.port");
				 databaseName = prop.getProperty("database.wappl.databaseName");
				return DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + databaseName, user,	password);				
				//return DriverManager.getConnection("jdbc:oracle:thin:@" + System.getProperty("database.wappl.host") + ":" + System.getProperty("database.wappl.port") + ":" + System.getProperty("database.wappl.name"), System.getProperty("database.wappl.username"),	System.getProperty("database.wappl.password"));			
			default:
			return null;
		}
	}
}