package cl.wom.beans;

import java.io.Serializable;
import java.sql.Date;
;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msisdn;
	private String customerId;
	private String codId;
	private String rateplan;
	private String tipoContrato;
	private String estado;
	private Date fechaActivacion;
	private Date fechaDesactivacion;
	private String secuencia;
	private String requestId;
	private String bangoTransactionId;
	private String merchanTransactionId;
	private String PaymentProviderTransaction;
	private String UserId;
	private String amount;
	private String currency;
	private String responsePay;
	private String descriptionResponsePay;
	private Date datePay;
	private  String merchantAccountKey;
	private String productKey;
	private String ProductDescription;
	private String productCategory;
	private String SupportContact;
	private String action;
	
	
	
	
	public String getMerchanTransactionId() {
		return merchanTransactionId;
	}
	public void setMerchanTransactionId(String merchanTransactionId) {
		this.merchanTransactionId = merchanTransactionId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getBangoTransactionId() {
		return bangoTransactionId;
	}
	public void setBangoTransactionId(String bangoTransactionId) {
		this.bangoTransactionId = bangoTransactionId;
	}
	public String getPaymentProviderTransaction() {
		return PaymentProviderTransaction;
	}
	public void setPaymentProviderTransaction(String paymentProviderTransaction) {
		PaymentProviderTransaction = paymentProviderTransaction;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getResponsePay() {
		return responsePay;
	}
	public void setResponsePay(String responsePay) {
		this.responsePay = responsePay;
	}
	public String getDescriptionResponsePay() {
		return descriptionResponsePay;
	}
	public void setDescriptionResponsePay(String descriptionResponsePay) {
		this.descriptionResponsePay = descriptionResponsePay;
	}
	public Date getDatePay() {
		return datePay;
	}
	public void setDatePay(Date datePay) {
		this.datePay = datePay;
	}
	public String getMerchantAccountKey() {
		return merchantAccountKey;
	}
	public void setMerchantAccountKey(String merchantAccountKey) {
		this.merchantAccountKey = merchantAccountKey;
	}
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	public String getProductDescription() {
		return ProductDescription;
	}
	public void setProductDescription(String productDescription) {
		ProductDescription = productDescription;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getSupportContact() {
		return SupportContact;
	}
	public void setSupportContact(String supportContact) {
		SupportContact = supportContact;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSecuencia() {
		return secuencia;
	}
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCodId() {
		return codId;
	}
	public void setCodId(String codId) {
		this.codId = codId;
	}
	public String getRateplan() {
		return rateplan;
	}
	public void setRateplan(String rateplan) {
		this.rateplan = rateplan;
	}
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaActivacion() {
		return fechaActivacion;
	}
	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	public Date getFechaDesactivacion() {
		return fechaDesactivacion;
	}
	public void setFechaDesactivacion(Date fechaDesactivacion) {
		this.fechaDesactivacion = fechaDesactivacion;
	}
	@Override
	public String toString() {
		return "Cliente [msisdn=" + msisdn + ", customerId=" + customerId + ", codId=" + codId + ", rateplan="
				+ rateplan + ", tipoContrato=" + tipoContrato + ", estado=" + estado + ", fechaActivacion="
				+ fechaActivacion + ", fechaDesactivacion=" + fechaDesactivacion + ", secuencia=" + secuencia
				+ ", requestId=" + requestId + ", bangoTransactionId=" + bangoTransactionId + ", merchanTransactionId="
				+ merchanTransactionId + ", PaymentProviderTransaction=" + PaymentProviderTransaction + ", UserId="
				+ UserId + ", amount=" + amount + ", currency=" + currency + ", responsePay=" + responsePay
				+ ", descriptionResponsePay=" + descriptionResponsePay + ", datePay=" + datePay
				+ ", merchantAccountKey=" + merchantAccountKey + ", productKey=" + productKey + ", ProductDescription="
				+ ProductDescription + ", productCategory=" + productCategory + ", SupportContact=" + SupportContact
				+ ", action=" + action + "]";
	}
	

	
	

}
