package cl.wom.middleware.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.vo.ProductOffer;

public class ProductOfferDAOImpl {
	public static List<ProductOffer> getProductOffer(String sql) throws SQLException{
		
		Connection conn = null;
		Statement stmt;
		List<ProductOffer> listProductOffer= new ArrayList <ProductOffer>();
		
		
		try {
			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			stmt = conn.createStatement();
			ResultSet rsGetproductOffer = conn.createStatement().executeQuery(sql);

//			a.CO_ID              as subscriberId,
//			a.tmcode             as offerId,
//			b.des                as name,
//			b.shdes              as shortDescription,
//			b.ats_prepaid_ind    as atsPrepaidInd,
//			a.seqno              as seqNo,	
//			a.tmcode_date        as starOffer,
//			a.request_id         as requestId,
//			a.transactionNo      as transactionNo,
//			a.userlastmod        as userLastMod
			
			while(rsGetproductOffer.next()) {
				ProductOffer productOffer= new ProductOffer();
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
				
				listProductOffer.add(productOffer);
			}
			rsGetproductOffer.close();
			stmt.close();

		}
		catch (SQLException e) {
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
		return listProductOffer;
	}

}