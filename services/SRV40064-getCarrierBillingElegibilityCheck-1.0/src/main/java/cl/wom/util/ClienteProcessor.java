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

	public void process(Exchange exchange) throws Exception {

		Map<String, Object> row = exchange.getIn().getBody(Map.class);

		Cliente cliente = new Cliente();

		if (row != null) {

			for (Entry<String, Object> entry : row.entrySet()) {
				System.out.println(entry.getKey() + "::" + entry.getValue());
			}
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
			cliente.setDnNum((String) row.get("DN_NUM"));
			cliente.setContador((BigDecimal)row.get("CONTADOR"));
			cliente.setIdOferta((String) row.get("ID_OFERTA"));
			cliente.setDesOferta((String) row.get("DES_OFERTA"));
			cliente.setIdOccBscs((String)row.get("ID_OCC_BSCS"));
			cliente.setMesesAntiguedad((BigDecimal)row.get("MESES_ANTIGUEDAD"));
			cliente.setValorMinimo((BigDecimal)row.get("VALOR_MINIMO_PLAN"));
			cliente.setFecDesde((Date) row.get("FEC_DESDE"));
			cliente.setFecHasta((Date) row.get("FEC_HASTA"));

			cliente.setCount((BigDecimal) row.get("COUNT(1)"));

		}else {
			System.err.println("viene vacio");
		}

		exchange.getOut().setBody(cliente);
	
		
	}




}