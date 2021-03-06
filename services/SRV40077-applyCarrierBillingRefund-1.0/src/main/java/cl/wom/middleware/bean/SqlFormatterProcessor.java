package cl.wom.middleware.bean;


import java.sql.SQLException;

import org.apache.camel.Exchange;

import cl.wom.exception.services.ServiceError;
import cl.wom.middleware.dao.CarrierRefundDAO;
import cl.wom.middleware.vo.Refund;


public class SqlFormatterProcessor {

	public Refund sqlParserCarrierRefund(Exchange ex) throws ServiceError {
		
		String userId = (String) ex.getIn().getHeader("userId");
		String payment = (String) ex.getIn().getHeader("paymentProviderTransactionId");
		
		System.out.println(userId);
		System.out.println(payment);
		
		CarrierRefundDAO carrierRefundDAO = new CarrierRefundDAO();
		Refund refund = carrierRefundDAO.getFacturacionRefund(userId, payment);
		
		
		
		return refund;
		
	}
	
	
	public void sqlInsertCarrierRefund(Exchange ex) throws ClassNotFoundException, SQLException {
		
		String requestId = (String) ex.getIn().getHeader("requestId");
		String bangoTransactionId = (String) ex.getIn().getHeader("bangoTransactionId");
		String merchantTransactionId = (String) ex.getIn().getHeader("merchantTransactionId");
		String userId = (String) ex.getIn().getHeader("userId");
		Integer amount = (Integer) ex.getIn().getHeader("amount");
		String currency = (String) ex.getIn().getHeader("currency");
		String responseCode = (String) ex.getIn().getHeader("responseCode");
		String responseMessage = (String) ex.getIn().getHeader("responseMessage");
		String occId = (String) ex.getIn().getHeader("occId");
		
		CarrierRefundDAO carrierRefundDAO = new CarrierRefundDAO();
		String paymentProviderTransactionId = carrierRefundDAO.insertCarrierRefund(requestId,bangoTransactionId,merchantTransactionId,userId,amount,currency,responseCode,responseMessage,occId);
		
		ex.getIn().setHeader("paymentProviderTransactionId", paymentProviderTransactionId);
	}

}
