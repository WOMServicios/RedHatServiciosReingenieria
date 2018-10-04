package cl.wom.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import cl.wom.beans.Cliente;

public class ClienteProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Map<String, Object> row = exchange.getIn().getBody(Map.class);



		Cliente cliente = new Cliente();

		if(row != null) {
			for (Entry<String, Object> entry : row.entrySet()) {
				System.out.println(entry.getKey() + "::" + entry.getValue());
			}
			
		cliente.setNumCelular((String) row.get("NUM_CELULAR"));
		cliente.setContratoId((String) row.get("CONTRATO_ID"));
		cliente.setPaymentProviderTransaction((String ) row.get("PAYMENT_PROVIDER_TRANSACTION"));
		cliente.setFechaIngreso((Date)row.get("FECHA_INGRESO"));
		cliente.setBangoTransactionId((String) row.get("BANGO_TRANSACTION_ID"));
		cliente.setMerchanTransactionId((String)row.get("MERCHAN_TRANSACTION_ID"));
		cliente.setAmount((BigDecimal) row.get("AMOUNT"));
		cliente.setUserId((String) row.get("USER_ID"));
		cliente.setResponsePay((String) row.get("RESPONSE_PAY"));
		cliente.setDatePay((Date) row.get("DATE_PAY"));
		cliente.setIdOferta((String) row.get("ID_OFERTA"));
			
			
		}else {
			System.err.println("vacio");
		}
		

		exchange.getOut().setBody(cliente);
	}

}
