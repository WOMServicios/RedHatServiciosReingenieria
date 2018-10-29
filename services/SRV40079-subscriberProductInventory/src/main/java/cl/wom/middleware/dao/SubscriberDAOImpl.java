package cl.wom.middleware.dao;

import cl.wom.middleware.util.PropertiesUtilSQL;
import cl.wom.middleware.vo.Subscriber;

import java.sql.SQLException;


public class SubscriberDAOImpl {
	
	public Subscriber getSubscriber(String subscriberId) throws SQLException {
		PropertiesUtilSQL querys = new PropertiesUtilSQL();
		Subscriber subscriber =new Subscriber();
		subscriber.setSubscriberId(subscriberId);
//		TODO: AGREGAR EL PARAMETRO SUBSCRIBERID DEL HEADER A LA QUERY
		String query1=querys.getProperties("sql_productOffer").toString();
		String query2=querys.getProperties("sql_productBundle1").toString();
		String query3=querys.getProperties("sql_productBundle2").toString();
		String query4=querys.getProperties("sql_productBundle3").toString();
		String query5=querys.getProperties("sql_productBundle4").toString();
//		SE EJECUTA LA QUERY PARA LLENAR LA LISTA DE OFFER PRODUCT
		subscriber.setProductOffer(ProductOfferDAOImpl.getProductOffer(query1));
//		SE EJECUTA LA QUERY PARA LLENAR LA LISTA DE BUNDLE PRODUCT
		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query2));
		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query3));
		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query4));
		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query5));
		
		
		return subscriber;
	}

}
