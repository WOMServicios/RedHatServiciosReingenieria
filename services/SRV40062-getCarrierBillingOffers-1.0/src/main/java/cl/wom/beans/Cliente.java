package cl.wom.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
	private Date fechaActivacion;
	private String mercado;
	private BigDecimal cargoBasico;
	private String dnNum;
	private BigDecimal contador;
	private String idOferta;
	private String desOferta;
	private String idOccBscs; 
	private BigDecimal mesesAntiguedad;
	private BigDecimal valorMinimo;
	private Date fecDesde;
	private Date fecHasta;

	public Cliente() {

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

	public Date getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(Date fechaActivacion) {
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

	public String getDnNum() {
		return dnNum;
	}

	public void setDnNum(String dnNum) {
		this.dnNum = dnNum;
	}

	public BigDecimal getContador() {
		return contador;
	}

	public void setContador(BigDecimal contador) {
		this.contador = contador;
	}

	public String getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	public String getDesOferta() {
		return desOferta;
	}

	public void setDesOferta(String desOferta) {
		this.desOferta = desOferta;
	}

	public String getIdOccBscs() {
		return idOccBscs;
	}

	public void setIdOccBscs(String idOccBscs) {
		this.idOccBscs = idOccBscs;
	}

	public BigDecimal getMesesAntiguedad() {
		return mesesAntiguedad;
	}

	public void setMesesAntiguedad(BigDecimal mesesAntiguedad) {
		this.mesesAntiguedad = mesesAntiguedad;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public Date getFecDesde() {
		return fecDesde;
	}

	public void setFecDesde(Date fecDesde) {
		this.fecDesde = fecDesde;
	}

	public Date getFecHasta() {
		return fecHasta;
	}

	public void setFecHasta(Date fecHasta) {
		this.fecHasta = fecHasta;
	}



	
}
