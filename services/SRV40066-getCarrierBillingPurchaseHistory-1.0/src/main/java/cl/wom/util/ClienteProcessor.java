package cl.wom.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import cl.wom.beans.Cliente;
import cl.wom.database.ClienteDaoImpl;
import cl.wom.database.Conexion;
import cl.wom.exception.services.ServiceError;

public class ClienteProcessor implements Processor {
	private ClienteDaoImpl iClienteDao=new ClienteDaoImpl();

	public void process(Exchange exchange) throws Exception {

	
		String row = (String) exchange.getIn().getBody();

		System.err.println(row);
		Cliente cliente;
		cliente=iClienteDao.getCliente(row);

		if(cliente.getNumCelular()== null) {
	
			 throw new ServiceError("416");
			
		}
		
		exchange.getIn().setBody(cliente);

}

}















	
