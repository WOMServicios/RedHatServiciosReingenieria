package cl.wom.beans;


import java.io.Serializable;

public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String rut;
	private String customerId;
	private String customerIdHigh;
	private String contractId;
	private String numCelular;
	private String tipoContrato;
	private String rateplan;
	private String antiguedad;
	private String ciclo;
	private String estadoContrato;
	private String fechaActivacion;
	private String mercado;
	private String cargoBasico;
	

	public Cliente() {
	
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerIdHigh() {
		return customerIdHigh;
	}
	public void setCustomerIdHigh(String customerIdHigh) {
		this.customerIdHigh = customerIdHigh;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
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
	public String getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getEstadoContrato() {
		return estadoContrato;
	}
	public void setEstadoContrato(String estadoContrato) {
		this.estadoContrato = estadoContrato;
	}
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	public String getMercado() {
		return mercado;
	}
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}
	public String getCargoBasico() {
		return cargoBasico;
	}
	public void setCargoBasico(String cargoBasico) {
		this.cargoBasico = cargoBasico;
	}

	
	
	
}
