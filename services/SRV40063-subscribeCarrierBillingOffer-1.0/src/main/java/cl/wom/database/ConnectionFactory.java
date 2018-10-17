package cl.wom.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import cl.wom.util.Util;

public class ConnectionFactory {

	static Connection con = null;

	static public enum DataBaseSchema {
		BSCS, WAPPL;
	}

	static Properties prop = Util.getProperties("APP_ENV");

	public static Connection getConnection(DataBaseSchema shema) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		switch (shema) {
		case BSCS:
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@" + prop.getProperty("database.replica.host") + ":"
							+ prop.getProperty("database.replica.port") + ":" + prop.getProperty("database.replica.name"),
					prop.getProperty("database.replica.username"), prop.getProperty("database.replica.password"));
		case WAPPL:

			return DriverManager.getConnection(
					"jdbc:oracle:thin:@" + prop.getProperty("database.wappl.host") + ":"
							+ prop.getProperty("database.wappl.port") + ":" + prop.getProperty("database.wappl.name"),
					prop.getProperty("database.wappl.username"), prop.getProperty("database.wappl.password"));

		default:
			return null;
		}

	}

}
