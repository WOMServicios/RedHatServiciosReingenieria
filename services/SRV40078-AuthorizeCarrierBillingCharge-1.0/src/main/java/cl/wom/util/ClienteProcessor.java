package cl.wom.util;

import java.sql.Connection;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import cl.wom.beans.Cliente;
import cl.wom.database.ClienteDaoImpl;
import cl.wom.database.ConnectionFactory;
import cl.wom.database.ConnectionFactory.DataBaseSchema;
import cl.wom.exception.services.ServiceError;


public class ClienteProcessor implements Processor {
	private ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();
	private Connection con;
	private Cliente cliente;

	@Override
	public void process(Exchange exchange) throws Exception {

		String headerBD = (String) exchange.getIn().getHeader("interface");
		System.out.println(headerBD);
		
		
		String sql = (String) exchange.getIn().getBody();

		if (headerBD.equals("getDatosFacturacion")) {
			
			
			
			String validacionLength=String.valueOf( exchange.getIn().getHeader("amount"));
			
			
			if (!ValidationUtil.isNumeric(validacionLength)) {
				throw new ServiceError("454");
			}
			if(exchange.getIn().getHeader("userId").toString().length()>=	22) {
				throw new ServiceError("452");
			}
			
		

			con = ConnectionFactory.getConnection(DataBaseSchema.BSCS);

			cliente = clienteDaoImpl.getDatosFacturacion(sql, con);
			
		
			
			if(cliente.getCodId()==null) {
				throw new ServiceError("USER_NOT_FOUND");
			}
			if(!cliente.getTipoContrato().equals("Postpaid")) {
				throw new ServiceError("USER_NOT_ENABLED");
			}
			if(cliente.getEstado().equals("s")) {
				throw new ServiceError("USER_SUSPENDED");
			}
			if(cliente.getEstado().equals("d") || cliente.getEstado().equals("o")) {
				throw new ServiceError("USER_NOT_ENABLED");
			}
			exchange.setProperty("requestIdProperty", exchange.getIn().getHeader("requestId"));
			exchange.setProperty("bangoTransactionIdProperty", exchange.getIn().getHeader("bangoTransactionId"));
			exchange.setProperty("merchantTransactionIdIdProperty", exchange.getIn().getHeader("merchantTransactionId"));
			exchange.setProperty("userIdProperty", exchange.getIn().getHeader("userId"));
			exchange.setProperty("amountProperty", exchange.getIn().getHeader("amount"));
			exchange.setProperty("currencyProperty", exchange.getIn().getHeader("currency"));
			exchange.setProperty("merchantAccountKeyProperty", exchange.getIn().getHeader("merchantAccountKey"));
			exchange.setProperty("productKeyProperty", exchange.getIn().getHeader("productKey"));
			exchange.setProperty("supportContactProperty", exchange.getIn().getHeader("supportContact"));
			

			exchange.getIn().setBody(cliente);

		}
		if (headerBD.equals("paymentTransactionId")) {

			con = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			String secuencia = clienteDaoImpl.paymentTransactionId(sql, con);
			exchange.setProperty("paymentTransactionIdProperty", secuencia);
			exchange.getIn().setBody(secuencia);

		}
	
	}


}
