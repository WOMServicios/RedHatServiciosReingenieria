package cl.wom.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import cl.wom.beans.Cliente;


public class ClienteDaoImpl implements IClienteDao {


	@Override
	public Cliente getCliente(String sql,Connection co) {

	
		Statement stm= null;
		ResultSet rs=null;
		
		Cliente cliente=null;
		
		try {			
			
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
		
			 cliente=new Cliente();
			 if(rs.next()) {
				 
					cliente.setRut(rs.getString("RUT"));
					cliente.setCustomerId(rs.getString("CUSTOMER_ID"));
					cliente.setCustomerIdHigh(rs.getString("CUSTOMER_ID_HIGH"));
					cliente.setContractId(rs.getString("CONTRACT_ID"));
					cliente.setNumCelular(rs.getString("NUM_CELULAR"));
					cliente.setTipoContrato(rs.getString("TIPO_CONTRATO"));
					cliente.setRateplan(rs.getString("RATEPLAN"));
					cliente.setAntiguedad(rs.getString("ANTIGUEDAD"));
					cliente.setCiclo(rs.getString("CICLO"));
					cliente.setTipoContrato(rs.getString("ESTADO_CONTRATO"));
					cliente.setFechaActivacion(rs.getDate("FECHA_ACTIVACION"));
					cliente.setMercado(rs.getString("MERCADO"));

			 }
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
		System.err.println(cliente.toString());
		return cliente;
	}

}
