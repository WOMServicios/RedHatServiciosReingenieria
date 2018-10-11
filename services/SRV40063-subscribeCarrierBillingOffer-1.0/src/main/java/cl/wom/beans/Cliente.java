package cl.wom.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msisdn;
	private BigDecimal customerId;
	private BigDecimal codId;
	private BigDecimal rateplan;
	private String tipoContrato;
	private char estado;
	private Date fechaActivacion;


	

	public Cliente() {

	}




	public String getMsisdn() {
		return msisdn;
	}




	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}




	public BigDecimal getCustomerId() {
		return customerId;
	}




	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}




	public BigDecimal getCodId() {
		return codId;
	}




	public void setCodId(BigDecimal codId) {
		this.codId = codId;
	}




	public BigDecimal getRateplan() {
		return rateplan;
	}




	public void setRateplan(BigDecimal rateplan) {
		this.rateplan = rateplan;
	}




	public String getTipoContrato() {
		return tipoContrato;
	}




	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}




	public char getEstado() {
		return estado;
	}




	public void setEstado(char estado) {
		this.estado = estado;
	}




	public Date getFechaActivacion() {
		return fechaActivacion;
	}




	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}







	
}
