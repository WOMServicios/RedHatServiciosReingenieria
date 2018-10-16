package cl.wom.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cl.wom.database.ConnectionFactory.DataBaseSchema;

public class ConnectionFactory {

	
	
	
	static Connection con = null;
	static public enum DataBaseSchema {
	    BSCS,
	    WAPPL;
	}
	



	public static Connection getConnection(DataBaseSchema shema) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		
		switch (shema) {
			case BSCS:
				return DriverManager.getConnection("jdbc:oracle:thin:@" + "10.120.241.44" + ":" + "1550" + ":" + "BSCSDESA", "OPSH_BSCSREP_SYSADM_CBI","knh#5tgl20k0lpm.l");
			case WAPPL:
				return DriverManager.getConnection("jdbc:oracle:thin:@" + "10.120.148.136" + ":" + "1521" + ":" + "WAPPLDESA", "CARRIERBILLING","carrierdes09");

		default:
			return null;
		}
		
	}
	

	
}