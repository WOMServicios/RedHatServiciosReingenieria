package cl.wom.middleware.dao;

import cl.wom.middleware.vo.Subscriber;

import java.sql.SQLException;


public class SubscriberDAOImpl {
	
	public Subscriber getSubscriber(String subscriberId) throws SQLException {
		Subscriber subscriber =new Subscriber();
		subscriber.setSubscriberId(subscriberId);
		String query1="";
		String query2="";
		String query3="";
		String query4="";
		String query5="";
		subscriber.setProductOffer(ProductOfferDAOImpl.getProductOffer(query1));
		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query2));
		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query3));
		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query4));
		subscriber.setProductBundle(ProductBundleDAOImpl.getProductBundle(query5));
		
		
		return subscriber;
	}

}
