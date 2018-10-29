package cl.wom.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import cl.wom.exception.services.ServiceError;
import cl.wom.util.PropertiesUtil;

public class ConnectionFactory {
	static Connection conn = null;
	static PropertiesUtil util = new PropertiesUtil();
	static Properties prop = util.getPropertiesEnvironment("APP_ENV");

	static public enum DataBaseSchema {
		BSCS,
	    WAPPL;
	}
	
	public static Connection getConnection(DataBaseSchema shema) throws ServiceError  {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String user = "";
		String password = "";
		String host = "";
		String port = "";
		String databaseName = "";
		
		Properties props = new Properties();

		switch (shema) {
			case BSCS:
				 user = prop.getProperty("database.replica.username");
				 password = prop.getProperty("database.replica.password");
				 host = prop.getProperty("database.replica.host");
				 port = prop.getProperty("database.replica.port");
				 databaseName = prop.getProperty("database.replica.name");
				 
				 props.setProperty("oracle.net.CONNECT_TIMEOUT","2000");
				 props.setProperty("user", user);
				 props.setProperty("password", password);
				 
			try {
				return DriverManager.getConnection ("jdbc:oracle:thin:@" + host + ":" + port + ":" + databaseName, props);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServiceError("455");
			}
			case WAPPL:
				 user = prop.getProperty("database.wappl.username");
				 password = prop.getProperty("database.wappl.password");
				 host = prop.getProperty("database.wappl.host");
				 port = prop.getProperty("database.wappl.port");
				 databaseName = prop.getProperty("database.wappl.name");
				 
				 props.setProperty("oracle.net.CONNECT_TIMEOUT","2000");
				 props.setProperty("user", user);
				 props.setProperty("password", password);
				 
			try {
				return DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port + ":" + databaseName, props);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServiceError("455");
			}				
			default:
			return null;
		}
	}
}