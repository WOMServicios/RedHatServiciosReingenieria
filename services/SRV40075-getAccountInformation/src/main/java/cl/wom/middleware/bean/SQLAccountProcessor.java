package cl.wom.middleware.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;

import cl.wom.middleware.vo.Account;
import cl.wom.middleware.vo.AccountInformation;

public class SQLAccountProcessor {
	
	
	public void parserSQLAccountInformation(Exchange ex){
		
		AccountInformation accountInformation = new AccountInformation();
		accountInformation.setRut((String)ex.getIn().getHeader("rutCesar"));
		
		ex.setProperty("accountInformation", accountInformation);
	}
	
	
	public AccountInformation parserSQLAccounts(Exchange ex){
		
		AccountInformation accountInformation = (AccountInformation)ex.getProperty("accountInformation");
		
		
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> listAccount = (List<Map<String,Object>>) ex.getIn().getBody();
		
		List<Account> listAccounts = new ArrayList<Account>();
		for (Map<String,Object> map :listAccount) {
			
			Account account = new Account();
			
			account.setAccountId((String) map.get("accountId"));
			
			
			
			
			listAccounts.add(account);
			
		}
		
		
		accountInformation.setAccount((Account[])listAccounts.toArray());
		
		return accountInformation;
	}	

}
