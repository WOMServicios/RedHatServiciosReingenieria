package cl.wom.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import cl.wom.beans.Cliente;


public class ClienteDaoImpl implements IClienteDao {


	@Override
	public Cliente getInfoSuscriptorCarrierBilling(String sql,Connection co) {

	
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
					cliente.setCargoBasico(rs.getString("CARGO_BASICO"));
			

			 }
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}

		return cliente;
	}

	@Override
	public int getSuscripcionesCarrierExist(String sql, Connection co) {
		Statement stm= null;
		ResultSet rs=null;
		
		Cliente cliente=null;
		
		try {			
			
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
		
			 cliente=new Cliente();
			 if(rs.next()) {
				 
				 cliente.setContador(rs.getString("CONTADOR"));
					
			

			 }
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}

		return Integer.parseInt(cliente.getContador());
	}

	@Override
	public String getCustomerContractMoreOld(String sql, Connection co) {
		Statement stm= null;
		ResultSet rs=null;
		
		Cliente cliente=null;
		
		try {			
			
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
		
			 cliente=new Cliente();
			 if(rs.next()) {
				 
				
				 cliente.setDnNum(rs.getString("DN_NUM"));
					
			

			 }
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}

		return cliente.getDnNum();
	}

	@Override
	public String getCustomerPagador(String sql, Connection co) {
		Statement stm= null;
		ResultSet rs=null;
		
		Cliente cliente=null;
		
		try {			
			
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
		
			 cliente=new Cliente();
			 if(rs.next()) {
				 
				
				 cliente.setCustomerId(rs.getString("CUSTOMER_ID"));
					
			

			 }
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}

		return cliente.getCustomerId();
	}

	@Override
	public Cliente getofertacarrier(String sql, Connection co) {

		Statement stm= null;
		ResultSet rs=null;
		
		Cliente cliente=null;
		
		try {			
			
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
		
			 cliente=new Cliente();
			 if(rs.next()) {
				 
				    cliente.setIdOferta(rs.getString("ID_OFERTA"));
					cliente.setDesOferta(rs.getString("DES_OFERTA"));
					cliente.setIdOccBscs(rs.getString("ID_OCC_BSCS"));
					cliente.setMesesAntiguedad(rs.getString("MESES_ANTIGUEDAD"));
					cliente.setValorMinimo(rs.getString("VALOR_MINIMO_PLAN"));
					cliente.setFecDesde(rs.getDate("FEC_DESDE"));
					cliente.setFecHasta(rs.getDate("FEC_HASTA"));
					
				
			

			 }
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}

		return cliente;
	}

	@Override
	public void insertaregelegcarrierbilling(String sql, Connection co) {
		
		System.err.println("query :" +sql);
		Statement stm= null;
		try {
			stm=co.createStatement();
			stm.executeUpdate(sql);
			stm.close();
		
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
			 
		
	

}

	@Override
	public int getrespuestaeligibilidad(String sql, Connection co) {
		Statement stm= null;
		ResultSet rs=null;
		
		Cliente cliente=null;
		
		try {			
			
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
		
			 cliente=new Cliente();
			 if(rs.next()) {
				 
				 cliente.setCount(rs.getString("COUNT(1)"));
					
			

			 }
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}

		return Integer.parseInt(cliente.getCount());
	}

	@Override
	public String paymentTransactionId(String sql, Connection co) {
		Statement stm= null;
		ResultSet rs=null;
		
		Cliente cliente=null;
		
		try {			
			
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
		
			 cliente=new Cliente();
			 if(rs.next()) {
				 
				 cliente.setSecuencia(rs.getString("SECUENCIA"));
					
			

			 }
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}

		return cliente.getSecuencia();
	}
}
