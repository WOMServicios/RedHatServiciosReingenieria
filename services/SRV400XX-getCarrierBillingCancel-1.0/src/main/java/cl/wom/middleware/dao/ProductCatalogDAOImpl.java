package cl.wom.middleware.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.wom.middleware.vo.ProductOffering;

public class ProductCatalogDAOImpl {
	
	public List<ProductOffering> getProductCatalog(String OfferID, String shortDesc) throws SQLException  {
 		System.out.println("entrando a la clase");

 		List<ProductOffering> listProductOffering = new ArrayList<ProductOffering>();
		return listProductOffering;
	}
}
