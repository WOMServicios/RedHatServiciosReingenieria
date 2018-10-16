package cl.wom.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import cl.wom.beans.Cliente;
import cl.wom.database.ClienteDaoImpl;
import cl.wom.database.Conexion;
import cl.wom.exception.services.ServiceError;

public class ClienteProcessor implements Processor {
	private ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();

	@Override
	public void process(Exchange exchange) throws Exception {

		String headerBD = (String) exchange.getIn().getHeader("database");
		System.err.println("base de datos " + headerBD);
		String sql = (String) exchange.getIn().getBody();

		Cliente cliente = null;
		Connection co;

		if (headerBD.contains("BSCSDESA")) {

			co = Conexion.conectar("knh#5tgl20k0lpm.l", "OPSH_BSCSREP_SYSADM_CBI", "10.120.241.44", "1550", "BSCSDESA");

			cliente = clienteDaoImpl.getInfoSuscriptorCarrierBilling(sql, co);
			

			if (cliente.getRut() == null) {
				throw new ServiceError("416");
			} else {
				exchange.getIn().setBody(cliente);
			}

			

		}
		
		
		if (headerBD.contains("WAPPLDESA")) {
			co = Conexion.conectar("carrierdes09", "CARRIERBILLING", "10.120.148.136", "1521", "WAPPLDESA");

			int contador = clienteDaoImpl.getSuscripcionesCarrierExist(sql, co);


			exchange.getIn().setBody(contador);

		}
		if(headerBD.contains("getCustomerContractMoreOld")) {
			co = Conexion.conectar("knh#5tgl20k0lpm.l", "OPSH_BSCSREP_SYSADM_CBI", "10.120.241.44", "1550", "BSCSDESA");
			

			String dnNum= clienteDaoImpl.getCustomerContractMoreOld(sql, co);
			if (dnNum == null) {
				throw new ServiceError("416");
			} else {
				System.err.println(dnNum+"prueba");
				exchange.getIn().setBody(dnNum);
			}
			
		}
		
		if(headerBD.contains("getCustomerPagador")) {
			co = Conexion.conectar("knh#5tgl20k0lpm.l", "OPSH_BSCSREP_SYSADM_CBI", "10.120.241.44", "1550", "BSCSDESA");
			

			String customerId= clienteDaoImpl.getCustomerPagador(sql, co);
			if (customerId== null) {
				throw new ServiceError("416");
			} else {
				
				exchange.getIn().setBody(customerId);
			}
			
		}
		
		if (headerBD.contains("getofertacarrier")) {
			co = Conexion.conectar("carrierdes09", "CARRIERBILLING", "10.120.148.136", "1521", "WAPPLDESA");

			cliente = clienteDaoImpl.getofertacarrier(sql, co);


			if (cliente.getIdOferta() == null) {
				throw new ServiceError("416");
			} else {
				exchange.getIn().setBody(cliente);
			}

		}
		
		if (headerBD.contains("insertaregelegcarrierbilling")) {
			co = Conexion.conectar("carrierdes09", "CARRIERBILLING", "10.120.148.136", "1521", "WAPPLDESA");

			clienteDaoImpl.insertaregelegcarrierbilling(sql, co);

		}


	}

}
