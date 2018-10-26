package cl.wom.middleware.bean;

import java.sql.SQLException;
import java.util.List;

import org.apache.camel.Exchange;
import org.json.JSONArray;

import cl.wom.middleware.dao.ProductCatalogDAOImpl;
import cl.wom.middleware.vo.ProductOffering;

public class SqlFormatterProcessor {
	
	public String sqlParserProductOffering(Exchange ex) throws SQLException {
		
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
	
}
