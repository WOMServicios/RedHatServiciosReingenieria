package cl.wom.middleware.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.util.PropertiesUtil;
import cl.wom.middleware.vo.FacturacionRefund;

public class CarrierRefundDAO {
	
	//TODO aplicar log y no system out
	public FacturacionRefund getFacturacionRefund(String userId, String payment){
		
		userId = userId == null ? "" : userId;
		payment = payment == null ? "" : payment;
		
		System.out.println(userId);
		System.out.println(payment);
		
		
		FacturacionRefund facturacionRefund = null;
		Connection conn = null;
		Statement stmt;

		try {
			
			conn = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			stmt = conn.createStatement();
			
			
			//TODO cambiar query archivo parametros
			String queryRefund = "SELECT COUNT(USER_ID) AS CONTADOR FROM CARRIERBILLING.RECEP_PAGOS_CARRIER_BILING_TO WHERE USER_ID = '" + userId + "' AND PAYMENT_PROVIDER_TRANSACTION = '" + payment + "' AND RESPONSE_PAY ='OK' AND ACTION ='AUTORIZED'";
			System.out.println("queryRefund: "+queryRefund);
			
			
			ResultSet rsCont = stmt.executeQuery(queryRefund);
			
			if(rsCont.next()) {
				System.out.println("Contador: " + rsCont.getInt("CONTADOR"));
				
				if (rsCont.getInt("CONTADOR") > 0) {
					

					//LOGICA
					
					
					
					
				}
			}
	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
				
		return facturacionRefund;
	}


}
