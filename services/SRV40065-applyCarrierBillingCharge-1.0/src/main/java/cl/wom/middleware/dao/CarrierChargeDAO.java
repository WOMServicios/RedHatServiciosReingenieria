package cl.wom.middleware.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import cl.wom.exception.services.ServiceError;
import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.PropertiesUtil;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.vo.Charge;

public class CarrierChargeDAO {
	
	Connection conWAPP = null;
	Connection conBSCS = null;
	Statement stmtWAPP;
	Statement stmtBCSC;
	
	PropertiesUtil sqlProperties = new PropertiesUtil();
	

	
	public  Charge getFacturacionCharge(String userId, String payment) throws ServiceError {
		
		userId = userId == null ? "" : userId;
		payment = payment == null ? "" : payment;
		
		Charge charge = null;

		try {

			conWAPP = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			conBSCS = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			stmtWAPP = conWAPP.createStatement();
			stmtBCSC = conBSCS.createStatement();
			
			

			// TODO cambiar query archivo parametros
			String queryAuthorized = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryAuthorized"),userId,payment);
			//System.out.println(queryAuthorized);
			ResultSet rsContCharge = stmtWAPP.executeQuery(queryAuthorized);
			rsContCharge.next();

			if (rsContCharge.getInt("count") == 0) {
				throw new ServiceError("ALREADY_REFUNDED");
			} else {
					String queryDatos = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryDatos"),userId);
					//System.out.println(queryDatos);
					ResultSet rsDatos = stmtBCSC.executeQuery(queryDatos);

					if (rsDatos.next()) {
						

						if (rsDatos.getInt("CO_ID") == 0) {
							throw new ServiceError("USER_NOT_FUND");
						} else if (!rsDatos.getString("TIPO_CONTRATO").equals("Postpaid")) {
							throw new ServiceError("USER_NOT_ENABLED");
						} else if (rsDatos.getString("estado").equals("s")) {
							throw new ServiceError("USER_SUSPENDED");
						} else if (rsDatos.getString("estado").equals("d") || rsDatos.getString("estado").equals("o")) {
							throw new ServiceError("USER_NOT_ENABLED");
						} else {
							charge = new Charge();
							charge.setMsisdn(rsDatos.getString("MSISDN"));
							charge.setShDesPlan(rsDatos.getString("SHDES_PLAN"));
							charge.setCustomerId(rsDatos.getLong("CUSTOMER_ID"));
							charge.setCodId(rsDatos.getLong("CO_ID"));
							charge.setRateplan(rsDatos.getInt("RATE_PLAN"));
							charge.setTipoContrato(rsDatos.getString("TIPO_CONTRATO"));
							charge.setEstado(rsDatos.getString("ESTADO"));
							charge.setFechaActivacion(rsDatos.getDate("FECHA_ACTIVACION"));
							charge.setFechaDesactivacion(rsDatos.getDate("FECHA_DESACTIVACION"));
						}
					}
				}
			return charge;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServiceError("455");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceError("455");
		} finally {
			if (conWAPP != null || conBSCS != null)
				try {
					conWAPP.close();
					conBSCS.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServiceError("455");
				}
		}
	}

	public String insertCarrierCharge(String requestId, String bangoTransactionId, String merchantTransactionId, 
			String userId, Integer amount, String currency,
			String responseCode, String responseMessage, String occId) throws ClassNotFoundException, SQLException, ServiceError {
		
		
		String paymentProviderTransactionId = "";
		String querySecuencia = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.querySecuencia"),0);
		System.out.println("querySecuencia: "+querySecuencia);
		
		conWAPP = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
		stmtWAPP = conWAPP.createStatement();
		
		ResultSet rsSecuencia = stmtWAPP.executeQuery(querySecuencia);
		if (rsSecuencia.next()) 
			paymentProviderTransactionId = rsSecuencia.getString("SECUENCIA");
		
		
		try {
			conWAPP = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			
			String queryInsert = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryInsert"),requestId,bangoTransactionId,merchantTransactionId,paymentProviderTransactionId,userId,amount,currency,responseCode,responseMessage,occId);
			System.out.println(queryInsert);
			stmtWAPP.executeUpdate(queryInsert);	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServiceError("455");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceError("455");
		} finally {
			if (conWAPP != null)
				try {
					conWAPP.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServiceError("455");
				}
		}
		return paymentProviderTransactionId;
	}
}
