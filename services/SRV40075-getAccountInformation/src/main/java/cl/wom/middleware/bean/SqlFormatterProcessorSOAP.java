package cl.wom.middleware.bean;

import java.util.List;

import org.apache.camel.Exchange;
import org.json.JSONObject;
import org.json.XML;

import cl.wom.middleware.dao.AccountManagerDAO;
import cl.wom.middleware.vo.Account;
import cl.wom.middleware.vo.AccountInformation;

public class SqlFormatterProcessorSOAP {

	public AccountInformation sqlParserAccountInformationSOAP(Exchange ex) {

		String rut = (String) ex.getIn().getHeader("rut");
		String accountId = (String) ex.getIn().getHeader("accountId");

		AccountManagerDAO accountManagerDAO = new AccountManagerDAO();
		 AccountInformation accountInformation = accountManagerDAO.getAccountInformation(rut, accountId);
		 
		 
		 System.out.println(accountInformation);
		 
		 
		 
		 return accountInformation;

	}

	public String sqlGetRutAccountManager(Exchange ex) {

		String resourceType = (String) ex.getIn().getHeader("resourceType");
		String resourceValue = (String) ex.getIn().getHeader("resourceValue");

		AccountManagerDAO accountManagerDAO = new AccountManagerDAO();
		String rut = accountManagerDAO.sqlGetRutAccountManager(resourceType, resourceValue);

		return rut;
	}
}
