package cl.wom.middleware.bean;


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

}
