#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package cl.wom.middleware.proxy;

import org.apache.camel.Exchange;

public class Sleep {
	
	
	public void ajustTimeOut(Exchange exchange){


			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}

}
