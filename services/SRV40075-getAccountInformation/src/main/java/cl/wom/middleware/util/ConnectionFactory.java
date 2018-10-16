package cl.wom.middleware.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
	
	static Connection conn = null;

	static public enum DataBaseSchema {
	    BSCS,
	    WAPPL;
	}
	
	public static Connection getConnection(DataBaseSchema shema) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		System.out.println("system property: "+System.getProperty("database.bscs.host"));
		
		switch (shema) {
			case BSCS:
				return DriverManager.getConnection("jdbc:oracle:thin:@" + System.getProperty("database.bscs.host") + ":" + System.getProperty("database.bscs.port") + ":" + System.getProperty("database.bscs.databasename"), System.getProperty("database.bscs.username"),	System.getProperty("database.bscs.password"));
			case WAPPL:
				return DriverManager.getConnection("jdbc:oracle:thin:@" + System.getProperty("database.wappl.host") + ":" + System.getProperty("database.wappl.port") + ":" + System.getProperty("database.wappl.name"), System.getProperty("database.wappl.username"),	System.getProperty("database.wappl.password"));				
			
			default:
			return null;
		}
	}
}