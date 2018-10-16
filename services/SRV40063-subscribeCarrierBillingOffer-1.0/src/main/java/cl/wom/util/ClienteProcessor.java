package cl.wom.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import cl.wom.beans.Cliente;
import cl.wom.database.ClienteDaoImpl;
import cl.wom.database.ConnectionFactory;
import cl.wom.database.ConnectionFactory.DataBaseSchema;
import cl.wom.exception.services.ServiceError;

public class ClienteProcessor implements Processor {
	
	private ClienteDaoImpl clienteDaoImpl ;
	private Connection con;
	private  Cliente cliente;

	public void process(Exchange exchange) throws Exception {

		String sql = (String) exchange.getIn().getBody();
		 clienteDaoImpl = new ClienteDaoImpl();	
		con = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
		cliente=clienteDaoImpl.getCliente(sql,con);

		if(cliente.getMsisdn() == null) {
	
			 throw new ServiceError("416");
			
		}
		
		exchange.getIn().setBody(cliente);

	}

}
