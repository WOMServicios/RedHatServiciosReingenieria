package cl.wom.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import cl.wom.beans.Cliente;


public class ClienteDaoImpl implements IClienteDao {


	@Override
	public Cliente getCliente(String sql,Connection con) {

	
		Statement stm= null;
		ResultSet rs=null;
		
		Cliente c=null;
		
		try {			
			
			stm=con.createStatement();
			rs=stm.executeQuery(sql);
		
			 c=new Cliente();
			 if(rs.next()) {
				c.setIdOferta(rs.getString(1));
				c.setNumCelular(rs.getString(2));
				c.setContratoId(rs.getString(3));
				c.setPaymentProviderTransaction(rs.getString(4));
				c.setFechaIngreso(rs.getDate(5));
				c.setBangoTransactionId(rs.getString(6));
				c.setMerchanTransactionId(rs.getString(7));
				c.setUserId(rs.getString(8));
				c.setAmount(rs.getString("AMOUNT"));
				c.setResponsePay(rs.getString(10));
   			c.setDatePay(rs.getDate(11));
			 }
			stm.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
		
		return c;
	}

}
