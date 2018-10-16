package cl.wom.middleware.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.json.JSONObject;

import cl.wom.middleware.vo.Account;
import cl.wom.middleware.vo.AccountInformation;
import cl.wom.middleware.vo.BillCycle;
import cl.wom.middleware.vo.SubscriberResources;
import cl.wom.middleware.vo.Subscribers;

public class SQLAccountProcessor {
	
	
	public void parserSQLAccountInformation(Exchange ex){
		
		AccountInformation accountInformation = new AccountInformation();
		accountInformation.setRut((String)ex.getIn().getHeader("rut"));
		
		System.out.println("dentro del flujo parserSQLAccountInformation");
		ex.setProperty("accountInformation", accountInformation);
	}
	
	
	public void parserSQLAccounts(Exchange ex){
		System.out.println("dentro del flujo parserSQLAccounts");
		AccountInformation accountInformation = (AccountInformation)ex.getProperty("accountInformation");
		
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> listAccountInf = (List<Map<String,Object>>) ex.getIn().getBody();
//		
		List<Account> listAccounts = new ArrayList<Account>();
		List<BillCycle> listaBillCycle = new ArrayList<BillCycle>();
		List<Subscribers> listasubscribers = new ArrayList<Subscribers>();
		List<SubscriberResources> listaSubscriberResources = new ArrayList<SubscriberResources>();
//		
		for (Map<String,Object> map :listAccountInf) {
			
			Account account = new Account();
			
			account.setAccountId(String.valueOf(map.get("ACCOUNTID")));
			account.setAccountIdHigh(String.valueOf (map.get("ACCOUNTIDHIGH")));
			account.setState(String.valueOf (map.get("STATE")));
			account.setCsLevel(String.valueOf (map.get("CSLEVEL")));
			account.setExternalAccountId(String.valueOf (map.get("EXTERNALACCOUNTID")));
			
				BillCycle billCycle = new BillCycle();
					billCycle.setBchRunDate(String.valueOf(map.get("BCHRUNDATE")));
					billCycle.setIntervalType(String.valueOf(map.get("INTERVALTYPE")));
					billCycle.setAccountId(String.valueOf(map.get("ACCOUNTID")));
					billCycle.setBillCycle(String.valueOf(map.get("BILLCYCLE")));
					billCycle.setLastRunDate(String.valueOf(map.get("LASTRUNDATE")));
					billCycle.setBillCycleDes(String.valueOf(map.get("BILLCYCLEDES")));
					listaBillCycle.add(billCycle);
					
			account.setBillCycle(billCycle);
			account.setDocTypeOutputCode(String.valueOf (map.get("DOCTYPEOUTPUTCODE")));
			account.setCustCode(String.valueOf (map.get("CUSTCODE")));
			account.setRut(String.valueOf (map.get("RUT")));
			account.setAccountType(String.valueOf (map.get("ACCOUNTTYPE")));
			
				Subscribers subscribers = new Subscribers();
					subscribers.setSubscriberType(String.valueOf(map.get("SUBSCRIBERTYPE")));
					
						SubscriberResources subscriberResources = new SubscriberResources();
							subscriberResources.setResourceId(String.valueOf(map.get("RESOURCEID")));
							subscriberResources.setResource(String.valueOf(map.get("RESOURCE")));
							subscriberResources.setSubscriberId(String.valueOf(map.get("SUBSCRIBERID")));
							subscriberResources.setResourceActivate(String.valueOf(map.get("RESOURCEACTIVATE")));
							subscriberResources.setResourceState(String.valueOf(map.get("RESOURCESTATE")));
							subscriberResources.setResourceDescription(String.valueOf(map.get("RESOURCEDESCRIPTION")));
							subscriberResources.setResourceDeactivate(String.valueOf(map.get("RESOURCEDEACTIVATE")));
							subscriberResources.setResourceType(String.valueOf(map.get("RESOURCETYPE")));
							listaSubscriberResources.add(subscriberResources);
						
					subscribers.setAccountId(String.valueOf(map.get("ACCOUNTID")));
					subscribers.setSubscriberExpired(String.valueOf(map.get("SUBSCRIBEREXPIRED")));
					subscribers.setRut(String.valueOf(map.get("RUT")));
					subscribers.setState(String.valueOf(map.get("STATE")));
					subscribers.setSubscriberId(String.valueOf(map.get("SUBSCRIBERID")));
					subscribers.setSubscriberActivate(String.valueOf(map.get("SUBSCRIBERACTIVATE")));
					subscribers.setSubscriberIdContract(String.valueOf(map.get("SUBSCRIBERIDCONTRACT")));
					listasubscribers.add(subscribers);
							
			account.setDocTypeDesc(String.valueOf (map.get("DOCTYPEDESC")));
			account.setAccountDeactivate(String.valueOf (map.get("ACCOUNTDEACTIVATE")));
			account.setAccountActivate(String.valueOf (map.get("ACCOUNTACTIVATE")));
			account.setDocTypeId(String.valueOf (map.get("DOCTYPEID")));
			
			listAccounts.add(account);
		}
		//System.out.println("accountInformation.toString(): "+accountInformation.toString());
		//System.out.println("listAccounts.toString(): "+ listAccounts.toString());
		accountInformation.setAccount(listAccounts);
		
		ex.setProperty("accountInformation", accountInformation);
		
	}	
	
	
	public void parserSQLSubscrption(Exchange ex){
		System.out.println("dentro del flujo parserSQLSubscrption");
		AccountInformation accountInformation = (AccountInformation)ex.getProperty("accountInformation");
		
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> listSuscriptions = (List<Map<String,Object>>) ex.getIn().getBody();
			
		System.out.println("listSuscriptions.toString(): "+listSuscriptions.toString());
		
	}	
	
	public String getAccountInformation(Exchange ex){
		JSONObject jsonObj = new JSONObject( (AccountInformation)ex.getProperty("accountInformation"));
		return jsonObj.toString() ;
	}	
	

}
