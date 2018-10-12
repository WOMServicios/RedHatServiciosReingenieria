package cl.wom.middleware.bean;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.json.JSONObject;

import cl.wom.middleware.vo.ProductOffering;
import cl.wom.middleware.vo.RecurringCharge;

public class SqlFormatterProcessor {

	
	
	
	public String sqlParserProductOffering(Exchange ex) {
		List<ProductOffering> listproductOffering = null;
 		ProductOffering productOffering = new ProductOffering();
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> listOffer = (List<Map<String, Object>>) ex.getIn().getBody();
		System.out.println("listOffer.toString(): "+listOffer.toString());
		
		for(Map<String, Object> map: listOffer) {
			
//			productOffering.setBundleProductOffering(bundleProductOffering);
			
			productOffering.setOfferId(String.valueOf(map.get("OFFERID")));
			System.out.println("mostrando:" + productOffering.getOfferId());
			productOffering.setName(String.valueOf (map.get("NAME")));
			productOffering.setShortDescription(String.valueOf (map.get("SHORTDESCRIPTION")));
			productOffering.setLastUpdate(String.valueOf ( map.get("LASTUPDATE")));
			productOffering.setStatus(String.valueOf (map.get("STATUS")));
			productOffering.setVersion(String.valueOf (map.get("VERSION")));
			productOffering.setIsSellable(String.valueOf ( map.get("ISSELLABLE")));
			productOffering.setMarketSegment(String.valueOf (map.get("MARKETSEGMENT")));
			productOffering.setFamilyOffer(String.valueOf( map.get("FAMILYOFFER")));
			productOffering.setTypeOffer(String.valueOf( map.get("TYPEOFFER")));
			productOffering.setLayoutTypeAPP(String.valueOf(map.get("LAYOUTTYPEAPP")));
			
			RecurringCharge recurringCharge = new RecurringCharge();
			recurringCharge.setAmount(String.valueOf (map.get("AMOUNT_RECURRINGCHARGE")));
			recurringCharge.setCurrency(String.valueOf( map.get("CURRENCY_RECURRINGCHARGE")));
			recurringCharge.setFrecuency(String.valueOf(map.get("FRECUENCY_RECURRINGCHARGE")));
			recurringCharge.setType(String.valueOf (map.get("TYPE_RECURRINGCHARGE")));
			recurringCharge.setUnitOfMeasure(String.valueOf(map.get("UNITOFMEASURE_RECURRINGCHARGE")));

			productOffering.setRecurringCharge(recurringCharge);
			

//			productOffering.set((String) map.get("duration_recurringCharge"));

//			productOffering.setName((String) map.get("name"));
//			productOffering.setName((String) map.get("name"));
//			productOffering.setName((String) map.get("name"));
//			productOffering.setName((String) map.get("name"));
//			productOffering.setName((String) map.get("name"));
//			productOffering.setName((String) map.get("name"));
			
//			listproductOffering.add(productOffering);
			
		}
		
		JSONObject jsonObj = new JSONObject( productOffering );
		
		
		return jsonObj.toString();
		
	}
	
}
