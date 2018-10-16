package cl.wom.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	

	private String idOferta;
	private String numCelular;
	private String contratoId;
	private String paymentProviderTransaction;
	private Date fechaIngreso;
	private String bangoTransactionId;
	private String  merchanTransactionId;
	private String amount;
	private String userId;
	private String responsePay;
	private Date datePay;
	
	public Cliente() {

	}

	public String getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	public String getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}


	public String getContratoId() {
		return contratoId;
	}

	public void setContratoId(String contratoId) {
		this.contratoId = contratoId;
	}

	public String getPaymentProviderTransaction() {
		return paymentProviderTransaction;
	}

	public void setPaymentProviderTransaction(String paymentProviderTransaction) {
		this.paymentProviderTransaction = paymentProviderTransaction;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getBangoTransactionId() {
		return bangoTransactionId;
	}

	public void setBangoTransactionId(String bangoTransactionId) {
		this.bangoTransactionId = bangoTransactionId;
	}

	public String getMerchanTransactionId() {
		return merchanTransactionId;
	}

	public void setMerchanTransactionId(String merchanTransactionId) {
		this.merchanTransactionId = merchanTransactionId;
	}



	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResponsePay() {
		return responsePay;
	}

	public void setResponsePay(String responsePay) {
		this.responsePay = responsePay;
	}

	public Date getDatePay() {
		return datePay;
	}

	public void setDatePay(Date datePay) {
		this.datePay = datePay;
	}

	@Override
	public String toString() {
		return "Cliente [idOferta=" + idOferta + ", numCelular=" + numCelular + ", contratoId=" + contratoId
				+ ", paymentProviderTransaction=" + paymentProviderTransaction + ", fechaIngreso=" + fechaIngreso
				+ ", bangoTransactionId=" + bangoTransactionId + ", merchanTransactionId=" + merchanTransactionId
				+ ", amount=" + amount + ", userId=" + userId + ", responsePay=" + responsePay + ", datePay=" + datePay
				+ "]";
	}

	
}
