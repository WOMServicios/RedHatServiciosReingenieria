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
import cl.wom.database.ConnectionFactory;
import cl.wom.database.ConnectionFactory.DataBaseSchema;
import cl.wom.exception.services.ServiceError;

public class ClienteProcessor implements Processor {
	private ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();

	public void process(Exchange exchange) throws Exception {

		String headerBD = (String) exchange.getIn().getHeader("interface");

		System.err.println("base de datos " + headerBD);
		String sql = (String) exchange.getIn().getBody();

		Cliente cliente = null;
		Connection co;

		if (headerBD.contains("getrespuestaeligibilidad")) {

			ConnectionFactory coni = new ConnectionFactory();
			coni.prueba();

			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);

			int count = clienteDaoImpl.getrespuestaeligibilidad(sql, co);

			exchange.getIn().setBody(count);
		}
		if (headerBD.contains("paymentTransactionId")) {

			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			String secuencia = clienteDaoImpl.paymentTransactionId(sql, co);

			exchange.getIn().setBody(secuencia);

		}
		if (headerBD.contains("getInfoSuscriptorCarrierBilling")) {

			co = ConnectionFactory.getConnection(DataBaseSchema.BSCS);

			cliente = clienteDaoImpl.getInfoSuscriptorCarrierBilling(sql, co);

			if (cliente.getRut() == null) {
				throw new ServiceError("416");
			} else {
				exchange.getIn().setBody(cliente);
			}

		}

		if (headerBD.contains("getSuscripcionesCarrierExist")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);

			int contador = clienteDaoImpl.getSuscripcionesCarrierExist(sql, co);

			exchange.getIn().setBody(contador);

		}
		if (headerBD.contains("getCustomerContractMoreOld")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.BSCS);

			String dnNum = clienteDaoImpl.getCustomerContractMoreOld(sql, co);
			if (dnNum == null) {
				throw new ServiceError("416");
			} else {
				System.err.println(dnNum + "prueba");
				exchange.getIn().setBody(dnNum);
			}

		}

		if (headerBD.contains("getCustomerPagador")) {
			co = ConnectionFactory.getConnection(DataBaseSchema.BSCS);

			String customerId = clienteDaoImpl.getCustomerPagador(sql, co);
			if (customerId == null) {
				throw new ServiceError("416");
			} else {

				exchange.getIn().setBody(customerId);
			}

		}

	}

}