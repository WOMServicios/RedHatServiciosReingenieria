package cl.wom.util;

import java.sql.Connection;
import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import cl.wom.beans.Cliente;
import cl.wom.database.ClienteDaoImpl;
import cl.wom.database.ConnectionFactory;
import cl.wom.database.ConnectionFactory.DataBaseSchema;
import cl.wom.exception.services.ServiceError;

public class ClienteProcessor implements Processor {
	private ClienteDaoImpl clienteDaoImpl = new ClienteDaoImpl();
	private Connection con;
	private Cliente cliente;
	private String responseCode;
	private String responseMessage;
	private PropertiesUtil propertiesUtil = new PropertiesUtil();

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		String headerBD = (String) exchange.getIn().getHeader("interface");
		

		String sql = (String) exchange.getIn().getBody();

		Properties prop = propertiesUtil.getProperties("sql.properties");

		if (headerBD.equals("getDatosFacturacion")) {

			String validacionLength = String.valueOf(exchange.getIn().getHeader("amount"));

			if (!ValidationUtil.isNumeric(validacionLength)) {
				throw new ServiceError("454");
			}
			if (exchange.getIn().getHeader("userId").toString().length() >= 22) {
				throw new ServiceError("452");
			}

			con = ConnectionFactory.getConnection(DataBaseSchema.BSCS);

			cliente = clienteDaoImpl.getDatosFacturacion(sql, con);

			exchange.setProperty("requestIdProperty", exchange.getIn().getHeader("requestId"));
			exchange.setProperty("bangoTransactionIdProperty", exchange.getIn().getHeader("bangoTransactionId"));
			exchange.setProperty("merchantTransactionIdIdProperty",exchange.getIn().getHeader("merchantTransactionId"));
			exchange.setProperty("userIdProperty", exchange.getIn().getHeader("userId"));
			exchange.setProperty("amountProperty", exchange.getIn().getHeader("amount"));
			exchange.setProperty("currencyProperty", exchange.getIn().getHeader("currency"));
			exchange.setProperty("merchantAccountKeyProperty", exchange.getIn().getHeader("merchantAccountKey"));
			exchange.setProperty("productKeyProperty", exchange.getIn().getHeader("productKey"));
			exchange.setProperty("productDescriptionProperty", exchange.getIn().getHeader("productDescription"));
			exchange.setProperty("productCategoryProperty", exchange.getIn().getHeader("productCategory"));
			exchange.setProperty("supportContactProperty", exchange.getIn().getHeader("supportContact"));

			if (cliente.getCodId() == null) {
				setResponseCode("USER_NOT_FOUND");
				setResponseMessage("The user credential cannot be found");
				throw new ServiceError("USER_NOT_FOUND");
			} else if (!cliente.getTipoContrato().equals("Postpaid")) {
				setResponseCode("USER_NOT_ENABLED");
				setResponseMessage("The user is not eligible");
				throw new ServiceError("USER_NOT_ENABLED");
			} else if (cliente.getEstado().equals("s")) {
				setResponseCode("USER_SUSPENDED");
				setResponseMessage("The user is suspended from using the service (permanent block)");
				throw new ServiceError("USER_SUSPENDED");
			} else if (cliente.getEstado().equals("d") || cliente.getEstado().equals("o")) {
				setResponseCode("USER_NOT_ENABLED");
				setResponseMessage("The user is not eligible");
				throw new ServiceError("USER_NOT_ENABLED");
			} else {
				setResponseCode("OK");
				setResponseMessage("Success");
			}

			exchange.setProperty("responseCodeProperty", getResponseCode());
			exchange.setProperty("responseMessageProperty", getResponseMessage());

			exchange.getIn().setBody(cliente);

		}
		if (headerBD.equals("paymentTransactionId")) {

			con = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
			String secuencia = clienteDaoImpl.paymentTransactionId(sql, con);

			if (secuencia != null) {
				cliente = new Cliente();
				cliente.setRequestId(exchange.getProperty("requestIdProperty").toString());
				cliente.setBangoTransactionId(exchange.getProperty("bangoTransactionIdProperty").toString());
				cliente.setMerchanTransactionId(exchange.getProperty("merchantTransactionIdIdProperty").toString());
				cliente.setUserId(exchange.getProperty("userIdProperty").toString());
				cliente.setAmount(exchange.getProperty("amountProperty").toString());
				cliente.setCurrency(exchange.getProperty("currencyProperty").toString());
				cliente.setMerchantAccountKey(exchange.getProperty("merchantAccountKeyProperty").toString());
				cliente.setProductKey(exchange.getProperty("productKeyProperty").toString());
				cliente.setProductDescription(exchange.getProperty("productDescriptionProperty").toString());
				cliente.setProductCategory(exchange.getProperty("productCategoryProperty").toString());
				cliente.setSupportContact(exchange.getProperty("supportContactProperty").toString());
				cliente.setAction("AUTORIZED");
				cliente.setSecuencia(secuencia);
				cliente.setResponsePay(exchange.getProperty("responseCodeProperty").toString());
				cliente.setDescriptionResponsePay(exchange.getProperty("responseMessageProperty").toString());

				if (insert(prop.getProperty("sql.insertAuthorizeCarrierbilling").toString(), cliente)) {
					System.out.println("Log exito insert");
				} else {
					System.out.println("Log error insert");
					
				}

			}

			exchange.getIn().setBody(secuencia);

		}

	}

	public boolean insert(String sql, Cliente cliente) throws ServiceError {
		con = ConnectionFactory.getConnection(DataBaseSchema.WAPPL);
		return clienteDaoImpl.insertAuthorizeCarrierbilling(sql, cliente, con);

	}

}
