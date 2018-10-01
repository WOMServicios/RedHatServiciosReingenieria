package cl.wom.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String rut;
	private BigDecimal customerId;
	private BigDecimal customerIdHigh;
	private BigDecimal contractId;
	private String numCelular;
	private String tipoContrato;
	private String rateplan;
	private BigDecimal antiguedad;
	private String ciclo;
	private char estadoContrato;
	private Timestamp fechaActivacion;
	private String mercado;
	private BigDecimal cargoBasico;
	private String dnNum;

	public Cliente() {

	}
	

	public String getDnNum() {
		return dnNum;
	}


	public void setDnNum(String dnNum) {
		this.dnNum = dnNum;
	}


	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public BigDecimal getCustomerId() {
		return customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getCustomerIdHigh() {
		return customerIdHigh;
	}

	public void setCustomerIdHigh(BigDecimal customerIdHigh) {
		this.customerIdHigh = customerIdHigh;
	}

	public BigDecimal getContractId() {
		return contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getRateplan() {
		return rateplan;
	}

	public void setRateplan(String rateplan) {
		this.rateplan = rateplan;
	}

	public BigDecimal getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(BigDecimal antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public char getEstadoContrato() {
		return estadoContrato;
	}

	public void setEstadoContrato(char estadoContrato) {
		this.estadoContrato = estadoContrato;
	}

	public Timestamp getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(Timestamp fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	public BigDecimal getCargoBasico() {
		return cargoBasico;
	}

	public void setCargoBasico(BigDecimal cargoBasico) {
		this.cargoBasico = cargoBasico;
	}

	@Override
	public String toString() {
		return "Cliente [rut=" + rut + ", customerId=" + customerId + ", customerIdHigh=" + customerIdHigh
				+ ", contractId=" + contractId + ", numCelular=" + numCelular + ", tipoContrato=" + tipoContrato
				+ ", rateplan=" + rateplan + ", antiguedad=" + antiguedad + ", ciclo=" + ciclo + ", estadoContrato="
				+ estadoContrato + ", fechaActivacion=" + fechaActivacion + ", mercado=" + mercado + ", cargoBasico="
				+ cargoBasico + "]";
	}

	
}
