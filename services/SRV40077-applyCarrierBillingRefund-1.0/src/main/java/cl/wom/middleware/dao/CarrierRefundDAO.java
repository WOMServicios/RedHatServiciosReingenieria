package cl.wom.middleware.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cl.wom.exception.services.ServiceError;
import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.vo.Refund;

public class CarrierRefundDAO {

	// TODO aplicar log y no system out
	public  Refund getFacturacionRefund(String userId, String payment) throws ServiceError {

		userId = userId == null ? "" : userId;
		payment = payment == null ? "" : payment;

		Refund refund = null;

		Connection conWAPP = null;
		Connection conBSCS = null;
		Statement stmtWAPP;
		Statement stmtBCSC;

		try {

			conWAPP = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			conBSCS = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			stmtWAPP = conWAPP.createStatement();
			stmtBCSC = conBSCS.createStatement();

			// TODO cambiar query archivo parametros
			String queryRefund = "SELECT COUNT(USER_ID) AS CONTADOR FROM CARRIERBILLING.RECEP_PAGOS_CARRIER_BILING_TO WHERE USER_ID = TRIM('"
					+ userId + "') AND PAYMENT_PROVIDER_TRANSACTION = TRIM('" + payment
					+ "') AND RESPONSE_PAY ='OK' AND ACTION ='REFUND'";
			System.out.println("queryRefund: " + queryRefund + "\n");
			ResultSet rsContRefund = stmtWAPP.executeQuery(queryRefund);
			rsContRefund.next();

			if (rsContRefund.getInt("CONTADOR") > 0) {
				// TODO enviar al camel para velocity
				throw new ServiceError("ALREADY_REFUNDED");
			} else {
				String queryCharge = "SELECT COUNT(USER_ID) AS CONTADOR FROM CARRIERBILLING.RECEP_PAGOS_CARRIER_BILING_TO WHERE USER_ID = TRIM('"
						+ userId + "') AND PAYMENT_PROVIDER_TRANSACTION = TRIM('" + payment
						+ "') AND RESPONSE_PAY ='OK' AND ACTION ='CHARGE'";
				System.out.println("queryCharge: " + queryCharge + "\n");
				ResultSet rsContCharge = stmtWAPP.executeQuery(queryCharge);
				rsContCharge.next();

				if (rsContCharge.getInt("CONTADOR") == 0) {
					// TODO enviar al camel para velocity
					throw new ServiceError("USER_NOT_ENABLED");
				} else {
					String queryDatos = "select a.dn_num as msisdn, d.shdes as shdes_plan, c.customer_id as customer_id, c.co_id as co_id, c.tmcode as rate_plan, decode(d.ATS_PREPAID_IND,'M','Control','N','Postpaid','P','Prepaid') as tipo_Contrato, c.CH_STATUS as estado, c.CO_ACTIVATED as fecha_activacion, b.cs_deactiv_date as fecha_desactivacion from sysadm.directory_number a, sysadm.contr_services_cap b, sysadm.contract_all c, sysadm.rateplan d where c.co_id = '"
							+ userId
							+ "'and a.dn_id = b.dn_id and b.sncode = 3 and b.co_id = c.co_id AND b.cs_deactiv_date IS NULL and c.tmcode = d.tmcode";
					System.out.println("queryDatos: " + queryDatos + "\n");

					ResultSet rsDatos = stmtBCSC.executeQuery(queryDatos);

					if (rsDatos.next()) {

						if (rsDatos.getInt("CO_ID") == 0) {
							// TODO enviar al camel para velocity
							throw new ServiceError("USER_NOT_FUND");
						} else if (!rsDatos.getString("TIPO_CONTRATO").equals("Postpaid")) {
							// TODO enviar al camel para velocity
							throw new ServiceError("USER_NOT_ENABLED");
						} else if (rsDatos.getString("estado").equals("s")) {
							// TODO enviar al camel para velocity
							throw new ServiceError("USER_SUSPENDED");
						} else if (rsDatos.getString("estado").equals("d") || rsDatos.getString("estado").equals("o")) {
							// TODO enviar al camel para velocity
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

}
