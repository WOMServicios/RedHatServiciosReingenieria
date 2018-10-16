package cl.wom.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
	
	static Connection conn = null;

	static public enum DataBaseSchema {
	    BSCS,
	    WAPPL,
		BSCSUAT;
	}
	
	public static Connection getConnection(DataBaseSchema shema) throws ClassNotFoundException, SQLException {
		ResourceBundle rb = ResourceBundle.getBundle("application.properties");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		switch (shema) {
			case BSCS:
				return DriverManager.getConnection("jdbc:oracle:thin:@" + rb.getString("database.replica.host") + ":" + rb.getString("database.replica.port") + ":" + rb.getString("database.replica.name"), rb.getString("database.replica.username"),	rb.getString("database.replica.password"));
			case WAPPL:
				return DriverManager.getConnection("jdbc:oracle:thin:@" + rb.getString("database.wappl.host") + ":" + rb.getString("database.wappl.port") + ":" + rb.getString("database.wappl.name"), rb.getString("database.wappl.username"),	rb.getString("database.wappl.password"));				
			case BSCSUAT:
				return DriverManager.getConnection("jdbc:oracle:thin:@" + rb.getString("camel.sql.host") + ":" + rb.getString("camel.sql.port") + ":" + rb.getString("camel.sql.name"), rb.getString("camel.sql.user"),	rb.getString("camel.sql.pass"));
			default:
			return null;
		}
	}
}


