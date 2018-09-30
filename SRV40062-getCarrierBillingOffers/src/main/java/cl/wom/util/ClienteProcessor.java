package cl.wom.util;

import java.math.BigDecimal;
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

		for (Entry<String, Object> entry : row.entrySet()) {
			System.out.println(entry.getKey() + "::" + entry.getValue());
		}

		Cliente cliente = new Cliente();

		cliente.setRut((String) row.get("RUT"));
		cliente.setCustomerId((BigDecimal) row.get("CUSTOMER_ID"));
		cliente.setCustomerIdHigh((BigDecimal) row.get("CUSTOMER_ID_HIGH"));
		cliente.setContractId((BigDecimal) row.get("CONTRACT_ID"));
		cliente.setNumCelular((String) row.get("NUM_CELULAR"));
		cliente.setTipoContrato((String) row.get("TIPO_CONTRATO"));
		cliente.setRateplan((String) row.get("RATEPLAN"));
		cliente.setAntiguedad((BigDecimal) row.get("ANTIGUEDAD"));
		cliente.setCiclo((String) row.get("CICLO"));
		cliente.setTipoContrato((String) row.get("ESTADO_CONTRATO"));
		cliente.setFechaActivacion((Timestamp) row.get("FECHA_ACTIVACION"));
		cliente.setMercado((String) row.get("MERCADO"));
		cliente.setCargoBasico((BigDecimal) row.get("CARGO_BASICO"));

		exchange.getOut().setBody(cliente);
	}

}
