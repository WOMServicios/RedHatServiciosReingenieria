package cl.wom.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cl.wom.database.ConnectionFactory.DataBaseSchema;

public class Ejecutasql {
	
	public static void main() throws ClassNotFoundException, SQLException {
		
		Connection conn = ConnectionFactory.getConnection(DataBaseSchema.BSCSUAT);
		
		Statement stmt = null;
		String query = "select\n" + 
				"a.cscompregno      as rut,\n" + 
				"a.CUSTOMER_ID      as accountId,\n" + 
				"a.CUSTOMER_ID_HIGH as accountIdHigh,\n" + 
				"a.cslevel          as csLevel,\n" + 
				"a.CUSTCODE         as custCode,\n" + 
				"a.CSTYPE           as accountType,\n" + 
				"a.CSACTIVATED      as accountActivate,\n" + 
				"a.CSDEACTIVATED    as accountDeactivate,\n" + 
				"a.CUSTOMER_ID_EXT  as externalAccountId,\n" + 
				"a.CSST             as state,\n" + 
				"a.DOCTYPE_ID       as docTypeId,\n" + 
				"b.DOCTYPE_DESC     as docTypeDesc,\n" + 
				"b.DOCTYPE_OUTPUT_CODE as docTypeOutputCode,\n" + 
				"a.CUSTOMER_ID      as accountId_biilcycle,\n" + 
				"d.BILLCYCLE        as billCycle_billcycle,\n" + 
				"d.description      as billCycleDes_billcycle,\n" + 
				"d.interval_type    as intervalType,\n" + 
				"d.last_run_date    as lastRunDate,\n" + 
				"d.bch_run_date     as bchRunDate\n" + 
				"from\n" + 
				"    sysadm.customer_all           a,\n" + 
				"    sysadm.DOCUMENT_TYPE_SII_CODE b,\n" + 
				"    SYSADM.BILLCYCLE_ACTUAL_VIEW  c,\n" + 
				"    SYSADM.BILLCYCLES             d\n" + 
				"where\n" + 
				"    a.cscompregno = '010567335'--variable\n" + 
				"and a.DOCTYPE_ID  = b.DOCTYPE_ID\n" + 
				"and a.CUSTOMER_ID = c.CUSTOMER_ID\n" + 
				"and c.billcycle   = d.billcycle;";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String rut = rs.getString("RUT");
				System.out.println(rut);
			}
		} cacth (SQLException e){
			
		}
		
	}

}
