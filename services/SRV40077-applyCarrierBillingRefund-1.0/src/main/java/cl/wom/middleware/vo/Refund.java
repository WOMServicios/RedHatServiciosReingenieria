package cl.wom.middleware.vo;

import java.sql.Date;

public class Refund {
	
	private String msisdn;
	private String shDesPlan;
	private Integer customerId;
	private Integer codId;
	private Integer rateplan;
	private String tipoContrato;
	private String estado;
	private Date fechaActivacion;
	private Date fechaDesactivacion;
	
	
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getShDesPlan() {
		return shDesPlan;
	}
	public void setShDesPlan(String shDesPlan) {
		this.shDesPlan = shDesPlan;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getCodId() {
		return codId;
	}
	public void setCodId(Integer codId) {
		this.codId = codId;
	}
	public Integer getRateplan() {
		return rateplan;
	}
	public void setRateplan(Integer rateplan) {
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
		return "Refund [msisdn=" + msisdn + ", shDesPlan=" + shDesPlan + ", customerId=" + customerId + ", codId="
				+ codId + ", rateplan=" + rateplan + ", tipoContrato=" + tipoContrato + ", estado=" + estado
				+ ", fechaActivacion=" + fechaActivacion + ", fechaDesactivacion=" + fechaDesactivacion + "]";
	}

	
}
