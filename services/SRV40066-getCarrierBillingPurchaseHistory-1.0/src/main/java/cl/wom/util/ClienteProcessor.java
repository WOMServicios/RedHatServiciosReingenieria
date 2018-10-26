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
	
	private ClienteDaoImpl clienteDaoImpl ;
	private Connection con;
	private  Cliente cliente;
	public void process(Exchange exchange) throws Exception {
		
		
		if(exchange.getIn().getHeader("msisdn").toString().length()>=30) {
			throw new ServiceError("452");
		}

	
		String sql = (String) exchange.getIn().getBody();
	
		 clienteDaoImpl = new ClienteDaoImpl();	
		con = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
		cliente=clienteDaoImpl.getCliente(sql,con);

		if(cliente.getNumCelular() == null) {
		
	
			 throw new ServiceError("453");
			
		}
		
		exchange.getIn().setBody(cliente);

}

}















	
