package cl.wom.middleware.proxy;

import org.apache.camel.Exchange;
import org.apache.commons.lang.RandomStringUtils;

public class TestRamdomGenerator {

	public String random(Exchange ex) {
		String body = ex.getIn().getBody(String.class);
		String generatedString = RandomStringUtils.randomAlphabetic(20);
		
		System.out.println("generatedString: "+generatedString);
		return body.replaceAll("string", generatedString);
	}
}
