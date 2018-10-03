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
			
			cliente.setMercado((String) row.get("MERCADO"));
			cliente.setIdOferta((String) row.get("ID_OFERTA"));
			cliente.setDesOferta((String)row.get("DES_OFERTA"));
			cliente.setIdOccBscs((String) row.get("ID_OCC_BSCS"));
			cliente.setIdOccCancel((String)row.get("ID_OCC_CANCEL"));
			cliente.setIdOccRefund((String )row.get("ID_OCC_REFUND"));
			cliente.setMesesAntiguedad((BigDecimal)row.get("MESES_ANTIGUEDAD"));
			cliente.setValorMinimoPlan((BigDecimal) row.get("VALOR_MINIMO_PLAN"));
			cliente.setFecDesde((Date) row.get("FEC_DESDE"));
			cliente.setFecHasta((Date) row.get("FEC_DESDE"));
			
			
			
		}else {
			System.err.println("vacio");
		}
		

		exchange.getOut().setBody(cliente);
	}

}
