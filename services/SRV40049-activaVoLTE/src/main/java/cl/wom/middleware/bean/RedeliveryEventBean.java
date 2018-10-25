package cl.wom.middleware.bean;

import org.apache.camel.Exchange;

public class RedeliveryEventBean {
	
	public void nextValue(Exchange ex)
    {
		int value = (Integer) ex.getIn().getHeader("value");
		ex.getIn().setHeader("value", ++value);
    }
}
