package cl.wom.middleware.bean;

import java.sql.SQLException;
import java.util.List;

import org.apache.camel.Exchange;
import org.json.JSONArray;
import org.json.XML;

import cl.wom.middleware.dao.ProductCatalogDAOImplSOAP;
import cl.wom.middleware.vo.ProductOffering;

public class SqlFormatterProcessorSOAP {
	
	public String sqlParserProductOffering(Exchange ex) throws SQLException {
		
		Long offerId   = (Long)ex.getIn().getHeader("offerId");
		String shortDes  = (String)ex.getIn().getHeader("shortDesc");
 		System.out.println("exito recibiendo datos");

 		ProductCatalogDAOImplSOAP productCatalogDAOImplSOAP = new ProductCatalogDAOImplSOAP();
 		List<ProductOffering>  listProductOffering =  productCatalogDAOImplSOAP.getProductCatalog(offerId, shortDes);

 		
 		JSONArray jsonObj = new org.json.JSONArray(listProductOffering);
 		String xml = XML.toString(jsonObj);
 		System.out.println("exito");
 		if (listProductOffering.size() > 0)
 			return xml;
 		else
 			return "";
	}
	
	
	public List<ProductOffering> sqlParserProductOfferingObject(Exchange ex) throws SQLException {
		
		Long offerId   = (Long)ex.getIn().getHeader("offerId");
		String shortDes  = (String)ex.getIn().getHeader("shortDesc");
 		System.out.println("exito recibiendo datos");

 		ProductCatalogDAOImplSOAP productCatalogDAOImplSOAP = new ProductCatalogDAOImplSOAP();
 		return  productCatalogDAOImplSOAP.getProductCatalog(offerId, shortDes);

	}
	
	
}