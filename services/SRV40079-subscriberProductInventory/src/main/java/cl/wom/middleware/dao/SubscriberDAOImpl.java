package cl.wom.middleware.dao;

import cl.wom.middleware.util.PropertiesUtil;
import cl.wom.middleware.vo.Subscriber;

import java.sql.SQLException;
import java.text.MessageFormat;


public class SubscriberDAOImpl {
	
	public Subscriber getSubscriber(String subscriberId) throws SQLException {
		PropertiesUtil sqlProperties = new PropertiesUtil();
		Subscriber subscriber =new Subscriber();
		subscriber.setSubscriberId(subscriberId);
		
//		TODO: AGREGAR EL PARAMETRO SUBSCRIBERID DEL HEADER A LA QUERY
//		sqlProperties.getLocalProperties()
		
		
		String query1 = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql_productOffer"),"jose vejares"); 
		
		System.out.println("query1: "+query1);
		
		
//		String query2=querys.getProperties("sql_productBundle1").toString();
//		String query3=querys.getProperties("sql_productBundle2").toString();
//		String query4=querys.getProperties("sql_productBundle3").toString();
//		String query5=querys.getProperties("sql_productBundle4").toString();
		
		
		
		
//		SE EJECUTA LA QUERY PARA LLENAR LA LISTA DE OFFER PRODUCT
//		subscriber.setProductOffer(ProductOfferDAOImpl.getProductOffer(query1));
		
		
		
//		SE EJECUTA LA QUERY PARA LLENAR LA LISTA DE BUNDLE PRODUCT
//		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query2));
//		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query3));
//		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query4));
//		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query5));
		
		
		return subscriber;
	}

}
