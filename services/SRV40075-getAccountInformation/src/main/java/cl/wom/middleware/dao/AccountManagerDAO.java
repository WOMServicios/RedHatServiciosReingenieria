package cl.wom.middleware.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//import org.apache.camel.Exchange;

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
	static Properties prop = util.getProperties("APP_ENV");

	public AccountInformation getAccountInformation(String rut, String accountId) {

		rut = rut == null ? "" : rut;
		accountId = accountId == null ? "" : accountId;

		AccountInformation accountInformation = null;
		Connection conn = null;
		Statement stmt;
		try {
			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS, prop);
			stmt = conn.createStatement();

			// 1.1
			String whereCondition = rut.equals("") ? "a.customer_id = " + accountId + ""
					: "a.cscompregno = '" + rut + "'";
			String queryAccounts = "SELECT a.cscompregno as rut,a.CUSTOMER_ID as accountId,a.CUSTOMER_ID_HIGH as accountIdHigh,a.cslevel as csLevel,a.CUSTCODE as custCode,a.CSTYPE as accountType,a.CSACTIVATED as accountActivate,a.CSDEACTIVATED as accountDeactivate,a.CUSTOMER_ID_EXT  as externalAccountId,a.CSST as state,a.DOCTYPE_ID as docTypeId,b.DOCTYPE_DESC as docTypeDesc,b.DOCTYPE_OUTPUT_CODE as docTypeOutputCode,a.CUSTOMER_ID as accountId_biilcycle,d.BILLCYCLE as billCycle_billcycle,d.description as billCycleDes_billcycle,d.interval_type as intervalType,d.last_run_date as lastRunDate,d.bch_run_date as bchRunDate FROM sysadm.customer_all a, sysadm.DOCUMENT_TYPE_SII_CODE b, SYSADM.BILLCYCLE_ACTUAL_VIEW  c, SYSADM.BILLCYCLES  d WHERE "
					+ whereCondition
					+ " and a.DOCTYPE_ID  = b.DOCTYPE_ID and a.CUSTOMER_ID = c.CUSTOMER_ID and c.billcycle   = d.billcycle";
			ResultSet rsAccounts = conn.createStatement().executeQuery(queryAccounts);

			if (rsAccounts.next()) {
				accountInformation = new AccountInformation();
				accountInformation.setRut(rsAccounts.getString("rut"));

				List<Account> listAccount = new ArrayList<Account>();
				List<BillCycle> listaBillCycle = new ArrayList<BillCycle>();
				do {

					// Incorporación de subscriptores al objeto account
					List<Subscribers> listasubscribers = new ArrayList<Subscribers>();
					// 2.1
					String querySubscribers = "SELECT a.cscompregno      as rut, b.customer_id      as accountId, b.co_id as subscriberId, b.type as subscriberType, b.co_code          as subscriberIdContract, b.CO_SIGNED        as subscriberActivate, b.CO_EXPIR_DATE    as subscriberExpired, b.CH_STATUS        as state FROM sysadm.customer_all           a, SYSADM.contract_all           b, SYSADM.CONTRACT_HISTORY       c WHERE "
							+ whereCondition
							+ " and a.customer_id = b.customer_id and b.co_id       = c.co_id and c.ch_seqno = (select max(x.ch_seqno) FROM SYSADM.CONTRACT_HISTORY x WHERE x.co_id = c.co_id and x.ch_status   = 'a')";
					ResultSet rsSubscribers = conn.createStatement().executeQuery(querySubscribers);

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
						String whereCondition2 = subid.equals("") ? "a.customer_id = '" + acoid + "'"
								: "a.co_id = '" + subid + "'";
						String querySubscriberResources = "SELECT a.co_id as subscriberId,b.dn_id as resourceId,c.dn_num as resourceValue, 'Número de celular del subscriptor' as resourceDescription, b.CS_ACTIV_DATE as resourceActivate,b.CS_DEACTIV_DATE as resourceDeactivate,b.CS_STATUS as resourceState, 'MSISDN' as resourceType FROM sysadm.contract_all a, SYSADM.contr_services_cap  b, sysadm.directory_number c WHERE "
								+ whereCondition2 + " and a.co_id = b.co_id and b.dn_id = c.dn_id and b.sncode = 3";
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		System.out.println("accountInformation.toString(): "+accountInformation.toString());
		
		
		return accountInformation;
	}

	public String sqlGetRutAccountManager(String resourceType, String resourceValue) {

		Connection conn = null;
		Statement stmt;
		String subrut = "";
		try {
			conn = ConnectionFactory.getConnection(DataBaseSchema.BSCS, prop);
			stmt = conn.createStatement();

			if ((resourceType.equals("MSISDN"))) {
				// 3.1.2
				String queryMSISDN = "SELECT a.co_id as subscriberId,b.dn_id as resourceId,c.dn_num as resourceValue, 'Número de celular del subscriptor' as resourceDescription, b.CS_ACTIV_DATE as resourceActivate,b.CS_DEACTIV_DATE as resourceDeactivate,b.CS_STATUS as resourceState, 'MSISDN' as resourceType FROM sysadm.contract_all a, SYSADM.contr_services_cap  b, sysadm.directory_number    c WHERE C.DN_NUM = '"
						+ resourceValue + "' and a.co_id = b.co_id and b.dn_id = c.dn_id and b.sncode = 3";

				ResultSet rsSubId = stmt.executeQuery(queryMSISDN);

				if (rsSubId.next()) {
					String subId = rsSubId.getString("SUBSCRIBERID");

					String querySubId = "SELECT a.cscompregno      as rut, b.customer_id      as accountId, b.co_id            as subscriberId, b.type             as subscriberType, b.co_code          as subscriberIdContract, b.CO_SIGNED        as subscriberActivate, b.CO_EXPIR_DATE    as subscriberExpired, b.CH_STATUS        as state FROM sysadm.customer_all           a, SYSADM.contract_all           b, SYSADM.CONTRACT_HISTORY       c WHERE b.co_id = "
							+ subId
							+ " and a.customer_id = b.customer_id and b.co_id       = c.co_id and c.ch_seqno = (SELECT max(x.ch_seqno) FROM SYSADM.CONTRACT_HISTORY x WHERE x.co_id = c.co_id and x.ch_status   = 'a')";

					ResultSet rsRut = stmt.executeQuery(querySubId);

					if (rsRut.next()) {
						subrut = rsRut.getString("RUT");
					}
				}

			} else if ((resourceType.equals("IMEI"))) {

				String queryIMEI = "SELECT a.co_id as subscriberId, b.dn_id as resourceId, d.eq_serial_num as resourceValue, (e.description||'-'||e.company)  as resourceDescription, b.CS_ACTIV_DATE as resourceActivate, b.CS_DEACTIV_DATE as resourceDeactivate, d.eq_status as resourceState, 'IMEI' as resourceType FROM sysadm.contract_all a, sysadm.contr_services_cap  b, sysadm.contr_devices c, sysadm.equipment d, sysadm.equipment_type e WHERE d.eq_serial_num = '"
						+ resourceValue
						+ "' and b.co_id   = a.co_id and b.co_id   = c.co_id and c.eq_id   = d.equipment_id and d.equipment_type_id = e.equipment_type_id";

				ResultSet rsSubId = stmt.executeQuery(queryIMEI);

				if (rsSubId.next()) {
					String subId = rsSubId.getString("SUBSCRIBERID");

					String querySubId = "SELECT a.cscompregno      as rut, b.customer_id      as accountId, b.co_id            as subscriberId, b.type             as subscriberType, b.co_code          as subscriberIdContract, b.CO_SIGNED        as subscriberActivate, b.CO_EXPIR_DATE    as subscriberExpired, b.CH_STATUS        as state FROM sysadm.customer_all           a, SYSADM.contract_all           b, SYSADM.CONTRACT_HISTORY       c WHERE b.co_id = "
							+ subId
							+ " and a.customer_id = b.customer_id and b.co_id       = c.co_id and c.ch_seqno = (SELECT max(x.ch_seqno) FROM SYSADM.CONTRACT_HISTORY x WHERE x.co_id = c.co_id and x.ch_status   = 'a')";

					ResultSet rsRut = stmt.executeQuery(querySubId);

					if (rsRut.next()) {
						subrut = rsRut.getString("RUT");
					}
				}

			} else if ((resourceType.equals("IMSI"))) {
				// 3.2.2
				String queryIMSI = "SELECT a.co_id as subscriberId, b.dn_id as resourceId,d.port_num as resourceValue,'Número de IMSI del subscriptor'     as resourceDescription, b.CS_ACTIV_DATE as resourceActivate,b.CS_DEACTIV_DATE as resourceDeactivate, D.PORT_STATUS as resourceState, 'IMSI' as resourceType FROM sysadm.contract_all a, sysadm.contr_services_cap  b, sysadm.contr_devices c, sysadm.port d WHERE d.port_num = '"
						+ resourceValue + "' and b.co_id   = a.co_id and b.co_id   = c.co_id and c.port_id = d.port_id";

				ResultSet rsSubId = stmt.executeQuery(queryIMSI);

				if (rsSubId.next()) {
					String subId = rsSubId.getString("SUBSCRIBERID");

					String querySubId = "SELECT a.cscompregno      as rut, b.customer_id      as accountId, b.co_id            as subscriberId, b.type             as subscriberType, b.co_code          as subscriberIdContract, b.CO_SIGNED        as subscriberActivate, b.CO_EXPIR_DATE    as subscriberExpired, b.CH_STATUS        as state FROM sysadm.customer_all           a, SYSADM.contract_all           b, SYSADM.CONTRACT_HISTORY       c WHERE b.co_id = "
							+ subId
							+ " and a.customer_id = b.customer_id and b.co_id       = c.co_id and c.ch_seqno = (SELECT max(x.ch_seqno) FROM SYSADM.CONTRACT_HISTORY x WHERE x.co_id = c.co_id and x.ch_status   = 'a')";

					ResultSet rsRut = stmt.executeQuery(querySubId);

					if (rsRut.next()) {
						subrut = rsRut.getString("RUT");
					}
				}

			} else if ((resourceType.equals("ICCID"))) {

				// 3.3.2
				String queryICCID = "SELECT a.co_id as subscriberId, b.dn_id as resourceId,E.SM_SERIALNUM as resourceValue, 'Número de ICCID del subscriptor'    as resourceDescription,b.CS_ACTIV_DATE as resourceActivate,b.CS_DEACTIV_DATE as resourceDeactivate,E.SM_STATUS as resourceState, 'ICCID' as resourceType FROM sysadm.contract_all a, sysadm.contr_services_cap  b,sysadm.contr_devices c, sysadm.port d, sysadm.storage_medium e WHERE E.SM_SERIALNUM = '"
						+ resourceValue
						+ "' and b.co_id   = a.co_id and b.co_id   = c.co_id and c.port_id = d.port_id AND D.sm_id   = E.sm_id";

				ResultSet rsSubId = stmt.executeQuery(queryICCID);

				if (rsSubId.next()) {
					String subId = rsSubId.getString("SUBSCRIBERID");

					String querySubId = "SELECT a.cscompregno as rut, b.customer_id as accountId, b.co_id as subscriberId, b.type as subscriberType, b.co_code as subscriberIdContract, b.CO_SIGNED as subscriberActivate, b.CO_EXPIR_DATE as subscriberExpired, b.CH_STATUS as state FROM sysadm.customer_all a, SYSADM.contract_all b, SYSADM.CONTRACT_HISTORY c WHERE b.co_id = "
							+ subId
							+ " and a.customer_id = b.customer_id and b.co_id = c.co_id and c.ch_seqno = (SELECT max(x.ch_seqno) FROM SYSADM.CONTRACT_HISTORY x WHERE x.co_id = c.co_id and x.ch_status   = 'a')";

					ResultSet rsRut = stmt.executeQuery(querySubId);

					if (rsRut.next()) {
						subrut = rsRut.getString("RUT");
					}
				}
			}
			return subrut;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
}
