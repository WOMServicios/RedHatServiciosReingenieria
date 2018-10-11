package cl.wom.middleware.bean;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;

import cl.wom.middleware.vo.ProductOffering;

public class SqlFormatterProcessor {

	
	
	
	public ProductOffering sqlParserProductOffering(Exchange ex) {
		
		ProductOffering productOffering = new ProductOffering();
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> listOffer = (List<Map<String, Object>>) ex.getIn().getBody();
		
		
		for(Map<String, Object> map: listOffer) {
			productOffering.setOfferId((String) map.get("offerId"));
			
		}
		
		
		
		
		
		return productOffering;
		
	}
	
}
