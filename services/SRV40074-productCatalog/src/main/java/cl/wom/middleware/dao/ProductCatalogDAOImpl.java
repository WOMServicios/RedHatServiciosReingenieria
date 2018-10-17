package cl.wom.middleware.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.util.Util;
import cl.wom.middleware.vo.BundleProductOffering;
import cl.wom.middleware.vo.Channel;
import cl.wom.middleware.vo.OneTime;
import cl.wom.middleware.vo.ProductOffering;
import cl.wom.middleware.vo.RecurringCharge;

public class ProductCatalogDAOImpl {

	
	public List<ProductOffering> getProductCatalog(String OfferID, String shortDesc, String marketSeg) throws SQLException  {

		Properties prop = Util.getProperties("APP_ENV");
		Properties sql = Util.getProperties("SQL_ENV");

		//Donde APP_ENV es la variable de enbtorno

		//System.setProperty("database.bscs.host", "10.120.241.44");
		//System.setProperty("database.bscs.port", "1540");
		//System.setProperty("database.bscs.databasename", "BSCSUAT");
		//System.setProperty("database.bscs.username", "VMD");
		//System.setProperty("database.bscs.password", "VMD");
		
		String user = prop.getProperty("database.bscs.username");
		String password = prop.getProperty("database.bscs.password");
		String host = prop.getProperty("database.bscs.host");
		String port = prop.getProperty("database.bscs.port");
		String databaseName = prop.getProperty("database.bscs.databasename");
		
		String whereCondition = "";
		if (!"".equals(OfferID) && OfferID != null)
			whereCondition = "a.tmcode="+OfferID+"";
		
		if (!"".equals(shortDesc)  && shortDesc != null)
			whereCondition = "a.SHDES='"+shortDesc+"'";
		
		if (!"".equals(marketSeg)  && marketSeg != null)
			whereCondition = "c.SEGMENT_PLAN='"+marketSeg+"'";
		
		System.out.println("whereCondition: "+whereCondition);
		
		Connection conn = null;
		Statement stmt;
		List<ProductOffering> listProductOffering = new ArrayList<ProductOffering>();
		try {
			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			stmt = conn.createStatement();
			
			String getOfferIdQuery ="select a.tmcode from sysadm.rateplan a, PROVI_BOLS.NEXTEL_CATALOGO_PLAN c where "+whereCondition +" and a.SHDES = c.SHDES_PLAN";
			System.out.println("getOfferIdQuery: "+getOfferIdQuery);
			String offerId = "";
			String getShDescQuery ="select a.SHDES from sysadm.rateplan a, PROVI_BOLS.NEXTEL_CATALOGO_PLAN c where "+whereCondition +" and a.SHDES = c.SHDES_PLAN";
			System.out.println("getShDescQuery: "+getShDescQuery);
			String ShDesc= "";
			ResultSet rsGetOfferID = conn.createStatement().executeQuery(getOfferIdQuery);
			if (rsGetOfferID.next())
				offerId = rsGetOfferID.getString(1);
			else
				return listProductOffering;
			
			ResultSet rsGetShDesc = conn.createStatement().executeQuery(getShDescQuery);
			if (rsGetShDesc.next())
				ShDesc = rsGetShDesc.getString(1);
			else
				return listProductOffering;
			
			System.out.println("OfferId:"+offerId+""+"ShDesc:"+ShDesc);
			//GetProductOfferID 1.1
			String queryGetProductOffering="select a.tmcode as offerId, "
					+ "a.des as name,"
					+ "a.SHDES as shortDescription,"
					+ "b.apdate as lastUpdate,"
					+ "b.status as status,"
					+ "b.rec_version as version,"
					+ "'true' as isSellable,"
					+ "c.SEGMENT_PLAN as marketSegment,"
					+ "c.FAMILY_PLAN as familyOffer,"
					+ "c.TYPE_PLAN as typeOffer,"
					+ "c.LAYOUT_TYPE_APP as layoutTypeApp,"
					+ "c.ACCESS_FEE as amount_recurringCharge,"
					+ "'CLP' as currency_recurringCharge,'43200' "
					+ "as duration_recurringCharge,"
					+ "'minutos' as unitOfMeasure_recurringCharge,"
					+ "'Offer' as type_recurringCharge "
					+ "from sysadm.rateplan a,sysadm.rateplan_version b,"
					+ " PROVI_BOLS.NEXTEL_CATALOGO_PLAN c "
					+ "where "+whereCondition+" and b.rec_version = (select max(x.rec_version) "
					+ "from sysadm.rateplan_version x "
					+ " where x.tmcode = "+offerId+" and x.tmcode  = b.tmcode and x.STATUS='P') "
					+ "and b.STATUS='P' and a.SHDES = c.SHDES_PLAN";
			//String queryGetProductOffering=sql.getProperty("queryGetProductOffering");
			ResultSet rsGetProductOffering = conn.createStatement().executeQuery(queryGetProductOffering);		
			while (rsGetProductOffering.next()) {
				ProductOffering productOffering = new ProductOffering();
				
				productOffering.setOfferId(rsGetProductOffering.getString("offerId"));
				productOffering.setLastUpdate(rsGetProductOffering.getString("lastUpdate"));
				productOffering.setShortDescription(rsGetProductOffering.getString("shortDescription"));
				productOffering.setStatus(rsGetProductOffering.getString("status"));
				productOffering.setName(rsGetProductOffering.getString("name"));
				productOffering.setVersion(rsGetProductOffering.getString("version"));
				productOffering.setIsSellable(rsGetProductOffering.getString("isSellable"));
				productOffering.setMarketSegment(rsGetProductOffering.getString("marketSegment"));
				productOffering.setFamilyOffer(rsGetProductOffering.getString("familyOffer"));
				productOffering.setTypeOffer(rsGetProductOffering.getString("typeOffer"));
				productOffering.setLayoutTypeAPP(rsGetProductOffering.getString("layoutTypeAPP"));
				//añade al objeto RecurringCharge 
				
				
				RecurringCharge recurringCharge = new RecurringCharge();
				recurringCharge.setAmount((rsGetProductOffering.getString("amount_recurringCharge")));
				recurringCharge.setCurrency((rsGetProductOffering.getString("currency_recurringCharge")));
				//recurringCharge.setFrecuency((rsGetProductOffering.getString("frecuency_recurringCharge"))); //TODO JV: Error por conflicto de columna, SQL EXCEPTION 
				recurringCharge.setType((rsGetProductOffering.getString("type_recurringCharge")));
				recurringCharge.setUnitOfMeasure((rsGetProductOffering.getString("unitOfMeasure_recurringCharge")));

				productOffering.setRecurringCharge(recurringCharge);	
				//Aqui va el bundle 2.1.1
				//TODO: quitar el ROWNUM
				List<BundleProductOffering> listBundleProductOffering = new ArrayList<BundleProductOffering>();
				String queryGetBundleOfferingA="select b.ID_PROD as id,a.tmcode as offerId,a.SHDES as shDes,b.NAME_PROD as name,"
						+ "b.DESC_PROD  as description,b.LEVEL_PCRF_PROD as priority,b.status as status,'1' as minimumRequired,'1' "
						+ "as maximumAllowed,'false' as isOfferProduct,'true' as isOptionProduct,decode(b.promo,'S','True','N','False') "
						+ "as isPromotionProduct,b.file1 as occ,b.sku as sku,b.CHANNEL_ACT as name_channel,b.CHANNEL_ACT as legacySystem_channel"
						+ ",decode(b.recurrence,'N',b.tariff_prod) as amount_oneTime,decode(b.recurrence,'N','CLP') "
						+ "as currency_oneTime,decode(b.recurrence,'N',b.VIGENCIA) as duration_oneTime,decode(b.recurrence,'N','Minutos') "
						+ "as unitOfMesaure_oneTime,decode(b.recurrence,'N','Bundle') as type_oneTime,decode(b.recurrence,'S',b.tariff_prod) "
						+ "as amount_recurringCharge,decode(b.recurrence,'S','CLP') as currency_recurringCharge,decode(b.recurrence,'S',b.VIGENCIA) "
						+ "as duration_recurringCharge,decode(b.recurrence,'S','Minutos') as unitOfMesaure_recurringCharge,decode(b.recurrence,'S','Bundle') "
						+ "as type_recurringCharge,b.traffic_type as unitType,b.unit_type as unitOfMesaure,b.unit_free as quantity from sysadm.rateplan a,"
						+ "PROVI_BOLS.NEXTEL_CATALOGO_PRODUCTOS b "
						+ "where  a.tmcode="+productOffering.getOfferId()+" and ROWNUM <= 10 "
						+ "---variable =>tmcode=offerId and REGEXP_LIKE(to_char(a.tmcode), '^(|'||REPLACE(REPLACE(UPPER(FAMILY_PLAN),'',UPPER"
						+ "(to_char(a.tmcode))),';','|')||')$')";
				
				ResultSet rsGetBundleOfferingA = conn.createStatement().executeQuery(queryGetBundleOfferingA);
				while (rsGetBundleOfferingA.next()) {
					BundleProductOffering bundleProductOffering = new BundleProductOffering();
					
		            bundleProductOffering.setMinimumRequired(rsGetBundleOfferingA.getString("minimumRequired"));
		            bundleProductOffering.setQuantity(rsGetBundleOfferingA.getString("quantity"));
		            bundleProductOffering.setUnitOfMeasure(rsGetBundleOfferingA.getString("unitOfMesaure")); 
		            bundleProductOffering.setIsPromotionProduct(rsGetBundleOfferingA.getString("isPromotionProduct"));
		            bundleProductOffering.setDescription(rsGetBundleOfferingA.getString("description"));
		            bundleProductOffering.setIsOfferProduct(rsGetBundleOfferingA.getString("isOfferProduct"));
		            bundleProductOffering.setIsOptionProduct(rsGetBundleOfferingA.getString("isOptionProduct"));
		            bundleProductOffering.setPriority(rsGetBundleOfferingA.getString("priority"));
		            bundleProductOffering.setOcc(rsGetBundleOfferingA.getString("occ"));
		            bundleProductOffering.setUnitType(rsGetBundleOfferingA.getString("unitType"));
		            bundleProductOffering.setName(rsGetBundleOfferingA.getString("name"));
		            bundleProductOffering.setOfferId(rsGetBundleOfferingA.getString("offerId"));
		            bundleProductOffering.setShDes(rsGetBundleOfferingA.getString("shDes"));
		            bundleProductOffering.setId(rsGetBundleOfferingA.getString("id"));
		            bundleProductOffering.setSku(rsGetBundleOfferingA.getString("sku"));
		            bundleProductOffering.setMaximunAllowed(rsGetBundleOfferingA.getString("maximumAllowed")); 
		            bundleProductOffering.setStatus(rsGetBundleOfferingA.getString("status"));
		            
		            //añade channel
		            Channel channelbundle= new Channel();
		            channelbundle.setLegacySystem((rsGetBundleOfferingA.getString("legacySystem_channel")));
		            channelbundle.setName((rsGetBundleOfferingA.getString("name_channel")));		            
		            bundleProductOffering.setChannel(channelbundle);
		            
		            //añade objeto RecurringCharge
		            RecurringCharge recurringChargebundle = new RecurringCharge();
					recurringChargebundle.setAmount((rsGetBundleOfferingA.getString("amount_recurringCharge")));
					recurringChargebundle.setCurrency((rsGetBundleOfferingA.getString("currency_recurringCharge")));
					//recurringChargebundle.setFrecuency((rsGetBundleOfferingA.getString("frecuency_recurringCharge")));  //TODO JV: Error por conflicto de columna, SQL EXCEPTION 
					recurringChargebundle.setType((rsGetBundleOfferingA.getString("type_recurringCharge")));
					recurringChargebundle.setUnitOfMeasure((rsGetBundleOfferingA.getString("unitOfMesaure_recurringCharge")));
					
					bundleProductOffering.setRecurringCharge(recurringChargebundle);
					//añade OneTime
		            OneTime onetime= new OneTime();
                    onetime.setAmount(rsGetBundleOfferingA.getString("amount_oneTime"));
                    onetime.setCurrency(rsGetBundleOfferingA.getString("currency_oneTime"));
                    onetime.setDuration(rsGetBundleOfferingA.getString("duration_oneTime"));
                    onetime.setUnitOfMeasure(rsGetBundleOfferingA.getString("unitOfMesaure_oneTime"));
                    onetime.setType(rsGetBundleOfferingA.getString("type_oneTime"));                   
                    bundleProductOffering.setOneTime(onetime);
		            
					listBundleProductOffering.add(bundleProductOffering);
				}
				rsGetBundleOfferingA.close();
				
				//2.2.1
				String queryGetBundleOfferingB="select  x.ID_PROD  as id,"
						+ " a.tmcode   as offerId, "
						+ " a.SHDES        as shDes,"
						+ "  x.NAME_PROD                            as name, "
						+ " x.DESC_PROD                            as description,"
						+ "  x.LEVEL_PCRF_PROD                      as priority,"
						+ "  x.status                               as status,"
						+ "  '1'                                    as minimumRequired,"
						+ "  '1'                                    as maximumAllowed,"
						+ "  'True'                                as isOfferProduct,"
						+ "  'False'                                 as isOptionProduct,"
						+ " decode(x.promo,'S','True','N','False') as isPromotionProduct, "
						+ " x.file1                                as occ,"
						+ "  x.sku                                  as sku, "
						+ " x.CHANNEL_ACT                          as name_channel,"
						+ "  x.CHANNEL_ACT                          as legacySystem_channel, "
						+ " decode(x.recurrence,'N',x.tariff_prod) as amount_oneTime,  "
						+ "decode(x.recurrence,'N','CLP')         as currency_oneTime,"
						+ " decode(x.recurrence,'N',x.VIGENCIA)    as duration_oneTime, "
						+ " decode(x.recurrence,'N','Minutos')     as unitOfMesaure_oneTime,  "
						+ "decode(x.recurrence,'N','Bundle')      as type_oneTime,"
						+ "  decode(x.recurrence,'S',x.tariff_prod) as amount_recurringCharge, "
						+ " decode(x.recurrence,'S','CLP')         as currency_recurringCharge, "
						+ " decode(x.recurrence,'S',x.VIGENCIA)    as duration_recurringCharge, "
						+ " decode(x.recurrence,'S','Minutos')     as unitOfMesaure_recurringCharge, "
						+ " decode(x.recurrence,'S','Bundle')      as type_recurringCharge,  "
						+ "x.traffic_type                         as unitType,"
						+ "  x.unit_type                            as unitOfMesaure, "
						+ " x.unit_free                            as quantity "
						+ " from  sysadm.rateplan  a,  sysadm.mpulktmb  b,  sysadm.mpulknxv  c,  sysadm.mpssvtab  d,  vmd.vmd_services e,  vmd.vmd_service_commands f, "
						+ " PROVI_BOLS.NEXTEL_CATALOGO_PRODUCTOS x "
						+ " where      a.tmcode="+productOffering.getOfferId()+"  and a.tmcode = b.tmcode  and b.sncode = c.sncode "
						+ " and c.sscode = d.SVCODE  and d.srvcode = e.bscs_code   and e.SERVICE_ID = f.SERVICE_ID  and f.SUBSYSTEM_MARKET = 'PC2' "
						+ " and f.register_command = x.ID_PCRF_PROD and ROWNUM <=10";
				ResultSet rsGetBundleOfferingB = conn.createStatement().executeQuery(queryGetBundleOfferingB);
				while (rsGetBundleOfferingB.next()) {
					BundleProductOffering bundleProductOffering = new BundleProductOffering();
					
		            bundleProductOffering.setMinimumRequired(rsGetBundleOfferingB.getString("minimumRequired"));
		            bundleProductOffering.setQuantity(rsGetBundleOfferingB.getString("quantity"));
		            bundleProductOffering.setUnitOfMeasure(rsGetBundleOfferingB.getString("unitOfMesaure"));  //TODO JV: Error por conflicto de columna, SQL EXCEPTION 
		            bundleProductOffering.setIsPromotionProduct(rsGetBundleOfferingB.getString("isPromotionProduct"));
		            bundleProductOffering.setDescription(rsGetBundleOfferingB.getString("description"));
		            bundleProductOffering.setIsOfferProduct(rsGetBundleOfferingB.getString("isOfferProduct"));
		            bundleProductOffering.setIsOptionProduct(rsGetBundleOfferingB.getString("isOptionProduct"));
		            bundleProductOffering.setPriority(rsGetBundleOfferingB.getString("priority"));
		            bundleProductOffering.setOcc(rsGetBundleOfferingB.getString("occ"));
		            bundleProductOffering.setUnitType(rsGetBundleOfferingB.getString("unitType"));
		            bundleProductOffering.setName(rsGetBundleOfferingB.getString("name"));
		            bundleProductOffering.setOfferId(rsGetBundleOfferingB.getString("offerId"));
		            bundleProductOffering.setShDes(rsGetBundleOfferingB.getString("shDes"));
		            bundleProductOffering.setId(rsGetBundleOfferingB.getString("id"));
		            bundleProductOffering.setSku(rsGetBundleOfferingB.getString("sku"));
		            bundleProductOffering.setMaximunAllowed(rsGetBundleOfferingB.getString("maximumAllowed"));  //TODO JV: Error por conflicto de columna, SQL EXCEPTION 
		            bundleProductOffering.setStatus(rsGetBundleOfferingB.getString("status"));
		            
		            //añade channel
		            Channel channelbundle= new Channel();
		            channelbundle.setLegacySystem((rsGetBundleOfferingB.getString("legacySystem_channel")));
		            channelbundle.setName((rsGetBundleOfferingB.getString("name_channel")));		            
		            bundleProductOffering.setChannel(channelbundle);
		            
		            //añade objeto RecurringCharge
		            RecurringCharge recurringChargebundle = new RecurringCharge();
					recurringChargebundle.setAmount((rsGetBundleOfferingB.getString("amount_recurringCharge")));
					recurringChargebundle.setCurrency((rsGetBundleOfferingB.getString("currency_recurringCharge")));
					//recurringChargebundle.setFrecuency((rsGetBundleOfferingB.getString("frecuency_recurringCharge")));  //TODO JV: Error por conflicto de columna, SQL EXCEPTION 
					recurringChargebundle.setType((rsGetBundleOfferingB.getString("type_recurringCharge")));
					recurringChargebundle.setUnitOfMeasure((rsGetBundleOfferingB.getString("unitOfMesaure_recurringCharge")));
					
					bundleProductOffering.setRecurringCharge(recurringChargebundle);
					// Aniade OneTime
		            OneTime onetime= new OneTime();
                    onetime.setAmount(rsGetBundleOfferingB.getString("amount_oneTime"));
                    onetime.setCurrency(rsGetBundleOfferingB.getString("currency_oneTime"));
                    onetime.setDuration(rsGetBundleOfferingB.getString("duration_oneTime"));
                    onetime.setUnitOfMeasure(rsGetBundleOfferingB.getString("unitOfMesaure_oneTime"));
                    onetime.setType(rsGetBundleOfferingB.getString("type_oneTime"));                   
                    bundleProductOffering.setOneTime(onetime);
		            
					listBundleProductOffering.add(bundleProductOffering);
				}
				rsGetBundleOfferingB.close();
				
				
				
				//2.2.2
				String queryGetBundleOfferingC=" select " + 
						"  b.tmcode                              as id," + 
						"  b.tmcode                               as offerId," + 
						"  b.SHDES                                as shDes," + 
						"  'Bolsa de Voz'                         as name," + 
						"  'Bolsa de Voz'                         as description," + 
						"  'null'                                 as priority," + 
						"  'a'                                    as status," + 
						"  '1'                                    as minimumRequired," + 
						"  '1'                                    as maximumAllowed," + 
						"  'true'                                 as isOfferProduct," + 
						"  'false'                                as isOptionProduct," + 
						"  'N'                                    as isPromotionProduct," + 
						"  'null'                                 as occ," + 
						"  'null'                                 as sku," + 
						"  'null'                                 as name_channel," + 
						"  'null'                                 as legacySystem_channel," + 
						"  'N'                                    as amount_oneTime," + 
						"  'N'                                    as currency_oneTime," + 
						"  'N'                                    as duration_oneTime," + 
						"  'N'                                    as unitOfMesaure_oneTime," + 
						"  'N'                                    as type_oneTime," + 
						"  '0'                                    as amount_recurringCharge," + 
						"  'CLP'                                  as currency_recurringCharge," + 
						"  '43200'                                as duration_recurringCharge," + 
						"  'Minutos'                              as unitOfMesaure_recurringCharge," + 
						"  'Bundle'                               as type_recurringCharge," + 
						"  'VOZ'                                  as unitType," + 
						"  'Minutos'                              as unitOfMesaure," + 
						"  a.unit_all_dest                        as quantity from  " + 
						"    PROVI_BOLS.NEXTEL_CATALOGO_PLAN a," + 
						"    sysadm.rateplan                 b where" + 
						"    a.shdes_plan = '"+ShDesc+"'" + 
						"and a.shdes_plan = b.shdes and ROWNUM <=10";
				
				System.out.println("imprimiendo shdes_plan:: "+productOffering.getShortDescription());
				System.out.println("imprimiendo ShDesc:: "+ShDesc);
				
				ResultSet rsGetBundleOfferingC = conn.createStatement().executeQuery(queryGetBundleOfferingC);
				while (rsGetBundleOfferingC.next()) {
					BundleProductOffering bundleProductOffering = new BundleProductOffering();
					
					 bundleProductOffering.setMinimumRequired(rsGetBundleOfferingC.getString("minimumRequired"));
			            bundleProductOffering.setQuantity(rsGetBundleOfferingC.getString("quantity"));
			            bundleProductOffering.setUnitOfMeasure(rsGetBundleOfferingC.getString("unitOfMesaure"));    
			            bundleProductOffering.setIsPromotionProduct(rsGetBundleOfferingC.getString("isPromotionProduct"));
			            bundleProductOffering.setDescription(rsGetBundleOfferingC.getString("description"));
			            bundleProductOffering.setIsOfferProduct(rsGetBundleOfferingC.getString("isOfferProduct"));
			            bundleProductOffering.setIsOptionProduct(rsGetBundleOfferingC.getString("isOptionProduct"));
			            bundleProductOffering.setPriority(rsGetBundleOfferingC.getString("priority"));
			            bundleProductOffering.setOcc(rsGetBundleOfferingC.getString("occ"));
			            bundleProductOffering.setUnitType(rsGetBundleOfferingC.getString("unitType"));
			            bundleProductOffering.setName(rsGetBundleOfferingC.getString("name"));
			            bundleProductOffering.setOfferId(rsGetBundleOfferingC.getString("offerId"));
			            bundleProductOffering.setShDes(rsGetBundleOfferingC.getString("shDes"));
			            bundleProductOffering.setId(rsGetBundleOfferingC.getString("id"));
			            bundleProductOffering.setSku(rsGetBundleOfferingC.getString("sku"));
			            bundleProductOffering.setMaximunAllowed(rsGetBundleOfferingC.getString("maximumAllowed"));  
			            bundleProductOffering.setStatus(rsGetBundleOfferingC.getString("status"));
			            
			            //añade channel
			            Channel channelbundle= new Channel();
			            channelbundle.setLegacySystem((rsGetBundleOfferingC.getString("legacySystem_channel")));
			            channelbundle.setName((rsGetBundleOfferingC.getString("name_channel")));		            
			            bundleProductOffering.setChannel(channelbundle);
			            
			            //añade objeto RecurringCharge
			            RecurringCharge recurringChargebundle = new RecurringCharge();
						recurringChargebundle.setAmount((rsGetBundleOfferingC.getString("amount_recurringCharge")));
						recurringChargebundle.setCurrency((rsGetBundleOfferingC.getString("currency_recurringCharge")));
						//recurringChargebundle.setFrecuency((rsGetBundleOfferingC.getString("frecuency_recurringCharge")));  //TODO JV: Error por conflicto de columna, SQL EXCEPTION 
						recurringChargebundle.setType((rsGetBundleOfferingC.getString("type_recurringCharge")));
						recurringChargebundle.setUnitOfMeasure((rsGetBundleOfferingC.getString("unitOfMesaure_recurringCharge")));
						
						bundleProductOffering.setRecurringCharge(recurringChargebundle);
						// Aniade OneTime
			            OneTime onetime= new OneTime();
	                    onetime.setAmount(rsGetBundleOfferingC.getString("amount_oneTime"));
	                    onetime.setCurrency(rsGetBundleOfferingC.getString("currency_oneTime"));
	                    onetime.setDuration(rsGetBundleOfferingC.getString("duration_oneTime"));
	                    onetime.setUnitOfMeasure(rsGetBundleOfferingC.getString("unitOfMesaure_oneTime"));
	                    onetime.setType(rsGetBundleOfferingC.getString("type_oneTime"));                   
	                    bundleProductOffering.setOneTime(onetime);
			            
						listBundleProductOffering.add(bundleProductOffering);
					}
					rsGetBundleOfferingC.close();
					
					
					
					//2.2.3
					String queryGetBundleOfferingD="select " + 
							"  b.tmcode                              as id," + 
							"  b.tmcode                               as offerId," + 
							"  b.SHDES                                as shDes," + 
							"  'Bolsa de Voz'                         as name," + 
							"  'Bolsa de Voz'                         as description," + 
							"  'null'                                 as priority," + 
							"  'a'                                    as status," + 
							"  '1'                                    as minimumRequired," + 
							"  '1'                                    as maximumAllowed," + 
							"  'true'                                 as isOfferProduct," + 
							"  'false'                                as isOptionProduct," + 
							"  'N'                                    as isPromotionProduct," + 
							"  'null'                                 as occ," + 
							"  'null'                                 as sku," + 
							"  'null'                                 as name_channel," + 
							"  'null'                                 as legacySystem_channel," + 
							"  'N'                                    as amount_oneTime," + 
							"  'N'                                    as currency_oneTime," + 
							"  'N'                                    as duration_oneTime," + 
							"  'N'                                    as unitOfMesaure_oneTime," + 
							"  'N'                                    as type_oneTime," + 
							"  '0'                                    as amount_recurringCharge," + 
							"  'CLP'                                  as currency_recurringCharge," + 
							"  '43200'                                as duration_recurringCharge," + 
							"  'Minutos'                              as unitOfMesaure_recurringCharge," + 
							"  'Bundle'                               as type_recurringCharge," + 
							"  'SMS'                                  as unitType," + 
							"  'Unitario'                              as unitOfMesaure," + 
							"  a.unit_sms                        as quantity from  " + 
							"    PROVI_BOLS.NEXTEL_CATALOGO_PLAN a," + 
							"    sysadm.rateplan                 b where" + 
							"    a.shdes_plan = '"+ShDesc+"'" + 
							"and a.shdes_plan = b.shdes and ROWNUM <=10";
				          ResultSet rsGetBundleOfferingD = conn.createStatement().executeQuery(queryGetBundleOfferingD);
				          while (rsGetBundleOfferingD.next()) {
				            BundleProductOffering bundleProductOffering = new BundleProductOffering();
				            
		             		bundleProductOffering.setMinimumRequired(rsGetBundleOfferingD.getString("minimumRequired"));
		                    bundleProductOffering.setQuantity(rsGetBundleOfferingD.getString("quantity"));
		                    bundleProductOffering.setUnitOfMeasure(rsGetBundleOfferingD.getString("unitOfMesaure"));
		                    bundleProductOffering.setIsPromotionProduct(rsGetBundleOfferingD.getString("isPromotionProduct"));
		                    bundleProductOffering.setDescription(rsGetBundleOfferingD.getString("description"));
		                    bundleProductOffering.setIsOfferProduct(rsGetBundleOfferingD.getString("isOfferProduct"));
		                    bundleProductOffering.setIsOptionProduct(rsGetBundleOfferingD.getString("isOptionProduct"));
		                    bundleProductOffering.setPriority(rsGetBundleOfferingD.getString("priority"));
		                    bundleProductOffering.setOcc(rsGetBundleOfferingD.getString("occ"));
		                    bundleProductOffering.setUnitType(rsGetBundleOfferingD.getString("unitType"));
		                    bundleProductOffering.setName(rsGetBundleOfferingD.getString("name"));
		                    bundleProductOffering.setOfferId(rsGetBundleOfferingD.getString("offerId"));
		                    bundleProductOffering.setShDes(rsGetBundleOfferingD.getString("shDes"));
		                    bundleProductOffering.setId(rsGetBundleOfferingD.getString("id"));
		                    bundleProductOffering.setSku(rsGetBundleOfferingD.getString("sku"));
		                    bundleProductOffering.setMaximunAllowed(rsGetBundleOfferingD.getString("maximumAllowed")); 
		                    bundleProductOffering.setStatus(rsGetBundleOfferingD.getString("status"));
		                    
	                    	//añade channel
		                    Channel channelbundle= new Channel();
		                    channelbundle.setLegacySystem((rsGetBundleOfferingD.getString("legacySystem_channel")));
		                    channelbundle.setName((rsGetBundleOfferingD.getString("name_channel")));                
		                    bundleProductOffering.setChannel(channelbundle);
				                    
			                  //añade objeto RecurringCharge
		                      RecurringCharge recurringChargebundle = new RecurringCharge();
				              recurringChargebundle.setAmount((rsGetBundleOfferingD.getString("amount_recurringCharge")));
				              recurringChargebundle.setCurrency((rsGetBundleOfferingD.getString("currency_recurringCharge")));
		              		  //recurringChargebundle.setFrecuency((rsGetBundleOfferingD.getString("frecuency_recurringCharge")));  
				              //TODO JV: Error por conflicto de columna,no esta considerado, SQL EXCEPTION 
				              recurringChargebundle.setType((rsGetBundleOfferingD.getString("type_recurringCharge")));
				              recurringChargebundle.setUnitOfMeasure((rsGetBundleOfferingD.getString("unitOfMesaure_recurringCharge")));
				              bundleProductOffering.setRecurringCharge(recurringChargebundle);
				              
				              // Aniade OneTime
				                    OneTime onetime= new OneTime();
				                        onetime.setAmount(rsGetBundleOfferingD.getString("amount_oneTime"));
				                        onetime.setCurrency(rsGetBundleOfferingD.getString("currency_oneTime"));
				                        onetime.setDuration(rsGetBundleOfferingD.getString("duration_oneTime"));
				                        onetime.setUnitOfMeasure(rsGetBundleOfferingD.getString("unitOfMesaure_oneTime"));
				                        onetime.setType(rsGetBundleOfferingD.getString("type_oneTime"));                   
				                        bundleProductOffering.setOneTime(onetime);
				                        
				              listBundleProductOffering.add(bundleProductOffering);
				            }
				            rsGetBundleOfferingD.close();
			
				productOffering.setBundleProductOffering(listBundleProductOffering);
				listProductOffering.add(productOffering);
			}
			
			
			rsGetProductOffering.close();	
			stmt.close();

		} catch (SQLException e) {
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
		return listProductOffering;
	
	}

}
