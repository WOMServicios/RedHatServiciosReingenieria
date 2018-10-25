package cl.wom.middleware.bean;


import org.apache.camel.Exchange;

import cl.wom.middleware.dao.CarrierRefundDAO;
import cl.wom.middleware.vo.FacturacionRefund;


public class SqlFormatterProcessor {

	public String sqlParserCarrierRefund(Exchange ex) {
		
		String userId = (String) ex.getIn().getHeader("userId");
		String payment = (String) ex.getIn().getHeader("paymentProviderTransactionId");
		
		System.out.println(userId);
		System.out.println(payment);
		
		System.out.println("CARRIER");
		
		
		CarrierRefundDAO carrierRefundDAO = new CarrierRefundDAO();
		FacturacionRefund facturacionRefund = carrierRefundDAO.getFacturacionRefund(userId, payment);
		
		return null;
		
	}

}
