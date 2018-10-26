package cl.wom.middleware.bean;


import org.apache.camel.Exchange;

import cl.wom.middleware.dao.CarrierRefundDAO;
import cl.wom.middleware.vo.FacturacionRefund;


public class SqlFormatterProcessor {

	public String sqlParserCarrierRefund(Exchange ex) {
		
		String userId = (String) ex.getIn().getHeader("userId");
		String payment = (String) ex.getIn().getHeader("paymet");
		
		userId="2495491";
		payment="PP-5a5164105ca648ceaef2bf1528503022";
		
		CarrierRefundDAO carrierRefundDAO = new CarrierRefundDAO();
		FacturacionRefund facturacionRefund = carrierRefundDAO.getFacturacionRefund(userId, payment);
		
		return null;
		
	}

}
