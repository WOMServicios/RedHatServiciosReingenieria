package cl.wom.middleware.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.vo.BundleProductOffering;
import cl.wom.middleware.vo.ProductOffering;

public class TestBaseDatos {

	public static void main(String[] args) {
		new TestBaseDatos().getProductCatalog("739");
	}
	
	
	
	
	public void getProductCatalog(String OfferID)  {
		
		System.setProperty("database.bscs.host", "10.120.241.44");
		System.setProperty("database.bscs.port", "1540");
		System.setProperty("database.bscs.databasename", "BSCSUAT");
		System.setProperty("database.bscs.username", "VMD");
		System.setProperty("database.bscs.password", "VMD");
		
		Connection conn = null;
		Statement stmt;
		try {
			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			stmt = conn.createStatement();
			

			//GetProductOfferID
			String queryGetProductOffering = "select a.tmcode as offerId, a.des as name, a.SHDES as shortDescription, b.apdate as lastUpdate, b.status as status, b.rec_version as version, 'true' as isSellable, c.SEGMENT_PLAN as marketSegment, c.FAMILY_PLAN as familyOffer, c.TYPE_PLAN as typeOffer, c.LAYOUT_TYPE_APP as layoutTypeApp, c.ACCESS_FEE as amount_recurringCharge, 'CLP' as currency_recurringCharge, '43200' as duration_recurringCharge, 'minutos' as unitOfMeasure_recurringCharge,  'Offer' as type_recurringCharge from sysadm.rateplan a, sysadm.rateplan_version b, PROVI_BOLS.NEXTEL_CATALOGO_PLAN c where a.tmcode=739 and ROWNUM <= 10 --parametroand b.rec_version = (select max(x.rec_version) from sysadm.rateplan_version x "
					+ "where x.tmcode="+OfferID+" and x.tmcode  = b.tmcode and x.STATUS='P')and b.STATUS='P'and a.SHDES = c.SHDES_PLAN";
			ResultSet rsGetProductOffering = conn.createStatement().executeQuery(queryGetProductOffering);
			
			List<ProductOffering> listProductOffering = new ArrayList<ProductOffering>();
			while (rsGetProductOffering.next()) {
				ProductOffering productOffering = new ProductOffering();
				
				productOffering.setLastUpdate(rsGetProductOffering.getString("lastUpdate"));
				
				
				//Aqui va el bundle 2.1.1
				//TODO: quitar el ROWNUM
				List<BundleProductOffering> listBundleProductOffering = new ArrayList<BundleProductOffering>();
				String queryGetBundleOfferingA = "select b.ID_PROD as id, a.tmcode as offerId, a.SHDES as shDes, b.NAME_PROD as name, b.DESC_PROD as description, b.LEVEL_PCRF_PROD as priority, b.status  as status, '1' as minimumRequired, '1' as maximumAllowed, 'false' as isOfferProduct, 'true' as isOptionProduct, decode(b.promo,'S','True','N','False') as isPromotionProduct, b.file1 as occ, b.sku as sku, b.CHANNEL_ACT as name_channel, b.CHANNEL_ACT as legacySystem_channel, decode(b.recurrence,'N',b.tariff_prod) as amount_oneTime, decode(b.recurrence,'N','CLP') as currency_oneTime, decode(b.recurrence,'N',b.VIGENCIA) as duration_oneTime, decode(b.recurrence,'N','Minutos') as unitOfMesaure_oneTime, decode(b.recurrence,'N','Bundle') as type_oneTime, decode(b.recurrence,'S',b.tariff_prod) as amount_recurringCharge, decode(b.recurrence,'S','CLP') as currency_recurringCharge, decode(b.recurrence,'S',b.VIGENCIA) as duration_recurringCharge, decode(b.recurrence,'S','Minutos') as unitOfMesaure_recurringCharge, decode(b.recurrence,'S','Bundle') as type_recurringCharge from sysadm.rateplan a, PROVI_BOLS.NEXTEL_CATALOGO_PRODUCTOS b "
						+ "where a.tmcode="+OfferID+" and ROWNUM <= 10 ---variable   and REGEXP_LIKE(to_char(a.tmcode), '^(|'||REPLACE(REPLACE(UPPER(FAMILY_PLAN),'',UPPER(to_char(a.tmcode))),';','|')||')$')";
				ResultSet rsGetBundleOfferingA = conn.createStatement().executeQuery(queryGetBundleOfferingA);
				while (rsGetBundleOfferingA.next()) {
					BundleProductOffering bundleProductOffering = new BundleProductOffering();
					bundleProductOffering.setName(rsGetBundleOfferingA.getString("name"));
					
					listBundleProductOffering.add(bundleProductOffering);
				}
				productOffering.setBundleProductOffering(listBundleProductOffering);
				
				
				
				
				
				listProductOffering.add(productOffering);
			}
			
			
//			JSONObject jsonObj = new JSONObject( listProductOffering );
			
//			JSONArray jsonObj = new org.json.JSONArray(listProductOffering);
			JSONArray jsonArray = new JSONArray(listProductOffering);
			
//			return jsonObj.toString();
			
			System.out.println("dentro del metodo: "+jsonArray.toString() );
			rsGetProductOffering.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
			
	
		
	}

}
