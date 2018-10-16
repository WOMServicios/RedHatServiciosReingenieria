package cl.wom.util;


import java.sql.Connection;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import cl.wom.beans.Cliente;
import cl.wom.database.ClienteDaoImpl;

import cl.wom.database.ConnectionFactory;
import cl.wom.database.ConnectionFactory.DataBaseSchema;
import cl.wom.exception.services.ServiceError;

public class ClienteProcessor implements Processor {
	private ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();

	@Override
	public void process(Exchange exchange) throws Exception {

		String headerBD = (String) exchange.getIn().getHeader("interface");
		System.err.println("base de datos " + headerBD);
		String sql = (String) exchange.getIn().getBody();

		Cliente cliente = null;
		Connection co;

		if (headerBD.equals("getInfoSuscriptorCarrierBilling")) {

			co = ConnectionFactory.getConnection(DataBaseSchema.BSCS);

			cliente = clienteDaoImpl.getInfoSuscriptorCarrierBilling(sql, co);
			

			if (cliente.getRut() == null) {
				throw new ServiceError("416");
			} else {
				exchange.setProperty("customerIdProperty",cliente.getCustomerId());
				exchange.setProperty("customerIdHighProperty",cliente.getCustomerIdHigh());
				exchange.setProperty("numCelularProperty",cliente.getNumCelular());
				exchange.setProperty("antiguedadProperty",cliente.getAntiguedad());
				exchange.setProperty("contractIdProperty",cliente.getContractId());
				exchange.setProperty("ratePlanProperty",cliente.getRateplan());
				exchange.setProperty("cargoBasicoProperty",cliente.getCargoBasico());
				exchange.getIn().setBody(cliente);
			}

			

		}
		
		
		if (headerBD.equals("getSuscripcionesCarrierExist")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);

			int contador = clienteDaoImpl.getSuscripcionesCarrierExist(sql, co);
			System.err.println("getSuscripcionesCarrierExist "+contador);
			
			exchange.getIn().setBody(contador);

		}
		if(headerBD.equals("getCustomerContractMoreOld")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			

			String dnNum = clienteDaoImpl.getCustomerContractMoreOld(sql, co);
			exchange.getIn().setBody(dnNum);
			
		}
		
		if(headerBD.equals("getCustomerPagador")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			

			String customerId = clienteDaoImpl.getCustomerPagador(sql, co);
			

			exchange.getIn().setBody(customerId);
		
			
		}
		
		if (headerBD.equals("getofertacarrier")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);

			cliente = clienteDaoImpl.getofertacarrier(sql, co);
			
			

			if (cliente != null) {
				exchange.setProperty("idOfertaProperty",cliente.getIdOferta());
				exchange.setProperty("desOfertaProperty",cliente.getDesOferta());
				//variable creada para realizar prueba y no duplicar pk
				exchange.setProperty("fechaPruebaProperty",new java.util.Date().getSeconds());
			
				exchange.getIn().setBody(cliente);
			} else {
				exchange.getIn().setBody(cliente);
			}

		}
		
		if (headerBD.equals("insertaregelegcarrierbilling")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);

			clienteDaoImpl.insertaregelegcarrierbilling(sql, co);
			exchange.getIn().setHeader("idOferta",exchange.getProperty("idOfertaProperty"));
			exchange.getIn().setHeader("desOferta",exchange.getProperty("desOfertaProperty"));


		}


	}

}
