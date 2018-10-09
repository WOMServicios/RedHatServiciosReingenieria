package cl.wom.middleware.proxy;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class Prueba  implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, Object> row = exchange.getIn().getBody(Map.class);
		
		String amountS= (String) exchange.getIn().getHeader("amount");
		System.out.println("pruebaaa "+amountS );
		
		
		for (Entry<String, Object> entry : row.entrySet()) {
			System.out.println(entry.getKey() + "::" + entry.getValue());
			
//			System.out.println(entry.getValue().toString().indexOf("<occId>"));
//			System.out.println(entry.getValue().toString().indexOf("</occId>"));
			
			int start=entry.getValue().toString().indexOf("<occId>")+"<occId>".length();
			int end =entry.getValue().toString().indexOf("</occId>");
		
			

			System.out.println(entry.getValue().toString().substring(start, end));
			String valor= entry.getValue().toString().substring(start, end);
		}
		
	}

}
