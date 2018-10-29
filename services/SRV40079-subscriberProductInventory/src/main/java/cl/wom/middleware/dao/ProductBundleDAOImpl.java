package cl.wom.middleware.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.vo.ProductBundle;

public class ProductBundleDAOImpl {
	public static List<ProductBundle> getProductBundle(String query) throws SQLException{
		Connection conn = null;
		Statement stmt;
		List<ProductBundle> listProductBundle=new ArrayList <ProductBundle>();
		try{
			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			stmt = conn.createStatement();
			ResultSet rsGetproductBundle = conn.createStatement().executeQuery(query);
			
			while(rsGetproductBundle.next()) {
				ProductBundle productBundle=new ProductBundle();
				productBundle.setSubscriberId(rsGetproductBundle.getString("subscriberId"));
				productBundle.setBundleId(rsGetproductBundle.getString("bundleId"));
				productBundle.setBundleName(rsGetproductBundle.getString("bundleName"));
				productBundle.setBundleDesc(rsGetproductBundle.getString("bundleDesc"));
				productBundle.setIsOptionProduct(rsGetproductBundle.getString("isOptionProduct"));
				productBundle.setIsOfferProduct(rsGetproductBundle.getString("isOfferProduct"));
				productBundle.setBundlePromotion(rsGetproductBundle.getString("bundlePromotion"));
				productBundle.setBundleTariff(rsGetproductBundle.getString("bundleTariff"));
				productBundle.setBundleQuantity(rsGetproductBundle.getString("bundleQuantity"));
				productBundle.setBundleType(rsGetproductBundle.getString("bundleType"));
				productBundle.setBundleUnit(rsGetproductBundle.getString("bundleIUnit"));
				productBundle.setBundleTraffic(rsGetproductBundle.getString("bundleTraffic"));
				productBundle.setBundleRecurrence(rsGetproductBundle.getString("bundleRecurrence"));
				productBundle.setBundleChannelActive(rsGetproductBundle.getString("bundleChannelActive"));
				productBundle.setBundleSaleDate(rsGetproductBundle.getString("bundleSaleDate"));
				productBundle.setBundleExpirationDate(rsGetproductBundle.getString("bundleExpirationDate"));
				productBundle.setBundleTransactionCode(rsGetproductBundle.getString("bundleTransactionCode"));
				productBundle.setBundlePaymentMethod(rsGetproductBundle.getString("bundlePaymentMethod"));

				listProductBundle.add(productBundle);
			}
			rsGetproductBundle.close();
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return listProductBundle;
	}

}
