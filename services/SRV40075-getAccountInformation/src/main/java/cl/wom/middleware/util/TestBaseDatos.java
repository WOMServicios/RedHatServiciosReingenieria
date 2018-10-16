package cl.wom.middleware.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.vo.Account;
import cl.wom.middleware.vo.AccountInformation;
import cl.wom.middleware.vo.BillCycle;
import cl.wom.middleware.vo.SubscriberResources;
import cl.wom.middleware.vo.Subscribers;

public class TestBaseDatos {

	public static void main(String[] args) {
		new TestBaseDatos().getProductCatalog("010567335","");
	}
	
	
	public void getProductCatalog(String rut, String accountdID)  {
		
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
			
			System.out.println("conn: "+conn);
			

			String queryAccounts = "SELECT a.cscompregno      as rut,a.CUSTOMER_ID      as accountId,a.CUSTOMER_ID_HIGH as accountIdHigh,a.cslevel          as csLevel,a.CUSTCODE         as custCode,a.CSTYPE           as accountType,a.CSACTIVATED      as accountActivate,a.CSDEACTIVATED    as accountDeactivate,a.CUSTOMER_ID_EXT  as externalAccountId,a.CSST             as state,a.DOCTYPE_ID       as docTypeId,b.DOCTYPE_DESC     as docTypeDesc,b.DOCTYPE_OUTPUT_CODE as docTypeOutputCode,a.CUSTOMER_ID      as accountId_biilcycle,d.BILLCYCLE        as billCycle_billcycle,d.description      as billCycleDes_billcycle,d.interval_type    as intervalType,d.last_run_date    as lastRunDate,d.bch_run_date     as bchRunDate FROM sysadm.customer_all           a, sysadm.DOCUMENT_TYPE_SII_CODE b, SYSADM.BILLCYCLE_ACTUAL_VIEW  c, SYSADM.BILLCYCLES  d WHERE a.cscompregno = '010567335' and a.DOCTYPE_ID  = b.DOCTYPE_ID and a.CUSTOMER_ID = c.CUSTOMER_ID and c.billcycle   = d.billcycle";
			ResultSet rsAccounts = conn.createStatement().executeQuery(queryAccounts);
			
			
			AccountInformation accountInformation = new AccountInformation();
			accountInformation.setRut(rut);
			List<Account> listAccount = new ArrayList<Account>();
			List<BillCycle> listaBillCycle = new ArrayList<BillCycle>();
			List<Subscribers> listasubscribers = new ArrayList<Subscribers>();
			List<SubscriberResources> listaSubscriberResources = new ArrayList<SubscriberResources>();
			while (rsAccounts.next()) {
				
				Account account = new Account();
				
				Subscribers subscribers = new Subscribers();
					subscribers.setRut(rsAccounts.getString("rut"));
					subscribers.setAccountId(rsAccounts.getString("accountId"));
					
					SubscriberResources subscriberResources = new SubscriberResources();
//					subscriberResources.setResourceId(rsAccounts.getString("resourceId"));
//					subscriberResources.setResourceDeactivate(rsAccounts.getString("resourceDeactivate"));
//					subscriberResources.setResource(rsAccounts.getString("resource"));
//					subscriberResources.setSubscriberId(rsAccounts.getString("subscriberId"));
//					subscriberResources.setResourceDescription(rsAccounts.getString("resourceDescription"));
//					subscriberResources.setResourceState(rsAccounts.getString("resourceState"));
//					subscriberResources.setResourceActivate(rsAccounts.getString("resourceActivate"));
//					subscriberResources.setResourceType(rsAccounts.getString("resourceType"));
					listaSubscriberResources.add(subscriberResources);
					
//					subscribers.setSubscriberIdContract(rsAccounts.getString("subscriberIdContract"));
//					subscribers.setSubscriberType(rsAccounts.getString("subscriberType"));
//					subscribers.setSubscriberActivate(rsAccounts.getString("subscriberActivate"));
//					subscribers.setSubscriberId(rsAccounts.getString("subscriberId"));
					subscribers.setState(rsAccounts.getString("state"));
//					subscribers.setSubscriberExpired(rsAccounts.getString("subscriberExpired"));
					System.out.println("subscriberRUT:" + rsAccounts.getString("rut"));
					listasubscribers.add(subscribers);
					
				//account.setSubscribers(subscribers);
				account.setAccountType(rsAccounts.getString("accountType"));
				account.setDocTypeDesc(rsAccounts.getString("docTypeDesc"));
				account.setCustCode(rsAccounts.getString("custCode"));
				account.setRut(rsAccounts.getString("rut"));
				account.setCsLevel(rsAccounts.getString("csLevel"));
				account.setAccountId(rsAccounts.getString("accountId"));
				account.setAccountDeactivate(rsAccounts.getString("accountDeactivate"));
				account.setExternalAccountId(rsAccounts.getString("externalAccountId"));
				account.setDocTypeId(rsAccounts.getString("docTypeId"));
				account.setAccountActivate(rsAccounts.getString("accountActivate"));
				account.setDocTypeOutputCode(rsAccounts.getString("docTypeOutputCode"));
				account.setAccountIdHigh(rsAccounts.getString("accountIdHigh"));
				account.setState(rsAccounts.getString("state"));
				
				BillCycle billCycle = new BillCycle();
					billCycle.setBchRunDate(rsAccounts.getString("bchRunDate"));
					billCycle.setAccountId(rsAccounts.getString("accountId"));
					billCycle.setIntervalType(rsAccounts.getString("intervalType"));
					billCycle.setBillCycleDes(rsAccounts.getString("billCycleDes_billcycle"));
					billCycle.setLastRunDate(rsAccounts.getString("lastRunDate"));
					billCycle.setBillCycle(rsAccounts.getString("billCycle_billcycle"));
					
					listaBillCycle.add(billCycle);
					
				account.setBillCycle(billCycle);
				
//				ProductOffering productOffering = new ProductOffering();
//				
//				productOffering.setLastUpdate(rsGetProductOffering.getString("lastUpdate"));
				
				
//				List<BundleProductOffering> listBundleProductOffering = new ArrayList<BundleProductOffering>();
//				String queryGetBundleOfferingA = "select b.ID_PROD as id, a.tmcode as offerId, a.SHDES as shDes, b.NAME_PROD as name, b.DESC_PROD as description, b.LEVEL_PCRF_PROD as priority, b.status  as status, '1' as minimumRequired, '1' as maximumAllowed, 'false' as isOfferProduct, 'true' as isOptionProduct, decode(b.promo,'S','True','N','False') as isPromotionProduct, b.file1 as occ, b.sku as sku, b.CHANNEL_ACT as name_channel, b.CHANNEL_ACT as legacySystem_channel, decode(b.recurrence,'N',b.tariff_prod) as amount_oneTime, decode(b.recurrence,'N','CLP') as currency_oneTime, decode(b.recurrence,'N',b.VIGENCIA) as duration_oneTime, decode(b.recurrence,'N','Minutos') as unitOfMesaure_oneTime, decode(b.recurrence,'N','Bundle') as type_oneTime, decode(b.recurrence,'S',b.tariff_prod) as amount_recurringCharge, decode(b.recurrence,'S','CLP') as currency_recurringCharge, decode(b.recurrence,'S',b.VIGENCIA) as duration_recurringCharge, decode(b.recurrence,'S','Minutos') as unitOfMesaure_recurringCharge, decode(b.recurrence,'S','Bundle') as type_recurringCharge from sysadm.rateplan a, PROVI_BOLS.NEXTEL_CATALOGO_PRODUCTOS b "
//						+ "where a.tmcode="+OfferID+" and ROWNUM <= 10 ---variable   and REGEXP_LIKE(to_char(a.tmcode), '^(|'||REPLACE(REPLACE(UPPER(FAMILY_PLAN),'',UPPER(to_char(a.tmcode))),';','|')||')$')";
//				ResultSet rsGetBundleOfferingA = conn.createStatement().executeQuery(queryGetBundleOfferingA);
//				while (rsGetBundleOfferingA.next()) {
//					BundleProductOffering bundleProductOffering = new BundleProductOffering();
//					bundleProductOffering.setName(rsGetBundleOfferingA.getString("name"));
//					
//					listBundleProductOffering.add(bundleProductOffering);
//				}
//				productOffering.setBundleProductOffering(listBundleProductOffering);
				
				
				
				listAccount.add(account);
			}
			
			accountInformation.setAccount(listAccount);
			
			JSONObject jsonObj = new JSONObject( accountInformation);
			
			System.out.println("jsonObj: "+jsonObj);
//			JSONArray jsonArray = new JSONArray(accountInformation);
			
//			System.out.println("dentro del metodo: "+jsonArray.toString() );
//			rsGetProductOffering.close();
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
