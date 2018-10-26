package cl.wom.middleware.bean;

import java.util.List;

import org.apache.camel.Exchange;
import org.json.JSONArray;

import cl.wom.middleware.dao.ProductCatalogDAOImpl;
import cl.wom.middleware.proxy.ServiceError;
import cl.wom.middleware.vo.ProductOffering;

public class SqlFormatterProcessor {
	
	public String sqlParserProductOffering(Exchange ex) throws ServiceError  {
		
		String offerId   = (String)ex.getIn().getHeader("productOffering.offerId");
		String shortDes  = (String)ex.getIn().getHeader("productOffering.shortDesc");
		
 		ProductCatalogDAOImpl productCatalogDAOImpl = new ProductCatalogDAOImpl();
 		List<ProductOffering>  listProductOffering =  productCatalogDAOImpl.getProductCatalog(offerId, shortDes);

 		JSONArray jsonObj = new org.json.JSONArray(listProductOffering);
 		
 		if (listProductOffering.size() > 0)
 			return jsonObj.toString();
 		else
 			return "";
	}
	
	
	public List<ProductOffering> sqlParserProductOfferingSOAP(Exchange ex) throws ServiceError  {
		
		String offerId   = (String)ex.getIn().getHeader("offerId");
		String shortDes  = (String)ex.getIn().getHeader("shortDesc");
		
 		ProductCatalogDAOImpl productCatalogDAOImpl = new ProductCatalogDAOImpl();
 		return productCatalogDAOImpl.getProductCatalog(offerId, shortDes);

// 		JSONArray jsonObj = new org.json.JSONArray(listProductOffering);
 		
// 		if (listProductOffering.size() > 0)
// 			return jsonObj.toString();
// 		else
// 			return "";
	}	
}
