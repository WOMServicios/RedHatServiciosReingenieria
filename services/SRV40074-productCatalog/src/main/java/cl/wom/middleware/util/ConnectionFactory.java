package cl.wom.middleware.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	static Connection conn = null;
	static Properties prop = PropertiesUtil.getProperties("APP_ENV");

	static public enum DataBaseSchema {
	    BSCS,
	    WAPPL;
	}
	
	public static Connection getConnection(DataBaseSchema shema) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String user = "";
		String password = "";
		String host = "";
		String port = "";
		String databaseName = "";
		
		Properties props = new Properties();

		switch (shema) {
			case BSCS:
				 user = prop.getProperty("database.bscs.username");
				 password = prop.getProperty("database.bscs.password");
				 host = prop.getProperty("database.bscs.host");
				 port = prop.getProperty("database.bscs.port");
				 databaseName = prop.getProperty("database.bscs.databasename");
				 
				 props.setProperty("oracle.net.CONNECT_TIMEOUT","2000");
				 props.setProperty("user", user);
				 props.setProperty("password", password);
				 
				 return DriverManager.getConnection ("jdbc:oracle:thin:@" + host + ":" + port + ":" + databaseName, props);
			default:
			return null;
		}
	}
}