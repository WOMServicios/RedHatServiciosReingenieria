package cl.wom.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msisdn;
	private String customerId;
	private String codId;
	private String rateplan;
	private String tipoContrato;
	private String estado;
	private Date fechaActivacion;


	

	public Cliente() {

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




	@Override
	public String toString() {
		return "Cliente [msisdn=" + msisdn + ", customerId=" + customerId + ", codId=" + codId + ", rateplan="
				+ rateplan + ", tipoContrato=" + tipoContrato + ", estado=" + estado + ", fechaActivacion="
				+ fechaActivacion + "]";
	}


	
}
