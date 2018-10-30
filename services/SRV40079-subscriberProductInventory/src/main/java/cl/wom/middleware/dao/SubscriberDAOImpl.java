package cl.wom.middleware.dao;

import cl.wom.middleware.proxy.ServiceError;
import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.PropertiesUtil;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.vo.ProductBundle;
import cl.wom.middleware.vo.ProductOffer;
import cl.wom.middleware.vo.Subscriber;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


public class SubscriberDAOImpl {
	
	public Subscriber getSubscriber(String subscriberId) throws SQLException, ServiceError, ClassNotFoundException {
		PropertiesUtil sqlProperties = new PropertiesUtil();
		Subscriber subscriber = new Subscriber();
		Connection conn = null;
		Statement stmt;
		List<ProductOffer> listProductOffer= new ArrayList <ProductOffer>();
		List<ProductBundle> listProductBundle=new ArrayList <ProductBundle>();
		
		String query1 = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql_productOffer"),subscriberId);
		String query2 = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql_productBundle1"), subscriberId);
		String query3 = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql_productBundle2"),subscriberId);
		String query4 = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql_productBundle3"),subscriberId);
		String query5 = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql_productBundle4"),subscriberId);
		
		try {
			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			stmt = conn.createStatement();		
//			AGREGA LA LISTA OFFER PRODUCT AL OBJETO
			ResultSet rsGetproductOffer= conn.createStatement().executeQuery(query1);
			while(rsGetproductOffer.next()){
				ProductOffer productOffer=new ProductOffer();
				
				subscriber.setSubscriberId(rsGetproductOffer.getString("subscriberId"));
				productOffer.setSubscriberId(rsGetproductOffer.getString("subscriberId"));
				productOffer.setOfferId(rsGetproductOffer.getString("offerId"));
				productOffer.setName(rsGetproductOffer.getString("name"));
				productOffer.setShortDescription(rsGetproductOffer.getString("shortDescription"));
				productOffer.setAtsPrepaidInd(rsGetproductOffer.getString("atsPrepaidInd"));
				productOffer.setSeqNo(rsGetproductOffer.getString("seqNo"));
				productOffer.setStartOffer(rsGetproductOffer.getString("starOffer"));
				productOffer.setRequestId(rsGetproductOffer.getString("requestId"));
				productOffer.setTransactionNo(rsGetproductOffer.getString("transactionNo"));
				productOffer.setUserLastMod(rsGetproductOffer.getString("userLastMod"));
				
				System.out.println("MUESTRA: "+ productOffer.getName());
				listProductOffer.add(productOffer);			
			}
			subscriber.setProductOffer(listProductOffer);
			rsGetproductOffer.close();

			ResultSet rsGetproductBundle1 = conn.createStatement().executeQuery(query2);
			while(rsGetproductBundle1.next()) {
				ProductBundle productBundle=new ProductBundle();
				productBundle.setSubscriberId(rsGetproductBundle1.getString("subscriberId"));
				productBundle.setBundleId(rsGetproductBundle1.getString("bundleId"));
				productBundle.setBundleName(rsGetproductBundle1.getString("bundleName"));
				productBundle.setBundleDesc(rsGetproductBundle1.getString("bundleDesc"));
				productBundle.setIsOptionProduct(rsGetproductBundle1.getString("isOptionProduct"));
				productBundle.setIsOfferProduct(rsGetproductBundle1.getString("isOfferProduct"));
				productBundle.setBundlePromotion(rsGetproductBundle1.getString("bundlePromotion"));
				productBundle.setBundleTariff(rsGetproductBundle1.getString("bundleTariff"));
				productBundle.setBundleQuantity(rsGetproductBundle1.getString("bundleQuantity"));
				productBundle.setBundleType(rsGetproductBundle1.getString("bundleType"));
				productBundle.setBundleUnit(rsGetproductBundle1.getString("bundleUnit"));
				productBundle.setBundleTraffic(rsGetproductBundle1.getString("bundleTraffic"));
				productBundle.setBundleRecurrence(rsGetproductBundle1.getString("bundleRecurrence"));
				productBundle.setBundleChannelActive(rsGetproductBundle1.getString("bundleChannelActive"));
				productBundle.setBundleSaleDate(rsGetproductBundle1.getString("bundleSaleDate"));
				productBundle.setBundleExpirationDate(rsGetproductBundle1.getString("bundleExpirationDate"));
				productBundle.setBundleTransactionCode(rsGetproductBundle1.getString("bundleTransactionCode"));
				productBundle.setBundlePaymentMethod(rsGetproductBundle1.getString("bundlePaymentMethod"));

				System.out.println("BUNDLE1: "+productBundle.getBundleName());
				listProductBundle.add(productBundle);
			}
			rsGetproductBundle1.close();
			ResultSet rsGetproductBundle2 = conn.createStatement().executeQuery(query3);
			while(rsGetproductBundle2.next()) {
				ProductBundle productBundle=new ProductBundle();
				productBundle.setSubscriberId(rsGetproductBundle2.getString("subscriberId"));
				productBundle.setBundleId(rsGetproductBundle2.getString("bundleId"));
				productBundle.setBundleName(rsGetproductBundle2.getString("bundleName"));
				productBundle.setBundleDesc(rsGetproductBundle2.getString("bundleDesc"));
				productBundle.setIsOptionProduct(rsGetproductBundle2.getString("isOptionProduct"));
				productBundle.setIsOfferProduct(rsGetproductBundle2.getString("isOfferProduct"));
				productBundle.setBundlePromotion(rsGetproductBundle2.getString("bundlePromotion"));
				productBundle.setBundleTariff(rsGetproductBundle2.getString("bundleTariff"));
				productBundle.setBundleQuantity(rsGetproductBundle2.getString("bundleQuantity"));
				productBundle.setBundleType(rsGetproductBundle2.getString("bundleType"));
				productBundle.setBundleUnit(rsGetproductBundle2.getString("bundleUnit"));
				productBundle.setBundleTraffic(rsGetproductBundle2.getString("bundleTraffic"));
				productBundle.setBundleRecurrence(rsGetproductBundle2.getString("bundleRecurrence"));
				productBundle.setBundleChannelActive(rsGetproductBundle2.getString("bundleChannelActive"));
				productBundle.setBundleSaleDate(rsGetproductBundle2.getString("bundleSaleDate"));
				productBundle.setBundleExpirationDate(rsGetproductBundle2.getString("bundleExpirationDate"));
				productBundle.setBundleTransactionCode(rsGetproductBundle2.getString("bundleTransactionCode"));
				productBundle.setBundlePaymentMethod(rsGetproductBundle2.getString("bundlePaymentMethod"));

				System.out.println("BUNDLE2: "+productBundle.getBundleName());
				listProductBundle.add(productBundle);
			}
			rsGetproductBundle2.close();
			ResultSet rsGetproductBundle3 = conn.createStatement().executeQuery(query4);
			while(rsGetproductBundle3.next()) {
				ProductBundle productBundle=new ProductBundle();
				productBundle.setSubscriberId(rsGetproductBundle3.getString("subscriberId"));
				productBundle.setBundleId(rsGetproductBundle3.getString("bundleId"));
				productBundle.setBundleName(rsGetproductBundle3.getString("bundleName"));
				productBundle.setBundleDesc(rsGetproductBundle3.getString("bundleDesc"));
				productBundle.setIsOptionProduct(rsGetproductBundle3.getString("isOptionProduct"));
				productBundle.setIsOfferProduct(rsGetproductBundle3.getString("isOfferProduct"));
				productBundle.setBundlePromotion(rsGetproductBundle3.getString("bundlePromotion"));
				productBundle.setBundleTariff(rsGetproductBundle3.getString("bundleTariff"));
				productBundle.setBundleQuantity(rsGetproductBundle3.getString("bundleQuantity"));
				productBundle.setBundleType(rsGetproductBundle3.getString("bundleType"));
				productBundle.setBundleUnit(rsGetproductBundle3.getString("bundleUnit"));
				productBundle.setBundleTraffic(rsGetproductBundle3.getString("bundleTraffic"));
				productBundle.setBundleRecurrence(rsGetproductBundle3.getString("bundleRecurrence"));
				productBundle.setBundleChannelActive(rsGetproductBundle3.getString("bundleChannelActive"));
				productBundle.setBundleSaleDate(rsGetproductBundle3.getString("bundleSaleDate"));
				productBundle.setBundleExpirationDate(rsGetproductBundle3.getString("bundleExpirationDate"));
				productBundle.setBundleTransactionCode(rsGetproductBundle3.getString("bundleTransactionCode"));
				productBundle.setBundlePaymentMethod(rsGetproductBundle3.getString("bundlePaymentMethod"));

				System.out.println("BUNDLE3: "+productBundle.getBundleName());
				listProductBundle.add(productBundle);
			}
			rsGetproductBundle3.close();
			ResultSet rsGetproductBundle4 = conn.createStatement().executeQuery(query5);
			while(rsGetproductBundle4.next()) {
				ProductBundle productBundle=new ProductBundle();
				productBundle.setSubscriberId(rsGetproductBundle4.getString("subscriberId"));
				productBundle.setBundleId(rsGetproductBundle4.getString("bundleId"));
				productBundle.setBundleName(rsGetproductBundle4.getString("bundleName"));
				productBundle.setBundleDesc(rsGetproductBundle4.getString("bundleDesc"));
				productBundle.setIsOptionProduct(rsGetproductBundle4.getString("isOptionProduct"));
				productBundle.setIsOfferProduct(rsGetproductBundle4.getString("isOfferProduct"));
				productBundle.setBundlePromotion(rsGetproductBundle4.getString("bundlePromotion"));
				productBundle.setBundleTariff(rsGetproductBundle4.getString("bundleTariff"));
				productBundle.setBundleQuantity(rsGetproductBundle4.getString("bundleQuantity"));
				productBundle.setBundleType(rsGetproductBundle4.getString("bundleType"));
				productBundle.setBundleUnit(rsGetproductBundle4.getString("bundleUnit"));
				productBundle.setBundleTraffic(rsGetproductBundle4.getString("bundleTraffic"));
				productBundle.setBundleRecurrence(rsGetproductBundle4.getString("bundleRecurrence"));
				productBundle.setBundleChannelActive(rsGetproductBundle4.getString("bundleChannelActive"));
				productBundle.setBundleSaleDate(rsGetproductBundle4.getString("bundleSaleDate"));
				productBundle.setBundleExpirationDate(rsGetproductBundle4.getString("bundleExpirationDate"));
				productBundle.setBundleTransactionCode(rsGetproductBundle4.getString("bundleTransactionCode"));
				productBundle.setBundlePaymentMethod(rsGetproductBundle4.getString("bundlePaymentMethod"));

				System.out.println("BUNDLE4: "+productBundle.getBundleName());
				listProductBundle.add(productBundle);
			}
			rsGetproductBundle4.close();
			
			subscriber.setProductBundle(listProductBundle);
	
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceError("455");
		}finally {
			if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServiceError("455");
				}
		}
		return subscriber;	
	}

}
