package cl.wom.database;

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
				 
				 c.setMsisdn(rs.getString("MSISDN"));
					c.setCustomerId(rs.getString("CUSTOMER_ID"));
					c.setCodId(rs.getString("CO_ID"));

					c.setRateplan(rs.getString("RATE_PLAN"));

					c.setTipoContrato(rs.getString("TIPO_CONTRATO"));
					c.setEstado(rs.getString("ESTADO"));
					c.setFechaActivacion(rs.getDate("FECHA_ACTIVACION"));
				 

			 }
			stm.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase cDaoImple, método obtener");
			e.printStackTrace();
		}
		
		return c;
	}

}
