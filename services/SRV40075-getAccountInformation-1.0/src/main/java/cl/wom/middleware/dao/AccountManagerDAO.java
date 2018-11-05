package cl.wom.middleware.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cl.wom.exception.services.ServiceError;

import cl.wom.middleware.util.ConnectionFactory;
import cl.wom.middleware.util.ConnectionFactory.DataBaseSchema;
import cl.wom.middleware.util.PropertiesUtil;
import cl.wom.middleware.vo.Account;
import cl.wom.middleware.vo.AccountInformation;
import cl.wom.middleware.vo.BillCycle;
import cl.wom.middleware.vo.SubscriberResources;
import cl.wom.middleware.vo.Subscribers;

public class AccountManagerDAO {

	static PropertiesUtil util = new PropertiesUtil();
	PropertiesUtil sqlProperties = new PropertiesUtil();
	static Properties prop = util.getProperties("APP_ENV");

	public AccountInformation getAccountInformation(String rut, String accountId) throws ServiceError {
		

		rut = rut == null ? "" : rut;
		accountId = accountId == null ? "" : accountId;

		AccountInformation accountInformation = null;
		Connection conn = null;
		Statement stmt;
		try {
			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS, prop);
			stmt = conn.createStatement();

			// 1.1 o 1.2
			String whereCondition = rut.equals("") ? "a.customer_id = TRIM(" + accountId + ")"
					: "a.cscompregno = TRIM('" + rut + "')";
			System.out.println(whereCondition);
			String queryAccounts= MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryAccount"), whereCondition);
			System.out.println("query account: " + queryAccounts);
			ResultSet rsAccounts = stmt.executeQuery(queryAccounts);

			if (rsAccounts.next()) {
				accountInformation = new AccountInformation();
				accountInformation.setRut(rsAccounts.getString("rut"));

				List<Account> listAccount = new ArrayList<Account>();
				List<BillCycle> listaBillCycle = new ArrayList<BillCycle>();
				do {

					// Incorporación de subscriptores al objeto account
					List<Subscribers> listasubscribers = new ArrayList<Subscribers>();
					// 2.1 o 2.2
					String querySubscriber = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.querySubscriber"),whereCondition);
					ResultSet rsSubscribers = conn.createStatement().executeQuery(querySubscriber);

					while (rsSubscribers.next()) {

						Subscribers subscribers = new Subscribers();
						subscribers.setRut(rsSubscribers.getString("rut"));
						subscribers.setAccountId(rsSubscribers.getString("accountId"));
						subscribers.setSubscriberIdContract(rsSubscribers.getString("subscriberIdContract"));
						subscribers.setSubscriberType(rsSubscribers.getString("subscriberType"));
						subscribers.setSubscriberActivate(rsSubscribers.getString("subscriberActivate"));
						subscribers.setSubscriberId(rsSubscribers.getString("subscriberId"));
						subscribers.setState(rsSubscribers.getString("state"));
						subscribers.setSubscriberExpired(rsSubscribers.getString("subscriberExpired"));

						String subid = rsSubscribers.getString("subscriberId");
						String acoid = rsSubscribers.getString("accountId");

						// Incorporación de SubscriberResouces al objeto subscriber
						List<SubscriberResources> listaSubscriberResources = new ArrayList<SubscriberResources>();
						// 3.1.1
						String subscriberCondition = subid.equals("") ? "a.customer_id = '" + acoid + "'" : "a.co_id = '" + subid + "'";
						String querySubscriberResources = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.querySubscriberResources"),subscriberCondition);
						ResultSet rsSubscriberResoucers = conn.createStatement().executeQuery(querySubscriberResources);
						while (rsSubscriberResoucers.next()) {
							SubscriberResources subscriberResources = new SubscriberResources();
							subscriberResources.setResourceId(rsSubscriberResoucers.getString("resourceId"));
							subscriberResources.setResourceDeactivate(rsSubscriberResoucers.getString("resourceDeactivate"));
							subscriberResources.setResource(rsSubscriberResoucers.getString("resourceValue"));
							subscriberResources.setSubscriberId(rsSubscriberResoucers.getString("subscriberId"));
							subscriberResources.setResourceDescription(rsSubscriberResoucers.getString("resourceDescription"));
							subscriberResources.setResourceState(rsSubscriberResoucers.getString("resourceState"));
							subscriberResources.setResourceActivate(rsSubscriberResoucers.getString("resourceActivate"));
							subscriberResources.setResourceType(rsSubscriberResoucers.getString("resourceType"));
							listaSubscriberResources.add(subscriberResources);
						}
						subscribers.setSubscriberResources(listaSubscriberResources);

						listasubscribers.add(subscribers);
					}

					Account account = new Account();
					account.setSubscribers(listasubscribers);
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

					// Incorporación de BillCycle
					BillCycle billCycle = new BillCycle();
						billCycle.setBchRunDate(rsAccounts.getString("bchRunDate"));
						billCycle.setAccountId(rsAccounts.getString("accountId_biilcycle"));
						billCycle.setIntervalType(rsAccounts.getString("intervalType"));
						billCycle.setBillCycleDes(rsAccounts.getString("billCycleDes_billcycle"));
						billCycle.setLastRunDate(rsAccounts.getString("lastRunDate"));
						billCycle.setBillCycle(rsAccounts.getString("billCycle_billcycle"));
						listaBillCycle.add(billCycle);
						
					account.setBillCycle(billCycle);

					listAccount.add(account);

				} while (rsAccounts.next());

				accountInformation.setAccount(listAccount);
			}

			stmt.close();

			return accountInformation;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServiceError("455");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceError("455");
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServiceError("455");
				}
		}
	}

	public String sqlGetRutAccountManager(String resourceType, String resourceValue) throws ServiceError {

		Connection conn = null;
		Statement stmt;
		String subrut = "";
		try {
			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS, prop);
			stmt = conn.createStatement();

			if ((resourceType.equals("MSISDN"))) {

				// 3.1.2
				String queryMSISDN = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryMSISDN"),resourceValue);
				ResultSet rsSubId = stmt.executeQuery(queryMSISDN);

				if (rsSubId.next()) {
					String subId = rsSubId.getString("SUBSCRIBERID");

					String querySubId = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.querySubId"),subId);
					ResultSet rsRut = stmt.executeQuery(querySubId);

					if (rsRut.next()) {
						subrut = rsRut.getString("RUT");
					}
				}

			} else if ((resourceType.equals("IMEI"))) {
				
				String queryIMEI = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryIMEI"),resourceValue);
				ResultSet rsSubId = stmt.executeQuery(queryIMEI);

				if (rsSubId.next()) {
					String subId = rsSubId.getString("SUBSCRIBERID");

					String querySubId = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.querySubId"),subId);
					ResultSet rsRut = stmt.executeQuery(querySubId);

					if (rsRut.next()) {
						subrut = rsRut.getString("RUT");
					}
				}

			} else if ((resourceType.equals("IMSI"))) {
				// 3.2.2
				String queryIMSI = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryIMSI"),resourceValue);
				ResultSet rsSubId = stmt.executeQuery(queryIMSI);

				if (rsSubId.next()) {
					String subId = rsSubId.getString("SUBSCRIBERID");

					String querySubId = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.querySubId"),subId);
					ResultSet rsRut = stmt.executeQuery(querySubId);

					if (rsRut.next()) {
						subrut = rsRut.getString("RUT");
					}
				}

			} else if ((resourceType.equals("ICCID"))) {
				// 3.3.2
				String queryICCID = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.queryICCID"),resourceValue);
				ResultSet rsSubId = stmt.executeQuery(queryICCID);

				if (rsSubId.next()) {
					String subId = rsSubId.getString("SUBSCRIBERID");

					String querySubId = MessageFormat.format(sqlProperties.getLocalProperties().getProperty("sql.querySubId"),subId);
					ResultSet rsRut = stmt.executeQuery(querySubId);

					if (rsRut.next()) {
						subrut = rsRut.getString("RUT");
					}
				}
			}
			return subrut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServiceError("455");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceError("455");
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ServiceError("455");
				}
		}
	}
}
