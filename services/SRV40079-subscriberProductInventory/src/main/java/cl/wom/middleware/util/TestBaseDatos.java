//package cl.wom.middleware.util;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.json.JSONArray;
//
//import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
//import cl.wom.middleware.vo.BundleProductOffering;
//import cl.wom.middleware.vo.Channel;
//import cl.wom.middleware.vo.OneTime;
//import cl.wom.middleware.vo.ProductOffering;
//import cl.wom.middleware.vo.RecurringCharge;
//
//public class TestBaseDatos {
//
//	public static void main(String[] args) {
//		JSONArray jsonArray=null;
//		new TestBaseDatos().getProductCatalog("739");
//	}
//	
//	public String getProductCatalog(String OfferID)  {
//		
//		System.setProperty("database.bscs.host", "10.120.241.44");
//		System.setProperty("database.bscs.port", "1540");
//		System.setProperty("database.bscs.databasename", "BSCSUAT");
//		System.setProperty("database.bscs.username", "VMD");
//		System.setProperty("database.bscs.password", "VMD");
//		
//		Connection conn = null;
//		Statement stmt;
//		try {
//			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
//			stmt = conn.createStatement();
//			
//
//			//GetProductOfferID
//			String queryGetProductOffering="select a.tmcode as offerId, a.des as name,a.SHDES as shortDescription,b.apdate as lastUpdate,b.status as status,b.rec_version as version,'true' as isSellable,c.SEGMENT_PLAN as marketSegment,c.FAMILY_PLAN as familyOffer,c.TYPE_PLAN as typeOffer,c.LAYOUT_TYPE_APP as layoutTypeApp,c.ACCESS_FEE as amount_recurringCharge,'CLP' as currency_recurringCharge,'43200' as duration_recurringCharge,'minutos' as unitOfMeasure_recurringCharge,'Offer' as type_recurringCharge from sysadm.rateplan a,sysadm.rateplan_version b, PROVI_BOLS.NEXTEL_CATALOGO_PLAN c where a.tmcode=739 and b.rec_version = (select max(x.rec_version) from sysadm.rateplan_version x  where x.tmcode=739 and x.tmcode  = b.tmcode and x.STATUS='P') and b.STATUS='P' and a.SHDES = c.SHDES_PLAN";
////			String queryGetProductOffering = "select a.tmcode as offerId, a.des as name, a.SHDES as shortDescription,"
////					+ " b.apdate as lastUpdate, b.status as status, b.rec_version as version, 'true' as isSellable, "
////					+ "c.SEGMENT_PLAN as marketSegment, c.FAMILY_PLAN as familyOffer, c.TYPE_PLAN as typeOffer,"
////					+ " c.LAYOUT_TYPE_APP as layoutTypeApp, c.ACCESS_FEE as amount_recurringCharge,"
////					+ " 'CLP' as currency_recurringCharge, '43200' as duration_recurringCharge,"
////					+ " 'minutos' as unitOfMeasure_recurringCharge,  'Offer' as type_recurringCharge "
////					+ "from sysadm.rateplan a, sysadm.rateplan_version b, PROVI_BOLS.NEXTEL_CATALOGO_PLAN c "
////					+ "where a.tmcode=739 and ROWNUM <= 10 --parametroand b.rec_version = (select max(x.rec_version) "
////					+ "from sysadm.rateplan_version x "
////					+ "where x.tmcode="+OfferID+" and x.tmcode  = b.tmcode and x.STATUS='P')"
////					+ "and b.STATUS='P'and a.SHDES = c.SHDES_PLAN";
//			ResultSet rsGetProductOffering = conn.createStatement().executeQuery(queryGetProductOffering);
//			
//			List<ProductOffering> listProductOffering = new ArrayList<ProductOffering>();
//			while (rsGetProductOffering.next()) {
//				ProductOffering productOffering = new ProductOffering();
//				
//				productOffering.setOfferId(rsGetProductOffering.getString("offerId"));
//				productOffering.setLastUpdate(rsGetProductOffering.getString("lastUpdate"));
//				productOffering.setShortDescription(rsGetProductOffering.getString("shortDescription"));
//				productOffering.setStatus(rsGetProductOffering.getString("status"));
//				productOffering.setName(rsGetProductOffering.getString("name"));
//				productOffering.setVersion(rsGetProductOffering.getString("version"));
//				productOffering.setIsSellable(rsGetProductOffering.getString("isSellable"));
//				productOffering.setMarketSegment(rsGetProductOffering.getString("marketSegment"));
//				productOffering.setFamilyOffer(rsGetProductOffering.getString("familyOffer"));
//				productOffering.setTypeOffer(rsGetProductOffering.getString("typeOffer"));
//				productOffering.setLayoutTypeAPP(rsGetProductOffering.getString("layoutTypeAPP"));
////				añade al objeto RecurringCharge
//				RecurringCharge recurringCharge = new RecurringCharge();
//				recurringCharge.setAmount((rsGetProductOffering.getString("amount_recurringCharge")));
//				recurringCharge.setCurrency((rsGetProductOffering.getString("currency_recurringCharge")));
////				recurringCharge.setFrecuency((rsGetProductOffering.getString("frecuency_recurringCharge")));
//				recurringCharge.setType((rsGetProductOffering.getString("type_recurringCharge")));
//				recurringCharge.setUnitOfMeasure((rsGetProductOffering.getString("unitOfMeasure_recurringCharge")));
//
//				productOffering.setRecurringCharge(recurringCharge);	
//				//Aqui va el bundle 2.1.1
//				//TODO: quitar el ROWNUM
//				List<BundleProductOffering> listBundleProductOffering = new ArrayList<BundleProductOffering>();
//				String queryGetBundleOfferingA="select b.ID_PROD as id,a.tmcode as offerId,a.SHDES as shDes,b.NAME_PROD as name,"
//						+ "b.DESC_PROD  as description,b.LEVEL_PCRF_PROD as priority,b.status as status,'1' as minimumRequired,'1' "
//						+ "as maximumAllowed,'false' as isOfferProduct,'true' as isOptionProduct,decode(b.promo,'S','True','N','False') "
//						+ "as isPromotionProduct,b.file1 as occ,b.sku as sku,b.CHANNEL_ACT as name_channel,b.CHANNEL_ACT as legacySystem_channel"
//						+ ",decode(b.recurrence,'N',b.tariff_prod) as amount_oneTime,decode(b.recurrence,'N','CLP') "
//						+ "as currency_oneTime,decode(b.recurrence,'N',b.VIGENCIA) as duration_oneTime,decode(b.recurrence,'N','Minutos') "
//						+ "as unitOfMesaure_oneTime,decode(b.recurrence,'N','Bundle') as type_oneTime,decode(b.recurrence,'S',b.tariff_prod) "
//						+ "as amount_recurringCharge,decode(b.recurrence,'S','CLP') as currency_recurringCharge,decode(b.recurrence,'S',b.VIGENCIA) "
//						+ "as duration_recurringCharge,decode(b.recurrence,'S','Minutos') as unitOfMesaure_recurringCharge,decode(b.recurrence,'S','Bundle') "
//						+ "as type_recurringCharge,b.traffic_type as unitType,b.unit_type as unitOfMesaure,b.unit_free as quantity from sysadm.rateplan a,"
//						+ "PROVI_BOLS.NEXTEL_CATALOGO_PRODUCTOS b "
//						+ "where  a.tmcode=336 and ROWNUM <= 10 "
//						+ "---variable =>tmcode=offerId and REGEXP_LIKE(to_char(a.tmcode), '^(|'||REPLACE(REPLACE(UPPER(FAMILY_PLAN),'',UPPER"
//						+ "(to_char(a.tmcode))),';','|')||')$')";
//				
//				ResultSet rsGetBundleOfferingA = conn.createStatement().executeQuery(queryGetBundleOfferingA);
//				while (rsGetBundleOfferingA.next()) {
//					BundleProductOffering bundleProductOffering = new BundleProductOffering();
//					
//		            bundleProductOffering.setMinimumRequired(rsGetBundleOfferingA.getString("minimumRequired"));
//		            bundleProductOffering.setQuantity(rsGetBundleOfferingA.getString("quantity"));
////		            bundleProductOffering.setUnitOfMeasure(rsGetBundleOfferingA.getString("unitOfMeasure"));
//		            bundleProductOffering.setIsPromotionProduct(rsGetBundleOfferingA.getString("isPromotionProduct"));
//		            bundleProductOffering.setDescription(rsGetBundleOfferingA.getString("description"));
//		            bundleProductOffering.setIsOfferProduct(rsGetBundleOfferingA.getString("isOfferProduct"));
//		            bundleProductOffering.setIsOptionProduct(rsGetBundleOfferingA.getString("isOptionProduct"));
//		            bundleProductOffering.setPriority(rsGetBundleOfferingA.getString("priority"));
//		            bundleProductOffering.setOcc(rsGetBundleOfferingA.getString("occ"));
//		            bundleProductOffering.setUnitType(rsGetBundleOfferingA.getString("unitType"));
//		            bundleProductOffering.setName(rsGetBundleOfferingA.getString("name"));
//		            bundleProductOffering.setOfferId(rsGetBundleOfferingA.getString("offerId"));
//		            bundleProductOffering.setShDes(rsGetBundleOfferingA.getString("shDes"));
//		            bundleProductOffering.setId(rsGetBundleOfferingA.getString("id"));
//		            bundleProductOffering.setSku(rsGetBundleOfferingA.getString("sku"));
////		            bundleProductOffering.setMaximunAllowed(rsGetBundleOfferingA.getString("maximunAllowed"));
//		            bundleProductOffering.setStatus(rsGetBundleOfferingA.getString("status"));
//		            
////		            añade channel
//		            Channel channelbundle= new Channel();
//		            channelbundle.setLegacySystem((rsGetBundleOfferingA.getString("legacySystem_channel")));
//		            channelbundle.setName((rsGetBundleOfferingA.getString("name_channel")));		            
//		            bundleProductOffering.setChannel(channelbundle);
//		            
////		            añade objeto RecurringCharge
//		            RecurringCharge recurringChargebundle = new RecurringCharge();
//					recurringChargebundle.setAmount((rsGetProductOffering.getString("amount_recurringCharge")));
//					recurringChargebundle.setCurrency((rsGetProductOffering.getString("currency_recurringCharge")));
////					recurringChargebundle.setFrecuency((rsGetProductOffering.getString("frecuency_recurringCharge")));
//					recurringChargebundle.setType((rsGetProductOffering.getString("type_recurringCharge")));
//					recurringChargebundle.setUnitOfMeasure((rsGetProductOffering.getString("unitOfMeasure_recurringCharge")));
//					
//					bundleProductOffering.setRecurringCharge(recurringChargebundle);
////		            añade OneTime
//		            OneTime onetime= new OneTime();
//                    onetime.setAmount(rsGetBundleOfferingA.getString("amount_oneTime"));
//                    onetime.setCurrency(rsGetBundleOfferingA.getString("currency_oneTime"));
//                    onetime.setDuration(rsGetBundleOfferingA.getString("duration_oneTime"));
//                    onetime.setUnitOfMeasure(rsGetBundleOfferingA.getString("unitOfMesaure_oneTime"));
//                    onetime.setType(rsGetBundleOfferingA.getString("type_oneTime"));                   
//                    bundleProductOffering.setOneTime(onetime);
//		            
//		            
//					listBundleProductOffering.add(bundleProductOffering);
//					
//				}
//				rsGetBundleOfferingA.close();
////				AÑADE NUEVO BUNDLE CON LA QUERY 2.2.1
//				
//				
//				String queryGetBundleOfferingB="select x.ID_PROD as id, a.tmcode as offerId,"
//						+ " a.SHDES as shDes, x.NAME_PROD as name, x.DESC_PROD as description,"
//						+ " x.LEVEL_PCRF_PROD as priority,  x.status as status, '1' as minimumRequired,"
//						+ "  '1' as maximumAllowed,  'True' as isOfferProduct, 'False' as isOptionProduct,"
//						+ " decode(x.promo,'S','True','N','False') as isPromotionProduct,  x.file1 as occ,"
//						+ "  x.sku  as sku,  x.CHANNEL_ACT as name_channel, "
//						+ " x.CHANNEL_ACT as legacySystem_channel, "
//						+ " decode(x.recurrence,'N',x.tariff_prod) as amount_oneTime, "
//						+ " decode(x.recurrence,'N','CLP') as currency_oneTime, "
//						+ " decode(x.recurrence,'N',x.VIGENCIA) as duration_oneTime,  "
//						+ "decode(x.recurrence,'N','Minutos') as unitOfMesaure_oneTime,  "
//						+ "decode(x.recurrence,'N','Bundle') as type_oneTime,  "
//						+ "decode(x.recurrence,'S',x.tariff_prod) as amount_recurringCharge,"
//						+ "  decode(x.recurrence,'S','CLP') as currency_recurringCharge, "
//						+ " decode(x.recurrence,'S',x.VIGENCIA) as duration_recurringCharge,"
//						+ "  decode(x.recurrence,'S','Minutos') as unitOfMesaure_recurringCharge, "
//						+ " decode(x.recurrence,'S','Bundle') as type_recurringCharge  "
//						+ "from  sysadm.rateplan  a,  sysadm.mpulktmb  b,  sysadm.mpulknxv  c, "
//						+ " sysadm.mpssvtab  d,  vmd.vmd_services e,  vmd.vmd_service_commands f, "
//						+ " PROVI_BOLS.NEXTEL_CATALOGO_PRODUCTOS x  where x.CHANNEL_ACT = 'PORTAL'"
//						+ " and a.tmcode = b.tmcode  and b.sncode = c.sncode  and c.sscode = d.SVCODE"
//						+ " and d.srvcode = e.bscs_code and e.SERVICE_ID = f.SERVICE_ID "
//						+ "and f.SUBSYSTEM_MARKET = 'PC2' and f.register_command = x.ID_PCRF_PROD "
//						+ "and ROWNUM <= 10";
//				ResultSet rsGetBundleOfferingB = conn.createStatement().executeQuery(queryGetBundleOfferingB);
//				while (rsGetBundleOfferingB.next()) {
//	
//				}
//				rsGetBundleOfferingB.close();
//		
//				
//				productOffering.setBundleProductOffering(listBundleProductOffering);
//				
//				
//				
//				
//				
//				listProductOffering.add(productOffering);
//			}
//			
//			
////			JSONObject jsonObj = new JSONObject( listProductOffering );
//			
////			JSONArray jsonObj = new org.json.JSONArray(listProductOffering);
//			JSONArray jsonArray = new JSONArray(listProductOffering);
//			rsGetProductOffering.close();	
//			stmt.close();
//			
//			return jsonArray.toString();
////			System.out.println("dentro del metodo: "+jsonArray.toString() );
////			rsGetProductOffering.close();
////			stmt.close();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			if (conn!=null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}
//		return "error";
//	
//	}
//
//}
