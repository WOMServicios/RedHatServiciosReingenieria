package cl.wom.database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;

import cl.wom.beans.Cliente;
import cl.wom.exception.services.ServiceError;

public class ClienteDaoImpl implements IClienteDao {

	@Override
	public Cliente getDatosFacturacion(String sql, Connection co) {
		Statement stm = null;
		ResultSet rs = null;

		Cliente cliente = null;

		try {

			stm = co.createStatement();
			rs = stm.executeQuery(sql);

			cliente = new Cliente();
			if (rs.next()) {
				cliente.setMsisdn(rs.getString("MSISDN"));
				cliente.setCustomerId(rs.getString("CUSTOMER_ID"));
				cliente.setCodId(rs.getString("CO_ID"));
				cliente.setRateplan(rs.getString("RATE_PLAN"));
				cliente.setTipoContrato(rs.getString("TIPO_CONTRATO"));
				cliente.setEstado(rs.getString("ESTADO"));
				cliente.setFechaActivacion(rs.getDate("FECHA_ACTIVACION"));
				cliente.setFechaDesactivacion(rs.getDate("FECHA_DESACTIVACION"));

			}
			stm.close();
			rs.close();
			co.close();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		}

		return cliente;
	}

	@Override
	public String paymentTransactionId(String sql, Connection co) {
		Statement stm = null;
		ResultSet rs = null;

		Cliente cliente = null;

		try {

			stm = co.createStatement();
			rs = stm.executeQuery(sql);

			cliente = new Cliente();
			if (rs.next()) {

				cliente.setSecuencia(rs.getString("SECUENCIA"));

			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return cliente.getSecuencia();
	}

	@Override
	public boolean insertAuthorizeCarrierbilling(String sql,Cliente cliente, Connection con) {
	boolean authorizedOk=false;
	
	



	PreparedStatement  stm= null;
		try {
			;
			stm=con.prepareStatement(sql);  
			stm.setString(1, cliente.getRequestId());
			stm.setString(2, cliente.getBangoTransactionId());
			stm.setString(3, cliente.getMerchanTransactionId());
			stm.setString(4, cliente.getSecuencia());
			stm.setString(5, cliente.getUserId());
			stm.setInt(6, Integer.parseInt(cliente.getUserId()));
			stm.setString(7, cliente.getCurrency());
			stm.setString(8, cliente.getResponsePay());
			stm.setString(9, cliente.getDescriptionResponsePay());
			java.util.Date date = Calendar.getInstance().getTime();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			stm.setDate(10, sqlDate);
			stm.setString(11, cliente.getMerchantAccountKey());
			stm.setString(12, cliente.getProductKey());
			stm.setString(13, cliente.getProductDescription());
			stm.setString(14, cliente.getProductCategory());
			stm.setString(15, cliente.getSupportContact());
			stm.setString(16, cliente.getAction());
				 
		if(stm.executeUpdate()==1) {
			authorizedOk=true;
		}
		stm.close();
	
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return authorizedOk;
	}
}
