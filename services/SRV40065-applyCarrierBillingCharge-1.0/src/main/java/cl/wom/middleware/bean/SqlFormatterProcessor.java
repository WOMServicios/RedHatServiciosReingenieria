package cl.wom.middleware.bean;


import java.sql.SQLException;

import org.apache.camel.Exchange;

import cl.wom.exception.services.ServiceError;
import cl.wom.middleware.dao.CarrierChargeDAO;
import cl.wom.middleware.vo.Charge;


public class SqlFormatterProcessor {

	public Charge sqlParserCarrierCharge(Exchange ex) throws ServiceError {
		
		String userId = (String) ex.getIn().getHeader("userId");
		String payment = (String) ex.getIn().getHeader("paymentProviderTransactionId");
		
		System.out.println(userId);
		System.out.println(payment);
		
		CarrierChargeDAO carrierRefundDAO = new CarrierChargeDAO();
		Charge charge = carrierRefundDAO.getFacturacionCharge(userId, payment);
		
		
		
		return charge;
		
	}
	
	
	public void sqlInsertCarrierCharge(Exchange ex) throws ClassNotFoundException, SQLException {
		
		String requestId = (String) ex.getIn().getHeader("requestId");
		String bangoTransactionId = (String) ex.getIn().getHeader("bangoTransactionId");
		String merchantTransactionId = (String) ex.getIn().getHeader("merchantTransactionId");
		String userId = (String) ex.getIn().getHeader("userId");
		Integer amount = (Integer) ex.getIn().getHeader("amount");
		String currency = (String) ex.getIn().getHeader("currency");
		String responseCode = (String) ex.getIn().getHeader("codigo");
		String responseMessage = (String) ex.getIn().getHeader("mensaje");
		String occId = (String) ex.getIn().getHeader("occId");
		
		CarrierChargeDAO carrierChargeDAO = new CarrierChargeDAO();
		String paymentProviderTransactionId = carrierChargeDAO.insertCarrierCharge(requestId,bangoTransactionId,merchantTransactionId,userId,amount,currency,responseCode,responseMessage,occId);
		
		
		ex.getIn().setHeader("paymentProviderTransactionId", paymentProviderTransactionId);
	}

}
