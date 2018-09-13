package cl.wom.middleware.proxy;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class ValidaResponseProxy {

	
    
    public void validaCodigoResponse(Exchange exchange) throws Exception{
        Message message = exchange.getIn();
        String mensaje =  message.getHeader("CamelCxfMessage", String.class);
        if(mensaje.contains("org.apache.cxf.message.Message.RESPONSE_CODE=500")){
        	//lanzar error
        	exchange.getIn().setHeader("estado", "500");
			throw new Exception(); 
        }
        else{
        	exchange.getIn().setHeader("estado", "200");        
        }        
    }
    
	
	
	
	
}
