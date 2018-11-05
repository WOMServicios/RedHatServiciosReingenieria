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
import cl.wom.middleware.vo.Refund;

public class CarrierRefundDAO {
	
	Connection conWAPP = null;
	Connection conBSCS = null;
	Statement stmtWAPP;
	Statement stmtBCSC;

	PropertiesUtil sqlProperties = new PropertiesUtil();
	
	
	public  Refund getFacturacionRefund(String userId, String payment) throws ServiceError {

		userId = userId == null ? "" : userId;
		payment = payment == null ? "" : payment;	
		
		Refund refund = null;

		try {

			conWAPP = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			conBSCS = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			stmtWAPP = conWAPP.createStatement();
			stmtBCSC = conBSCS.createStatement();

			// TODO cambiar query archivo parametros
			String queryRefund = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryRefund"),userId,payment);
			ResultSet rsContRefund = stmtWAPP.executeQuery(queryRefund);
			rsContRefund.next();

			if (rsContRefund.getInt("CONTADOR") > 0) {
				throw new ServiceError("ALREADY_REFUNDED");
			} else {
				String checkCharge = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.checkCharge"),userId,payment);
				ResultSet rsContCharge = stmtWAPP.executeQuery(checkCharge);
				rsContCharge.next();

				if (rsContCharge.getInt("CONTADOR") != 0) {
					throw new ServiceError("USER_NOT_ENABLED");
				} else {
					String queryDatos = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryDatos"),userId);

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
							refund = new Refund();
							refund.setMsisdn(rsDatos.getString("MSISDN"));
							refund.setShDesPlan(rsDatos.getString("SHDES_PLAN"));
							refund.setCustomerId(rsDatos.getInt("CUSTOMER_ID"));
							refund.setCodId(rsDatos.getInt("CO_ID"));
							refund.setRateplan(rsDatos.getInt("RATE_PLAN"));
							refund.setTipoContrato(rsDatos.getString("TIPO_CONTRATO"));
							refund.setEstado(rsDatos.getString("ESTADO"));
							refund.setFechaActivacion(rsDatos.getDate("FECHA_ACTIVACION"));
							refund.setFechaDesactivacion(rsDatos.getDate("FECHA_DESACTIVACION"));
						
						}
					}
				}
			}
			return refund;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conWAPP != null || conBSCS != null)
				try {
					conWAPP.close();
					conBSCS.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return refund;
	}

	public String insertCarrierRefund(String requestId, String bangoTransactionId, String merchantTransactionId,
			String userId, Integer amount, String currency,
			String responseCode, String responseMessage, String occId) throws ClassNotFoundException, SQLException {
		
		String paymentProviderTransactionId = "";
		String querySecuencia = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.querySecuencia"),0);
		System.out.println("querySecuencia: "+querySecuencia);
		ResultSet rsSecuencia = stmtWAPP.executeQuery(querySecuencia);
		
		if (rsSecuencia.next()) 
			paymentProviderTransactionId = rsSecuencia.getString("SECUENCIA");
		
		
		try {
			conWAPP = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			
			String queryInsert = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryInsert"),requestId,bangoTransactionId,merchantTransactionId,paymentProviderTransactionId,userId,amount,currency,responseCode,responseMessage,occId);
			stmtWAPP.executeUpdate(queryInsert);	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conWAPP != null)
				try {
					conWAPP.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return paymentProviderTransactionId;
	}
}
