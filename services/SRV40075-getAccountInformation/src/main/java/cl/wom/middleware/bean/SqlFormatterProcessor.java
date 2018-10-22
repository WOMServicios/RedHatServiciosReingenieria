package cl.wom.middleware.bean;

import org.apache.camel.Exchange;
import org.json.JSONObject;

import cl.wom.middleware.dao.AccountManagerDAO;
import cl.wom.middleware.vo.AccountInformation;

public class SqlFormatterProcessor {

	public String sqlParserAccountInformation(Exchange ex) {

		String rut = (String) ex.getIn().getHeader("rut");
		String accountId = (String) ex.getIn().getHeader("accountId");

		AccountManagerDAO accountManagerDAO = new AccountManagerDAO();
		AccountInformation accountInformation = accountManagerDAO.getAccountInformation(rut, accountId);

		if (accountInformation != null) {
			JSONObject jsonObj = new JSONObject(accountInformation);
			return jsonObj.toString();
		} else {
			return null;
		}
	}

	public String sqlGetRutAccountManager(Exchange ex) {

		String resourceType = (String) ex.getIn().getHeader("resourceType");
		String resourceValue = (String) ex.getIn().getHeader("resourceValue");

		AccountManagerDAO accountManagerDAO = new AccountManagerDAO();
		String rut = accountManagerDAO.sqlGetRutAccountManager(resourceType, resourceValue);

		return rut;
	}
	
	public AccountInformation sqlParserAccountInformationSOAP(Exchange ex) {

		String rut = (String) ex.getIn().getHeader("rut");
		String accountId = (String) ex.getIn().getHeader("accountId");

		AccountManagerDAO accountManagerDAO = new AccountManagerDAO();
		AccountInformation accountInformation = accountManagerDAO.getAccountInformation(rut, accountId);
		
		
		return accountInformation;

	}

}
