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
		if (row != null) {

			for (Entry<String, Object> entry : row.entrySet()) {
				System.out.println(entry.getKey() + "::" + entry.getValue());
			}
			
			cliente.setMsisdn((String) row.get("MSISDN"));
			cliente.setCustomerId((BigDecimal) row.get("CUSTOMER_ID"));
			cliente.setCodId((BigDecimal) row.get("CO_ID"));

			cliente.setRateplan((BigDecimal) row.get("RATE_PLAN:"));

			cliente.setTipoContrato((String) row.get("ESTADO_CONTRATO"));
			cliente.setFechaActivacion((Date) row.get("FECHA_ACTIVACION"));

		}

		exchange.getOut().setBody(cliente);

	}

}
