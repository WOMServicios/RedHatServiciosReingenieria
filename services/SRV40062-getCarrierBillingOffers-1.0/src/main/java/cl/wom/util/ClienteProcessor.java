package cl.wom.util;


import java.sql.Connection;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONArray;

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

		String sql = (String) exchange.getIn().getBody();

		Cliente cliente = null;
		Connection co;
		

		if (headerBD.equals("getInfoSuscriptorCarrierBilling")) {
			
			
			if(exchange.getIn().getHeader("msisdn").toString().length()>=30) {
				//exchange.setProperty("exceds", 0);
				throw new ServiceError("452");
			}


			co = ConnectionFactory.getConnection(DataBaseSchema.BSCS);

			cliente = clienteDaoImpl.getInfoSuscriptorCarrierBilling(sql, co);
			

			if (cliente.getRut() == null) {
				throw new ServiceError("MSISDN no disponible");
			} else {
				exchange.setProperty("customerIdProperty",cliente.getCustomerId());
			
				exchange.setProperty("customerIdHighProperty",cliente.getCustomerIdHigh());
				exchange.setProperty("numCelularProperty",cliente.getNumCelular());
				exchange.setProperty("antiguedadProperty",cliente.getAntiguedad());
				exchange.setProperty("contractIdProperty",cliente.getContractId());
				exchange.setProperty("ratePlanProperty",cliente.getRateplan());
				exchange.setProperty("cargoBasicoProperty",cliente.getCargoBasico());
				exchange.setProperty("msisdnProperty", exchange.getIn().getHeader("msisdn"));
				exchange.getIn().setBody(cliente);
			}


		}
		
		
		if (headerBD.equals("getSuscripcionesCarrierExist")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);

			int contador = clienteDaoImpl.getSuscripcionesCarrierExist(sql, co);
			
			if(contador != 0) {
				throw new ServiceError("Cliente ya posse un subscripcion activa");
			}
			exchange.getIn().setBody(contador);

		}
		if(headerBD.equals("getCustomerContractMoreOld")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			

			String dnNum = clienteDaoImpl.getCustomerContractMoreOld(sql, co);
			 String msisdn = (String) exchange.getProperty("numCelularProperty");
            
            
          
            
           int cont=0;
  	
			if(!dnNum.equals(msisdn)) {
				cont=1;
				throw new ServiceError("MISISDN no coincide con el responsable de pago");
				
				
			}
			exchange.getIn().setBody(cont);
		
			
		}
		
		if(headerBD.equals("getCustomerPagador")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.BSCS);
			

			String customerId = clienteDaoImpl.getCustomerPagador(sql, co);
			if(customerId==null) {
				throw new ServiceError("No existe responsable de pago");
			}
			

			exchange.getIn().setBody(customerId);
		
			
		}
		
		if (headerBD.equals("getofertacarrier")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);

			List<Cliente>clientes = clienteDaoImpl.getofertacarrier(sql, co);
			
			
	 		JSONArray jsonObj = new org.json.JSONArray(clientes);
	 		
	 		int count;

			if (clientes.size()>0) {
				
				for(Cliente c: clientes ) {
					co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
					count =clienteDaoImpl.validacionPreInsert("SELECT COUNT(*) FROM CARRIERBILLING.CONT_OFFER_CARRIER_BILLING_TO WHERE ID_OFERTA ='"+c.getIdOferta()+"' AND CUSTOMER_ID="+exchange.getProperty("customerIdProperty"), co);
					
					if(count==0) {
					co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
					clienteDaoImpl.insertaregelegcarrierbilling("INSERT INTO CARRIERBILLING.CONT_OFFER_CARRIER_BILLING_TO (ID_OFERTA,CUSTOMER_ID,NUM_CELULAR,CONTRATO_ID,RATEPLAN,ANTIGUEDAD,CARGO_BASICO,ESTADO_OFERTA) VALUES ('"+c.getIdOferta()+"', "+exchange.getProperty("customerIdProperty")+", '"+exchange.getProperty("numCelularProperty")+"', "+exchange.getProperty("contractIdProperty")+", '"+exchange.getProperty("ratePlanProperty")+"', "+exchange.getProperty("antiguedadProperty")+", "+exchange.getProperty("cargoBasicoProperty")+", 2)", co);
				}
					
				}
				
			
				exchange.getIn().setBody(jsonObj.toString());
			} else {
				throw new ServiceError("No existe oferta carrier billing");
			}

		}
		if (headerBD.equals("validacionpreinsert")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
		
		
			exchange.getIn().setBody(clienteDaoImpl.validacionPreInsert(sql, co));
			exchange.getIn().setHeader("idOferta",exchange.getProperty("idOfertaProperty"));
			exchange.getIn().setHeader("desOferta",exchange.getProperty("desOfertaProperty"));

		}
		if (headerBD.equals("insertaregelegcarrierbilling")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			clienteDaoImpl.insertaregelegcarrierbilling(sql, co);
		
			exchange.getIn().setHeader("idOferta",exchange.getProperty("idOfertaProperty"));
			exchange.getIn().setHeader("desOferta",exchange.getProperty("desOfertaProperty"));

		}

	}

}
